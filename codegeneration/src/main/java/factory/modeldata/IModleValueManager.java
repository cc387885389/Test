package factory.modeldata;

import info.Info;

import java.util.List;

public interface IModleValueManager {

	public Object createModleValue(List<FieldInfo> fieldInfolist,Info info);
}
