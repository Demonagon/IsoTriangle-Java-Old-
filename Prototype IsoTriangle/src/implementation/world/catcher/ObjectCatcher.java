package implementation.world.catcher;

import java.util.LinkedList;
import java.util.List;

import core.object.Entity;
import implementation.world.listeners.EntityRemovalAnalyst;
import implementation.world.listeners.EntitySpawnAnalyst;

public class ObjectCatcher<T> implements Catcher<T>, EntityRemovalAnalyst, EntitySpawnAnalyst {

	private List<Catcher.Listener<T>> listeners;
	
	private Class<T> type;
	
	public ObjectCatcher(Class<T> type) {
		this.listeners = new LinkedList<Catcher.Listener<T>>();
		this.type = type;
	}
	
	@Override
	public void addListener(Catcher.Listener<T> listener) {
		listeners.add(listener);
	}

	@Override
	public void onSpawn(Entity entity) {
		if( ! type.isInstance(entity) ) return;
		T t = type.cast(entity);
		
		for(Catcher.Listener<T> listener : listeners)
			listener.onSpawn(t);
	}

	@Override
	public void onRemoval(Entity entity) {
		if( ! type.isInstance(entity) ) return;
		T t = type.cast(entity);
		
		for(Catcher.Listener<T> listener : listeners)
			listener.onRemoval(t);
	}

}
