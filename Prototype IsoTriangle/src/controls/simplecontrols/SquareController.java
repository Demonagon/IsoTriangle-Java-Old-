package controls.simplecontrols;

import graphics.twodimensions.object.Graphic2DBooster;
import graphics.twodimensions.object.Graphic2DSquare;
import implementation.object.Booster;
import implementation.object.Square;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class SquareController implements EventHandler<MouseEvent> {
	
	Graphic2DSquare graphical_square;
	Square square;
	
	public SquareController(Graphic2DSquare graphical_square, Square square) {
		this.graphical_square = graphical_square;
		this.square = square;

		graphical_square.addEventHandler(MouseEvent.MOUSE_ENTERED, this);
		graphical_square.addEventHandler(MouseEvent.MOUSE_EXITED, this);
		graphical_square.addEventHandler(MouseEvent.MOUSE_DRAGGED, this);
		graphical_square.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
	}
	
	@Override
	public void handle(MouseEvent event) {
		Booster current_booster = square.getCurrentBooster();
		
		if( current_booster == null ) return;
		
		Graphic2DBooster graphical_booster = (Graphic2DBooster) current_booster.getGraphicalRepresentation();
		event.copyFor(event.getSource(), graphical_booster);
	}
	
}
