package implementation.object;

import java.util.HashMap;
import java.util.Map;

public class TagManager {
	private Map<String, Object> tags;
	
	public TagManager() {
		tags = new HashMap<String, Object>();
	}
	
	public void setTag(String name, Object value) {
		tags.put(name, value);
	}
	
	public Object getTag(String name) {
		if( tags.containsKey(name) )
			return tags.get(name);
		else return null;
	}
	
	public void clear() {
		tags.clear();
	}
}
