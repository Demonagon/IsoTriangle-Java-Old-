package implementation.world;

import java.util.HashMap;
import java.util.Map;

import implementation.world.indexers.ObjectIndexer;

public class IndexMaster {
	private Map<Object, Object> indexers;
	
	/*private ObjectIndexer<Booster> booster_indexer;
	private ObjectIndexer<Floor> floor_indexer;
	private ObjectIndexer<IterableEntity> iterable_indexer;
	private ObjectIndexer<Square> square_indexer;
	private ObjectIndexer<TaggedEntity> tagged_indexer;
	private ObjectIndexer<Objective> objective_indexer;*/
	
	private IsoTriangleWorldAnalyst main_analyst;
	
	public IndexMaster(IsoTriangleWorldAnalyst main_analyst) {
		indexers = new HashMap<Object, Object>();
		this.main_analyst = main_analyst;
	}
	
	@SuppressWarnings("rawtypes")
	public void addIndexer(Class type, ObjectIndexer indexer) {
		indexers.put(type, indexer);
		main_analyst.addAnalyst(indexer);
	}
	
	public static class Accessor<T> {
		
		private IndexMaster master;
		
		public Accessor(IndexMaster master) {
			this.master = master;
		}
		
		public ObjectIndexer<T> getIndexer(Class<T> type) {
			if ( ! master.indexers.containsKey(type) ) {
				System.err.println("Error : no indexer for type " + type.getName());
				return null;
			}
			
			Object result = master.indexers.get(type);
			
			if( ! (result instanceof ObjectIndexer<?>) ) {
				System.err.println("Error in IndexMaster : object at class " + type.getName() + " is not an indexer, but of class " +
								   result.getClass() );
				return null;
			}
			
			@SuppressWarnings("unchecked")
			ObjectIndexer<T> indexer = (ObjectIndexer<T>) result;
			return indexer;
		}
	}
	
	@SuppressWarnings("rawtypes")
	public Object getIndexer(Class type) {
		return indexers.get(type);
	}
}
