package code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperManager {

	public static Map<String, Object> createDate(Info info) {
		
		Map<String, Object> data = new HashMap<String,Object>();
		
		String tableName = info.getTableName();
		String alias = createAlias(tableName);
		
		StringBuilder sqlInfo = new StringBuilder();
		
		JDBCTools jt = new JDBCTools();
		List<Map<String, Object>> query = jt.query("desc "+tableName);
		if(query.size() != 0){
			for(Map<String, Object> map : query){
				String field = map.get(JDBCTools.field).toString();
				sqlInfo.append(alias+"."+field+",");
			}
			sqlInfo.deleteCharAt(sqlInfo.length()-1);
		}
		data.put("tableName", tableName);
		data.put("sqlInfo", sqlInfo);
		data.put("alias", alias);
		data.put("info", info);
		
		return data;
	}

	private static String createAlias(String tableName){
		tableName = tableName.toLowerCase();
		if(tableName.length() > 3){
			return tableName.substring(0,3);
		}
		return tableName;
	}
}
