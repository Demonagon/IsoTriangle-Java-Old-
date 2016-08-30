package implementation.world;

import java.util.HashMap;
import java.util.Map;

import implementation.world.catcher.ObjectCatcher;

public class CatchMaster {
	private Map<Object, Object> catchers;
	
	private IsoTriangleWorldAnalyst main_analyst;
	
	public CatchMaster(IsoTriangleWorldAnalyst main_analyst) {
		this.catchers = new HashMap<Object, Object>();
		this.main_analyst = main_analyst;
	}
	
	@SuppressWarnings("rawtypes")
	public void addCatcher(Class type, ObjectCatcher catcher) {
		catchers.put(type, catcher);
		main_analyst.addAnalyst(catcher);
	}
	
	public static class Accessor<T> {
		
		private CatchMaster master;
		
		public Accessor(CatchMaster master) {
			this.master = master;
		}
		
		public ObjectCatcher<T> getCatcher(Class<T> type) {
			if ( ! master.catchers.containsKey(type) ) {
				System.err.println("Error : no catcher for type " + type.getName());
				return null;
			}
			
			Object result = master.catchers.get(type);
			
			if( ! (result instanceof ObjectCatcher<?>) ) {
				System.err.println("Error in CatchMaster : object at class " + type.getName() + " is not an catcher, but of class " +
								   result.getClass() );
				return null;
			}
			
			@SuppressWarnings("unchecked")
			ObjectCatcher<T> catcher = (ObjectCatcher<T>) result;
			return catcher;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Object getCatcher(Class type) {
		return catchers.get(type);
	}
}
