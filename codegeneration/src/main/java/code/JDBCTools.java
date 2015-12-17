package code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JDBCTools {
	
	public static final String field = "Field";
	public static final String key = "Key";

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private String url = "jdbc:mysql://192.168.3.6/rocomall?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&allowMultiQueries=true";
	private String user = "dev_mall";
	private String pwd = "123456";
	private static Map<String,String> map = new HashMap<String,String>();

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			map.put("COLUMN_NAME", "Field");
			map.put("COLUMN_TYPE", "Type");
			map.put("IS_NULLABLE", "Null");
			map.put("COLUMN_KEY", "Key");
			map.put("COLUMN_DEFAULT", "Default");
			map.put("EXTRA", "Extra");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	private PreparedStatement getStmt(String sql) {
		try {
			conn = DriverManager.getConnection(url, user, pwd);
			stmt = conn.prepareStatement(sql);
			return stmt;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Map<String, Object>> query(String sql) {
		try {
			List<Map<String, Object>> rsList = new LinkedList<Map<String, Object>>();
			rs = getStmt(sql).executeQuery();
			if (rs != null) {
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnCount = rsmd.getColumnCount();
				String[] columnNames = new String[columnCount];
				for (int i = 0; i < columnNames.length; i++) {
					columnNames[i] = rsmd.getColumnName(i + 1);
				}
				while (rs.next()) {
					Map<String, Object> rsMap = new HashMap<String, Object>();
					for (int i = 0; i < columnNames.length; i++) {
							String key = map.get(columnNames[i]);
							String value = rs.getString(key);
							rsMap.put(key, value);
					}
					rsList.add(rsMap);
				}
			}
			return rsList;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			close();
		}
	}

	public void close() {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				rs = null;
				e.printStackTrace();
			}
		}
		if (stmt != null) {
			try {
				stmt.close();
			} catch (Exception e) {
				stmt = null;
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				conn = null;
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		JDBCTools jt = new JDBCTools();
		System.out.println(jt.query("desc dict_platform").size());
		
	}
}
