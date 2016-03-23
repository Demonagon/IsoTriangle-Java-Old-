package implementation.world;

import implementation.world.catcher.Catcher;
import core.artist.Artist;
import core.artist.GraphicalAsset;
import core.artist.PaintableEntity;

public class GraphicWorldAnalyst {

	private Artist artist;
	private CatchMaster master;
	
	public GraphicWorldAnalyst(CatchMaster master, Artist artist) {
		this.master = master;
		this.artist = artist;
	}
	
	public void accessCatchers() {
		new CatchMaster.Accessor<PaintableEntity>(master)
			.getCatcher(PaintableEntity.class)
			.addListener( new PaintableObjectHooker() );
		
		new CatchMaster.Accessor<GraphicalAsset>(master)
		   .getCatcher(GraphicalAsset.class)
		   .addListener( new GraphicalAssetHooker() );
	}
	
	public class PaintableObjectHooker implements Catcher.Listener<PaintableEntity> {
		@Override
		public void onSpawn(PaintableEntity t) {
			artist.paintObject(t);
		}

		@Override
		public void onRemoval(PaintableEntity t) {
			artist.eraseObject(t);
		}
	}
	
	public class GraphicalAssetHooker implements Catcher.Listener<GraphicalAsset> {
		@Override
		public void onSpawn(GraphicalAsset a) {
			artist.addAsset(a);
		}

		@Override
		public void onRemoval(GraphicalAsset a) {
			artist.removeAsset(a);
		}
	}

}
