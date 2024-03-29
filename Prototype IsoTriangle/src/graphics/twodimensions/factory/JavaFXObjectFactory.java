package graphics.twodimensions.factory;

import core.artist.GraphicalCorrespondanceFactory;
import core.artist.GraphicalObject;
import core.object.Entity;
import graphics.twodimensions.object.Graphic2DBooster;
import graphics.twodimensions.object.Graphic2DCheckPoint;
import graphics.twodimensions.object.Graphic2DFloor;
import graphics.twodimensions.object.Graphic2DObjective;
import graphics.twodimensions.object.Graphic2DSpawner;
import graphics.twodimensions.object.Graphic2DSquare;
import graphics.twodimensions.object.Graphic2DTriangle;
import implementation.object.Booster;
import implementation.object.CheckPoint;
import implementation.object.Floor;
import implementation.object.Objective;
import implementation.object.Spawner;
import implementation.object.Square;
import implementation.object.Triangle;

public class JavaFXObjectFactory implements GraphicalCorrespondanceFactory {

	@Override
	public GraphicalObject createCorrespondant(Entity e) {
		if( e instanceof Triangle )
			return new Graphic2DTriangle();
		if( e instanceof Square )
			return new Graphic2DSquare();
		if( e instanceof Booster )
			return new Graphic2DBooster();
		if( e instanceof Floor )
			return new Graphic2DFloor();
		if( e instanceof Spawner )
			return new Graphic2DSpawner();
		if( e instanceof Objective )
			return new Graphic2DObjective();
		if( e instanceof CheckPoint )
			return new Graphic2DCheckPoint();
		System.err.println(this.getClass().getName() + " : Class " + e.getClass().getName() +
						   " does not have a defined graphic match");
		return null;
	}

}
