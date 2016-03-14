package graphics.twodimensions.object;

import core.artist.GraphicalObject;
import core.object.Entity;
import implementation.object.Spawner;
import javafx.scene.Parent;

public class Graphic2DSpawner extends Parent implements GraphicalObject   {

	private Spawner spawner;
	
	@Override
	public void setPhysicRepresentation(Entity object) {
		if( ! (object instanceof Spawner) ) {
			System.err.println("Error : " + this.getClass().getName() +
								" does not fit type " + object.getClass().getName());
			return;
		}
		
		this.spawner = (Spawner) object;
		
			
	}

	@Override
	public void setDrawingLayer() {
		this.toFront();
	}

}
