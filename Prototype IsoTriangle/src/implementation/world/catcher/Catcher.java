package implementation.world.catcher;

public interface Catcher<T> {
	
	public void addListener(Listener<T> listener);
	
	public static interface Listener<T> {
		public void onSpawn(T t);
		public void onRemoval(T t);
	}
}
