package controls.simplecontrols;


import implementation.object.Booster;
import graphics.twodimensions.object.Graphic2DBooster;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class BoosterController implements EventHandler<MouseEvent> {

	Graphic2DBooster graphical_booster;
	Booster booster;
	
	public BoosterController(Graphic2DBooster graphical_booster, Booster booster) {
		this.graphical_booster = graphical_booster;
		this.booster = booster;

		graphical_booster.addEventHandler(MouseEvent.MOUSE_ENTERED, this);
		graphical_booster.addEventHandler(MouseEvent.MOUSE_EXITED, this);
		graphical_booster.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
		graphical_booster.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
	}
	
	@Override
	public void handle(MouseEvent event) {
		if( event.getEventType() == MouseEvent.MOUSE_ENTERED )
			handleEntered(event);
		else if( event.getEventType() == MouseEvent.MOUSE_EXITED )
			handleExited(event);
		else if( event.getEventType() == MouseEvent.MOUSE_PRESSED )
			handlePress(event);
		else if( event.getEventType() == MouseEvent.MOUSE_DRAGGED )
			handleDrag(event);
		else if( event.getEventType() == MouseEvent.MOUSE_RELEASED )
			handleRelease(event);
		else if( event.getEventType() == MouseEvent.MOUSE_CLICKED )
			handleClick(event);
	}
	
	public void handleEntered(MouseEvent event) {
	}
	
	public void handleExited(MouseEvent event) {
	}
	
	public void handlePress(MouseEvent event) {
		
	}

	public void handleDrag(MouseEvent event) {
		if( ! booster.isActive() )
			return;
		
		//Point2D origin = graphical_booster.localToScene(new Point2D(graphical_booster.getTranslateX(), graphical_booster.getTranslateY()));
		
		Point2D origin = new Point2D(booster.getX(), booster.getY());
		
		//Point2D origin = graphical_booster.getBoundsInParent().g
		
		double current_x = event.getSceneX();
		double current_y = event.getSceneY();
		
		double angle = Math.atan2(current_y - origin.getY(), current_x - origin.getX());
		
		booster.setArrowAngle(Math.toDegrees(angle));
	}
	
	public void handleRelease(MouseEvent event) {
		
	}
	
	public void handleClick(MouseEvent event) {
		if( event.getButton() != MouseButton.SECONDARY ) return;
		
		if( booster.isActive() )
			booster.setActive(false);
		else
			booster.setActive(true);
	}
}
