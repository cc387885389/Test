package code;

import java.util.List;

public class TempFactory {

	public void createTemp(String name){
		TempInfoManager tif = new TempInfoManager();
		List<Info> listInfo = tif.analysis(name);
		for(Info info : listInfo){
			String outPutPath = info.getOutPutfilePath()+info.getPath();
			String outPutFileName = info.getClassName()+"."+info.getType();
			String templatePath = getClass().getResource("/").getFile().toString();
			String templateName = info.getTempName();
			Object modelValue = info;
			if(info.getTableName() != null && !info.getTableName().equals("")){
				modelValue = MapperManager.createDate(info);
			}
			FreeMarkerUtil.createTemplate(modelValue, outPutPath, outPutFileName, templatePath, templateName);
			System.out.println(outPutFileName+" 创建成功！");	
			
		}
		
	}

	public static void main(String[] args) {
		TempFactory factory = new TempFactory();
		factory.createTemp("Student");
	}
}
