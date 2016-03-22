package graphics.twodimensions.object;

import core.artist.GraphicalObject;
import core.object.Entity;
import graphics.twodimensions.factory.JavaFXArtist2D;
import implementation.object.CheckPoint;
import implementation.object.Objective;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Graphic2DCheckPoint extends Parent implements GraphicalObject {

	private CheckPoint check_point;
	private Text text;
	private Circle circle;
	
	public Graphic2DCheckPoint() {
		text = new Text();
		text.setText("C");
		text.setFill(Color.RED);
		text.setFont(new Font(20));
		
		circle = new Circle();
		circle.setRadius(20);
		circle.setFill(null);
		circle.setStroke(Color.RED);
		
		this.getChildren().add(text);
		this.getChildren().add(circle);
		
	}
	
	@Override
	public void setPhysicRepresentation(Entity object) {
		if( ! (object instanceof CheckPoint ) ) {
			System.err.println("Error : " + this.getClass().getName() +
								" does not fit type " + object.getClass().getName());
			return;
		}
		
		check_point = (CheckPoint) object;
		text.setTranslateX(check_point.getX() - 6);
		text.setTranslateY(check_point.getY() + 7);
		circle.setTranslateX(check_point.getX());
		circle.setTranslateY(check_point.getY());

		text.setFill(JavaFXArtist2D.getFamilyColor(check_point.getFamily()));
		circle.setStroke(JavaFXArtist2D.getFamilyColor(check_point.getFamily()));
	}

	@Override
	public void setDrawingLayer() {
		this.toFront();
	}

}
