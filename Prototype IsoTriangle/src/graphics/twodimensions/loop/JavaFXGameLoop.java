package graphics.twodimensions.loop;

import graphics.twodimensions.factory.JavaFXArtist2D;
import implementation.object.IterableEntity;
import implementation.world.IndexMaster;
import javafx.animation.AnimationTimer;

public class JavaFXGameLoop extends AnimationTimer {

	JavaFXArtist2D artist;
	IndexMaster index_master;
	
	public JavaFXGameLoop(JavaFXArtist2D artist, IndexMaster index_master) {
		this.artist = artist;
		this.index_master = index_master;
	}
	
	@Override
	public void handle(long now) {
		index_master.getIterableEntityIndexer().setStackingMode(true);
		for(IterableEntity e : index_master.getIterableEntityIndexer().getList())
			e.iterate();
		index_master.getIterableEntityIndexer().setStackingMode(false);
	}

}
