package implementation.world.indexers;

import java.util.List;

public interface Indexer<T> {
	public List<T> getList();
	public void addListener(Listener<T> listener);
	
	public static interface Listener<T> {
		public void onSpawn(T t);
		public void onRemoval(T t);
	}
}
