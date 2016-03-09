package graphics.twodimensions.object;

import implementation.object.EntityMoveListener;
import implementation.object.Triangle;
import javafx.animation.Transition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.StrokeType;
import core.artist.GraphicalObject;
import core.object.Entity;

public class Graphic2DTriangle extends Polygon implements GraphicalObject, EntityMoveListener {
	
	Triangle triangle;
	Transition transition;
	
	public Graphic2DTriangle() {
		super();
		/*this.getPoints().addAll(new Double[]{
				MyMath.getTriangleAx(), MyMath.getTriangleAy(),
				MyMath.getTriangleBx(), MyMath.getTriangleBy(),
				MyMath.getTriangleCx(), MyMath.getTriangleCy()
		});*/
		this.getPoints().addAll(new Double[]{
				0.0, 0.0,
				1.0, 1.0,
				1.0, 0.0
		});
		this.setScaleX(20);
		this.setScaleY(20);
		this.setFill(Color.RED);
		this.setStroke(Color.BLACK);
		this.setStrokeType(StrokeType.INSIDE);
		this.setSmooth(false);
		this.transition = null;
	}

	@Override
	public void setPhysicRepresentation(Entity object) {
		if( ! (object instanceof Triangle) ) {
			System.err.println("Error : " + this.getClass().getName() +
					" does not fit type " + object.getClass().getName());
			return;
		}
		
		this.triangle = (Triangle) object;
		this.triangle.addMoveListener(this);
		this.triangle.notifyMove();
	}

	@Override
	public void onMove(Entity entity, double old_x, double old_y, double old_a) {
		this.setTranslateX(triangle.getX());
		this.setTranslateY(triangle.getY());
		this.setRotate(triangle.getAngle());
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
