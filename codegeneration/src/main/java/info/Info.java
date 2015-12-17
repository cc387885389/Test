package info;

/**
 * 输出模板基本信息
 * @author YUZHE
 * 2015年12月17日 下午3:48:42
 */
public class Info extends BaseInfo{
	
	//当前类名
	private String className;
	//小写bean名
	private String lowerCaseName;
	//输出文件路径
	private String outPutfilePath;
	//输出文件子路径
	private String path;
	//bean名前缀
	private String startName;
	//bean名后缀
	private String endName;
	//输出文件类型
	private String type;
	//模板名称
	private String tempName;
	
	
	public String getClassName() {
		className = startName + name + endName;
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getLowerCaseName() {
		lowerCaseName = name.toLowerCase();
		return lowerCaseName;
	}
	public void setLowerCaseName(String lowerCaseName) {
		this.lowerCaseName = lowerCaseName;
	}
	public String getOutPutfilePath() {
		return outPutfilePath;
	}
	public void setOutPutfilePath(String outPutfilePath) {
		this.outPutfilePath = outPutfilePath;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getStartName() {
		return startName;
	}
	public void setStartName(String startName) {
		this.startName = startName;
	}
	public String getEndName() {
		return endName;
	}
	public void setEndName(String endName) {
		this.endName = endName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTempName() {
		return tempName;
	}
	public void setTempName(String tempName) {
		this.tempName = tempName;
	}
	
}