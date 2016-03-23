package graphics.twodimensions.loop;

import graphics.twodimensions.factory.JavaFXArtist2D;
import implementation.object.IterableEntity;
import implementation.world.IndexMaster;
import implementation.world.indexers.ObjectIndexer;
import javafx.animation.AnimationTimer;

public class JavaFXGameLoop extends AnimationTimer {

	JavaFXArtist2D artist;
	
	private ObjectIndexer<IterableEntity> iterable_entity_index;
	
	public JavaFXGameLoop(JavaFXArtist2D artist, IndexMaster index_master) {
		this.artist = artist;
		
		iterable_entity_index = new IndexMaster.Accessor<IterableEntity>(index_master).getIndexer(IterableEntity.class);
	}
	
	@Override
	public void handle(long now) {
		iterable_entity_index.setStackingMode(true);
		for(IterableEntity e : iterable_entity_index.getList())
			e.iterate();
		iterable_entity_index.setStackingMode(false);
	}

}
