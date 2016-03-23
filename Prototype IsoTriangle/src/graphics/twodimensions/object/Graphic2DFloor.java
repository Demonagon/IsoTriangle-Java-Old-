package graphics.twodimensions.object;

import implementation.object.Floor;
import core.artist.GraphicalObject;
import core.object.Entity;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Graphic2DFloor extends Rectangle implements GraphicalObject {

	@Override
	public void setPhysicRepresentation(Entity object) {
		if( ! ( object instanceof Floor ) ) {
			System.err.println("Error : " + this.getClass().getName() +
								" does not fit type " + object.getClass().getName());
			return;
		}
		
		Floor floor = (Floor) object;
		this.setTranslateX(floor.getXCenter() - floor.getWidth()/2);
		this.setTranslateY(floor.getYCenter() - floor.getHeight()/2);
		this.setWidth(floor.getWidth());
		this.setHeight(floor.getHeight());
		this.setRotate(-floor.getAngle());
		
		this.setStrokeWidth(4);
		this.setStroke(Color.BLACK);
		this.setFill(Color.LIGHTGRAY);
	}

	@Override
	public void setDrawingLayer() {
		this.toBack();
	}

}
