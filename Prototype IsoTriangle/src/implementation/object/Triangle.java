package implementation.object;

import core.artist.GraphicalObject;
import core.artist.PaintableEntity;

public class Triangle extends MovingEntity implements IterableEntity, PaintableEntity {
	
	GraphicalObject representation;
	
	public Triangle() {
		super();
	}

	@Override
	public void setGraphicalRepresentation(GraphicalObject representation) {
		this.representation = representation;
	}

	@Override
	public GraphicalObject getGraphicalRepresentation() {
		return representation;
	}

	@Override
	public void iterate() {
		setAngle( this.getAngle() + 1 );
		notifyMove();
	}
}
