package graphics.twodimensions.object;

import javafx.scene.Parent;
import javafx.scene.shape.Shape;
import core.artist.GraphicalAsset;
import core.artist.GraphicalObject;
import core.object.Entity;
import implementation.world.IndexMaster;
import implementation.world.indexers.ObjectIndexer;

public class Graphic2DFloorCoverFactory extends Parent implements GraphicalAsset, ObjectIndexer.Listener<Graphic2DFloor> {

	private Shape shape;
	
	public Graphic2DFloorCoverFactory(IndexMaster master) {
		
	}
	
	@Override
	public void onSpawn(Graphic2DFloor t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRemoval(Graphic2DFloor t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDrawingLayer() {
		// TODO Auto-generated method stub
		
	}
	
}
