package factory;

import info.Info;

import java.util.HashMap;
import java.util.Map;

public class ModleValueManager {

	Map<String,Object> modleValue = new HashMap<String,Object>();
	
	public Object getCommonValue(Info info){
		modleValue.put("data", info);
		return modleValue;
	}
	
	public Object getMapperValue(Info info){
		
	}
}
