package uitls;

import info.BaseInfo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import factory.modeldata.FieldInfo;

public class JDBCTools {
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	private String url = "jdbc:mysql://192.168.3.6/rocomall?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true&allowMultiQueries=true";
	private String user = "dev_mall";
	private String pwd = "123456";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
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

	private List<Map<String, Object>> query(String sql) {
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
							String key = columnNames[i];
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

	private void close() {
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

	public List<FieldInfo> analysisTable(BaseInfo info){
		if(info == null || info.getTableName().equals("")){
			return null;
		}
		String sql = "SELECT distinct COLUMN_NAME, DATA_TYPE FROM information_schema.columns WHERE table_name = '"+info.getTableName()+"'";
		List<Map<String, Object>> query = query(sql);
		List<FieldInfo> list = new ArrayList<FieldInfo>();
		if(query!= null && query.size() >0){
			for(Map<String, Object> m : query){
				FieldInfo f = new FieldInfo();
				f.setTableColumnName(m.get("COLUMN_NAME").toString());
				f.setTableColumnType(m.get("DATA_TYPE").toString());
				list.add(f);
			}
			return list;
		}
		return null;
	}
}
