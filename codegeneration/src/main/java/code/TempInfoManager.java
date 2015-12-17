package code;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@SuppressWarnings("unused")
public class TempInfoManager {

	private final String properties = "/ftl.properties";
	private Properties prop = new Properties();

	private void readProperties() {
		InputStream in = FreeMarkerUtil.class.getResourceAsStream(properties);
		try {
			prop.load(in);
		} catch (IOException e) {
			throw new RuntimeException("properties文件读取失败！ " + e.getMessage());
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				in = null;
			}
		}
	}

	public List<Info> analysis(String name){
		if(name == null){
			name = "";
		}
		readProperties();
		
		String temp_list = prop.getProperty("temp.list");
		
		if(temp_list == null)
			throw new RuntimeException("没有读取到properties文件temp.list属性！ ");
		
		String[] tempArr = temp_list.trim().split(",");
		List<Info> infoList = new ArrayList<Info>();
		
		for(String attr : tempArr){
			
			String open = prop.getProperty(attr+".open");
			if(open != null && open.equals("false")){
				continue;
			}
			
			String path = prop.getProperty(attr+".path");
			String endname = prop.getProperty(attr+".endname");
			String startname = prop.getProperty(attr+".startname");
			String type = prop.getProperty(attr+".type");
			String tempname = prop.getProperty(attr+".tempname");
			String outPutfilePath = prop.getProperty("outputfile.path");
			String tableName =  prop.getProperty(attr+".tablename");
			
			Info info = new Info();
			if(endname == null || endname.trim().equals("")){
				endname = "";
			}
			if(startname == null || startname.trim().equals("")){
				startname = "";
			}
			if(path == null || path.trim().equals("")){
				throw new RuntimeException("没有读取到properties文件"+attr+".path属性！ ");
			}
			if(type == null || type.trim().equals("")){
				throw new RuntimeException("没有读取到properties文件"+attr+".type属性！ ");
			}
			if(tempname == null || tempname.trim().equals("")){
				throw new RuntimeException("没有读取到properties文件"+attr+".tempname属性！ ");
			}
			if(outPutfilePath == null || outPutfilePath.trim().equals("")){
				throw new RuntimeException("没有读取到properties文件outputfile.path属性！ ");
			}
			if(tableName != null ){
				if(!tableName.trim().equals("") && type.trim().equals("xml")){
					info.setTableName(tableName.trim());
				}
			}
			
			info.setName(name);
			info.setStartName(startname.trim());
			info.setEndName(endname.trim());
			info.setOutPutfilePath(outPutfilePath.trim());
			info.setPath(path.trim());
			info.setTempName(tempname.trim());
			info.setType(type.trim());
			
			infoList.add(info);
		}
		return infoList;
	}

}
