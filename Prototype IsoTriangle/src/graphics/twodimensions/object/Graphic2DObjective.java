package graphics.twodimensions.object;

import core.artist.GraphicalObject;
import core.object.Entity;
import graphics.twodimensions.factory.JavaFXArtist2D;
import implementation.object.Objective;
import javafx.scene.Parent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Graphic2DObjective extends Parent implements GraphicalObject, Objective.ScoreListener {

	private Objective objective;
	private Text text;
	private Circle circle;
	
	public Graphic2DObjective() {
		text = new Text();
		text.setText("0");
		text.setFill(Color.RED);
		text.setFont(new Font(20));
		
		circle = new Circle();
		circle.setFill(null);
		circle.setStroke(Color.RED);
		
		this.getChildren().add(text);
		this.getChildren().add(circle);
		
	}
	
	@Override
	public void setPhysicRepresentation(Entity object) {
		if( ! (object instanceof Objective) ) {
			System.err.println("Error : " + this.getClass().getName() +
								" does not fit type " + object.getClass().getName());
			return;
		}
		
		objective = (Objective) object;
		text.setText( "" + objective.getScore() );
		text.setTranslateX(objective.getX() - 6);
		text.setTranslateY(objective.getY() + 7);
		circle.setRadius(objective.getRadius());
		circle.setTranslateX(objective.getX());
		circle.setTranslateY(objective.getY());

		text.setFill(JavaFXArtist2D.getFamilyColor(objective.getFamily()));
		circle.setStroke(JavaFXArtist2D.getFamilyColor(objective.getFamily()));
		
		objective.addScoreListener(this);
	}

	@Override
	public void setDrawingLayer() {
		this.toFront();
	}

	@Override
	public void onScore(int old_score, int new_score) {
		text.setText( "" + objective.getScore() );
	}

}
