package graphics.twodimensions.object;

import implementation.object.Booster;
import implementation.object.BoosterListener;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;
import controls.simplecontrols.BoosterController;
import core.artist.GraphicalObject;
import core.object.Entity;

public class Graphic2DBooster extends Parent implements GraphicalObject  {


	private Booster booster;
	private Graphic2DBoosterFrame frame;
	private Graphic2DBoosterArrow arrow;

	public Graphic2DBooster() {
		frame = new Graphic2DBoosterFrame();
		arrow = new Graphic2DBoosterArrow();
		
		this.getChildren().add(frame);
		this.getChildren().add(arrow);
	}

	@Override
	public void setPhysicRepresentation(Entity object) {
		if( ! (object instanceof Booster) ) {
			System.err.println("Error : " + this.getClass().getName() +
								" does not fit type " + object.getClass().getName());
			return;
		}
		
		booster = (Booster) object;
		frame.setTranslateX(booster.getX() - booster.getSquareSize() / 2);
		frame.setTranslateY(booster.getY() - booster.getSquareSize() / 2);
		frame.setWidth(booster.getSquareSize());
		frame.setHeight(booster.getSquareSize());
		
		arrow.setTranslateX(booster.getX());
		arrow.setTranslateY(booster.getY());
		arrow.setRotate(booster.getArrowAngle());
		
		booster.addListener(arrow);
		
		new BoosterController(this, booster);
	}

	@Override
	public void setDrawingLayer() {
		this.toFront();
	}
	
	public Graphic2DBoosterFrame getFrame() {
		return frame;
	}
	
	public static class Graphic2DBoosterFrame extends Rectangle {

		public static final double base_stroke_width = 4;
		
		public Graphic2DBoosterFrame() {
			this.setStroke(Color.BLACK);
			this.setFill(Color.WHITE);
			this.setStrokeWidth(base_stroke_width);
			this.toBack();
		}
		
	}
	
	public Graphic2DBoosterArrow getArrow() {
		return arrow;
	}
	
	public static class Graphic2DBoosterArrow extends Polygon implements BoosterListener {
		
		public static final double base_arrow_x_scale = 14 * 0.8;
		public static final double base_arrow_y_scale = 14;
		//public static final Duration base_arrow_rotation_time = new Duration(1000);
		public static final int rotation_animation_time = 200;
		public static final int apparition_animation_time = 300;
		
		private RotateTransition arrow_rotation;
		private ScaleTransition arrow_activation;
		
		public Graphic2DBoosterArrow() {
			this.setStroke(Color.BLACK);
			this.setStrokeType(StrokeType.INSIDE);
			this.setFill(Color.BLUE);
			this.setStrokeWidth(0.1);
			this.setScaleX(0);
			this.setScaleY(0);
			this.setSmooth(true);
			
			this.getPoints().addAll(new Double[]{
					+1.5, +0.0,
					+0.5, +1.0, 
					+0.5, +0.5,
					-1.5, +0.5,
					-1.5, -0.5,
					+0.5, -0.5,
					+0.5, -1.0,
			});
			
		}

		@Override
		public void onRotation(Booster booster, double old_angle, double new_angle) {
			//this.setRotate(new_angle);
			arrow_rotation = new RotateTransition();
			arrow_rotation.setFromAngle(old_angle);
			arrow_rotation.setToAngle(new_angle);
			arrow_rotation.setNode(this);
			arrow_rotation.setDuration(new Duration(rotation_animation_time));
			arrow_rotation.setInterpolator(Interpolator.EASE_BOTH);
			
			arrow_rotation.play();
		}

		@Override
		public void onActivation(boolean active) {
			arrow_activation = new ScaleTransition();
			arrow_activation.setNode(this);
			arrow_activation.setDuration(new Duration(apparition_animation_time));
			if(active) {
				arrow_activation.setFromX(0);
				arrow_activation.setFromY(0);
				arrow_activation.setToX(base_arrow_x_scale);
				arrow_activation.setToY(base_arrow_y_scale);
				//arrow_activation.setInterpolator(Interpolator.EASE_OUT);
			}
			else {
				arrow_activation.setFromX(base_arrow_x_scale);
				arrow_activation.setFromY(base_arrow_y_scale);
				arrow_activation.setToX(0);
				arrow_activation.setToY(0);
				//arrow_activation.setInterpolator(Interpolator.EASE_IN);
			}
			
			arrow_activation.play();
		}
		
		
	}

}
