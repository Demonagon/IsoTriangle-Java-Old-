package graphics.twodimensions.object;

import core.artist.GraphicalObject;
import core.object.Entity;
import implementation.object.Spawner;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Graphic2DSpawner extends Parent implements GraphicalObject   {

	private Spawner spawner;
	private Text text;
	private Circle circle;
	
	public Graphic2DSpawner() {
		text = new Text();
		text.setText("S");
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
		if( ! (object instanceof Spawner) ) {
			System.err.println("Error : " + this.getClass().getName() +
								" does not fit type " + object.getClass().getName());
			return;
		}
		
		spawner = (Spawner) object;
		text.setTranslateX(spawner.getX() - 6);
		text.setTranslateY(spawner.getY() + 7);
		circle.setTranslateX(spawner.getX());
		circle.setTranslateY(spawner.getY());
	}

	@Override
	public void setDrawingLayer() {
		this.toFront();
	}

}
