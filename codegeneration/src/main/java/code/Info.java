package code;

public class Info {
	
	private String name = "";
	private String className;
	private String minClassName;
	private String outPutfilePath;
	private String tempFilePath;
	private String path;
	private String startName;
	private String endName;
	private String type;
	private String tempName;
	private String tableName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClassName() {
		className = startName + name + endName;
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMinClassName() {
		minClassName = name.toLowerCase();
		return minClassName;
	}
	public void setMinClassName(String minClassName) {
		this.minClassName = minClassName;
	}
	public String getOutPutfilePath() {
		return outPutfilePath;
	}
	public void setOutPutfilePath(String outPutfilePath) {
		this.outPutfilePath = outPutfilePath;
	}
	public String getTempFilePath() {
		return tempFilePath;
	}
	public void setTempFilePath(String tempFilePath) {
		this.tempFilePath = tempFilePath;
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
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}