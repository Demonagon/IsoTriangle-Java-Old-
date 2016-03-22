package graphics.twodimensions.object;

import implementation.object.EntityMoveListener;
import implementation.object.Square;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import core.artist.GraphicalObject;
import core.object.Entity;
import graphics.twodimensions.factory.JavaFXArtist2D;

public class Graphic2DSquare extends Polygon implements GraphicalObject, EntityMoveListener {

	Square square;
	Transition transition;
	
	public Graphic2DSquare() {
		super();
		this.getPoints().addAll(new Double[]{
				-1.0, -1.0,
				-1.0, +1.0,
				+1.0, +1.0,
				+1.0, -1.0
		});
		//this.setScaleX(10);
		//this.setScaleY(10);
		this.setFill(Color.RED);
		this.setStroke(Color.BLACK);
		this.setStrokeWidth(0.1);
		this.setStrokeType(StrokeType.OUTSIDE);
		this.setSmooth(true);
		this.transition = null;
	}

	@Override
	public void setPhysicRepresentation(Entity object) {
		if( ! (object instanceof Square) ) {
			System.err.println("Error : " + this.getClass().getName() +
								" does not fit type " + object.getClass().getName());
			return;
		}
		
		this.square = (Square) object;
		this.setScaleX(square.getRadius());
		this.setScaleY(square.getRadius());
		this.setFill(JavaFXArtist2D.getFamilyColor((Integer) square.getTag("family")));
		
		square.addMoveListener(this);
		square.notifyMove();
	}

	@Override
	public void onMove(Entity entity, double old_x, double old_y, double old_a) {
		this.setTranslateX(square.getX());
		this.setTranslateY(square.getY());
		this.setRotate(square.getAngle());
	}
	
	@Override
	public String toString() {
		return "[" + this.getClass().getName() + "]";
	}

	@Override
	public void setDrawingLayer() {
		this.toFront();
	}

}
