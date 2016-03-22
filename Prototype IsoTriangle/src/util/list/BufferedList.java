package util.list;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class BufferedList<S> implements Iterable<S> {
	private List<S> entering_buffer;
	private List<S> list;
	private List<S> exiting_buffer;
	private boolean in_buffering_mode;
	
	public BufferedList() {
		entering_buffer = new LinkedList<S>();
		list = new LinkedList<S>();
		exiting_buffer = new LinkedList<S>();
		in_buffering_mode = false;
	}
	
	public void add(S object) {
		if( ! in_buffering_mode ) {
			list.add(object);
			//notifyAdding(object);
		}
		else entering_buffer.add(object);
	}
	
	/*public void notifyAdding(S object) {
		for(Indexer.Listener<T> listener : listeners)
			listener.onSpawn(t);
	}*/

	public void remove(S object) {
		if( ! in_buffering_mode ) {
			list.remove(object);
			//notifyRemoval(t);
		}
		else exiting_buffer.add(object);
	}
	
	/*public void notifyRemoval(T t) {
		for(Indexer.Listener<T> listener : listeners)
			listener.onRemoval(t);
	}*/
	
	public void setBufferingMode(boolean set_buffering_mode) {
		if( in_buffering_mode && ! set_buffering_mode ) {
			in_buffering_mode = false;
			for(S object : entering_buffer)
				add(object);
			for(S object : exiting_buffer)
				remove(object);
			entering_buffer.clear();
			exiting_buffer.clear();
		}
		else in_buffering_mode = set_buffering_mode;
	}

	public List<S> getList() {
		return list;
	}

	/*@Override
	public void addListener(Indexer.Listener<T> listener) {
		listeners.add(listener);
	}*/
	
	public void clear() {
		list.clear();
		entering_buffer.clear();
		exiting_buffer.clear();	
	}

	@Override
	public Iterator<S> iterator() {
		return list.iterator();
	}
}
