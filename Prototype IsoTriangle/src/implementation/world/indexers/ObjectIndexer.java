package implementation.world.indexers;

import implementation.world.listeners.EntityRemovalAnalyst;
import implementation.world.listeners.EntitySpawnAnalyst;

import java.util.LinkedList;
import java.util.List;

import core.object.Entity;

public class ObjectIndexer<T> implements Indexer<T>, EntityRemovalAnalyst, EntitySpawnAnalyst {

	private List<T> objects;
	
	private List<T> adding_list;
	private List<T> removing_list;
	
	private List<Indexer.Listener<T>> listeners;
	
	private Class<T> type;
	
	private boolean in_stacking_mode;
	
	public ObjectIndexer(Class<T> type) {
		objects = new LinkedList<T>();
		adding_list = new LinkedList<T>();
		removing_list = new LinkedList<T>();
		listeners = new LinkedList<Indexer.Listener<T>>();
		this.type = type;
		this.in_stacking_mode = false;
	}
	
	@Override
	public void onSpawn(Entity entity) {
		if( type.isInstance(entity) ) {
			T t = type.cast(entity);
			confirmSpawn(t);
		}
	}
	
	public void confirmSpawn(T t) {
		if( ! in_stacking_mode ) {
			objects.add(t);
			notifySpawn(t);
		}
		else adding_list.add(t);
	}
	
	public void notifySpawn(T t) {
		for(Indexer.Listener<T> listener : listeners)
			listener.onSpawn(t);
	}

	@Override
	public void onRemoval(Entity entity) {
		if( type.isInstance(entity) ) {
			T t = type.cast(entity);
			confirmRemoval( t );
		}
	}
	
	public void confirmRemoval(T t) {
		if( ! in_stacking_mode ) {
			objects.remove(t);
			notifyRemoval(t);
		}
		else removing_list.add(t);
	}
	
	public void notifyRemoval(T t) {
		for(Indexer.Listener<T> listener : listeners)
			listener.onRemoval(t);
	}
	
	public void setStackingMode(boolean stacking_mode) {
		if( in_stacking_mode && ! stacking_mode ) {
			in_stacking_mode = false;
			for(T t : adding_list)
				confirmSpawn(t);
			for(T t : removing_list)
				confirmRemoval(t);
			adding_list.clear();
			removing_list.clear();
		}
		else in_stacking_mode = stacking_mode;
	}

	@Override
	public List<T> getList() {
		return objects;
	}

	@Override
	public void addListener(Indexer.Listener<T> listener) {
		listeners.add(listener);
	}
	
	public void clear() {
		objects.clear();
		adding_list.clear();
		removing_list.clear();	
	}
}
