package factory;

import info.BaseInfo;
import info.Info;

import java.util.ArrayList;
import java.util.List;

import propertie.PropertiesInfoManager;
import uitls.JDBCTools;
import code.FreeMarkerUtil;
import code.MapperManager;
import factory.modeldata.FieldInfo;
import factory.modeldata.IModleValueManager;

public class TempFactory {

	private List<FieldInfo> fieldInfolist= new ArrayList<FieldInfo>();
	private List<Info> listInfo = null;
	private BaseInfo base = null;
	
	public TempFactory() {
		init();
	}
	
	private void init(){
		PropertiesInfoManager pim = new PropertiesInfoManager();
		listInfo = pim.analysis();
		base = pim.getBaseInfo();
		JDBCTools tool = new JDBCTools();
		fieldInfolist = tool.analysisTable(base);
	}
	
	/**
	 * 创建模板
	 * @param name
	 * 2015年12月17日 下午4:07:37
	 */
	public void createTemp(String name){
		
		//拼装数据
		for(Info info : listInfo){
			String outPutPath = info.getOutPutfilePath()+info.getPath();
			String outPutFileName = info.getClassName()+"."+info.getType();
			String templatePath = getClass().getResource("/").getFile().toString();
			String templateName = info.getTempName();
			Object modelValue = info;
			if(info.getType().equals("xml")){
				modelValue = createDate();
			}else{
				
			}
			FreeMarkerUtil.createTemplate(modelValue, outPutPath, outPutFileName, templatePath, templateName);
			System.out.println(outPutFileName+" 创建成功！");	
			
		}
		
	}
	
	/**
	 * 生成数据
	 * @param modleValue
	 * @param fieldInfolist
	 * @param info
	 * @return
	 * 2015年12月17日 下午4:52:47
	 */
	private Object createDate(IModleValueManager modleValue,List<FieldInfo> fieldInfolist,Info info){
		return modleValue.createModleValue(fieldInfolist, info);
	}
	

	public static void main(String[] args) {
		TempFactory factory = new TempFactory();
		factory.createTemp("Student");
	}
}
