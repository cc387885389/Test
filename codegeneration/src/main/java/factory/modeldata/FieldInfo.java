package factory.modeldata;

public class FieldInfo {

	//实体类字段名
	private String fieldName;
	//实体类字段类型
	private String fieldType;
	//数据库列名
	private String tableColumnName;
	//数据库字段类型
	private String tableColumnType;
	
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public String getTableColumnName() {
		return tableColumnName;
	}
	public void setTableColumnName(String tableColumnName) {
		this.tableColumnName = tableColumnName;
	}
	public String getTableColumnType() {
		return tableColumnType;
	}
	public void setTableColumnType(String tableColumnType) {
		this.tableColumnType = tableColumnType;
	}
}
