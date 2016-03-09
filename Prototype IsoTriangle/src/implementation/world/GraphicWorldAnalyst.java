package implementation.world;

import implementation.world.catcher.Catcher;
import core.artist.Artist;
import core.artist.PaintableEntity;

public class GraphicWorldAnalyst implements Catcher.Listener<PaintableEntity> {

	private Artist artist;
	
	public GraphicWorldAnalyst(CatchMaster master, Artist artist) {
		this.artist = artist;
		
		master.getPaintingCatcher().addListener(this);
	}

	@Override
	public void onSpawn(PaintableEntity t) {
		artist.notifyObject(t);
	}

	@Override
	public void onRemoval(PaintableEntity t) {
		artist.removeObject(t);
	}

}
