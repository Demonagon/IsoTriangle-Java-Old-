package graphics.twodimensions.factory;

import core.artist.GraphicalCorrespondanceFactory;
import core.artist.GraphicalObject;
import core.object.Entity;
import graphics.twodimensions.object.Graphic2DBooster;
import graphics.twodimensions.object.Graphic2DFloor;
import graphics.twodimensions.object.Graphic2DSquare;
import graphics.twodimensions.object.Graphic2DTriangle;
import implementation.object.Booster;
import implementation.object.Floor;
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
		return null;
	}

}
