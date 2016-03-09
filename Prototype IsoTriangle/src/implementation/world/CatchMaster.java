package implementation.world;

import core.artist.PaintableEntity;
import implementation.world.catcher.ObjectCatcher;

public class CatchMaster {
	private ObjectCatcher<PaintableEntity> painting_catcher;
	
	public CatchMaster(IsoTriangleWorldAnalyst main_analyst) {
		painting_catcher = new ObjectCatcher<PaintableEntity>(PaintableEntity.class);
		
		main_analyst.addAnalyst(painting_catcher);
	}
	
	public ObjectCatcher<PaintableEntity> getPaintingCatcher() {
		return painting_catcher;
	}
}
