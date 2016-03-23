package graphics.twodimensions.object;

import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import core.artist.GraphicalAsset;
import core.artist.GraphicalObject;
import core.object.Entity;
import implementation.world.IndexMaster;
import implementation.world.indexers.ObjectIndexer;

public class Graphic2DFloorCover extends Parent implements GraphicalAsset, ObjectIndexer.Listener<Graphic2DFloor> {

	private Shape shape;
	private ObjectIndexer<Graphic2DFloor> floor_indexer;
	
	public Graphic2DFloorCover(IndexMaster master) {
		floor_indexer = new IndexMaster.Accessor<Graphic2DFloor>(master).getIndexer(Graphic2DFloor.class);
		floor_indexer.addListener(this);
		resetShape();
	}
	
	public void actualiseShape() {
		this.getChildren().clear();
		shape.setStrokeWidth(4);
		shape.setStroke(Color.BLACK);
		shape.setFill(Color.LIGHTGRAY);
		this.getChildren().add(shape);
	}
	
	public void addShape(Shape s) {
		if( shape == null ) {
			shape = s;
			actualiseShape();
			return;
		}
		
		shape = Shape.union(shape, s);
		actualiseShape();
	}
	
	public void resetShape() {
		shape = null;
		for(Shape s : floor_indexer.getList())
			addShape(s);
	}
	
	@Override
	public void onSpawn(Graphic2DFloor t) {
		addShape(t);
	}

	@Override
	public void onRemoval(Graphic2DFloor t) {
		resetShape();
	}

	@Override
	public void setDrawingLayer() {
		this.toBack();
	}
	
}
