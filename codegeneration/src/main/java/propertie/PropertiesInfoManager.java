package propertie;

import info.BaseInfo;
import info.Info;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import engine.FreeMarkerEngine;

@SuppressWarnings("unused")
public class PropertiesInfoManager {

	//properties文件路径，相对于项目根目录
	private final String properties = "/ftl.properties";
	private Properties prop = new Properties();

	/**
	 * 读取配置文件
	 * 
	 * 2015年12月17日 下午3:56:11
	 */
	private void readProperties() {
		InputStream in = FreeMarkerEngine.class.getResourceAsStream(properties);
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

	/**
	 * 解析并验证配置文件信息
	 * @return
	 * 2015年12月17日 下午3:56:25
	 */
	public List<Info> analysis(){
		readProperties();
		
		String temp_list = prop.getProperty("temp.list");
		
		if(temp_list == null)
			throw new RuntimeException("没有读取到properties文件temp.list属性！ ");
		
		String[] tempArr = temp_list.trim().split(",");
		List<Info> infoList = new ArrayList<Info>();
		
		for(String attr : tempArr){
			//如果open 属性==false 则跳过
			String open = prop.getProperty(attr+".open");
			if(open != null && open.equals("false")){
				continue;
			}
			infoList.add(verify(attr));
		}
		return infoList;
	}
	
	public BaseInfo getBaseInfo(){
		BaseInfo base = new BaseInfo();
		String name = prop.getProperty("class.name");
		String tableName =  prop.getProperty("table.name");
		if(name == null || name.trim().equals("")){
			throw new RuntimeException("没有读取到properties文件class.name属性！ ");
		}
		if(tableName != null && !tableName.trim().equals("")){
			base.setTableName(tableName.trim());
			base.setName(name);
		}
		return base;
	}

	/**
	 * 通过标识验证，如果验证通过返回info,否则抛出异常
	 * @param flag
	 * @return
	 * 2015年12月17日 下午3:59:40
	 */
	private Info verify(String attr){
		
		String name = prop.getProperty("class.name");
		String tableName =  prop.getProperty("table.name");
		String outPutfilePath = prop.getProperty("outputfile.path");
		String path = prop.getProperty(attr+".path");
		String endname = prop.getProperty(attr+".endname");
		String startname = prop.getProperty(attr+".startname");
		String type = prop.getProperty(attr+".type");
		String tempname = prop.getProperty(attr+".tempname");
		
		Info info = new Info();
		if(name == null || name.trim().equals("")){
			throw new RuntimeException("没有读取到properties文件class.name属性！ ");
		}
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
		if(tableName != null && !tableName.trim().equals("")){
			info.setTableName(tableName.trim());
		}
		
		info.setName(name);
		info.setStartName(startname.trim());
		info.setEndName(endname.trim());
		info.setOutPutfilePath(outPutfilePath.trim());
		info.setPath(path.trim());
		info.setTempName(tempname.trim());
		info.setType(type.trim());
		
		return info;
	}
}
