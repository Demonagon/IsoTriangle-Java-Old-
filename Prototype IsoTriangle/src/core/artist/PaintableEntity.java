package core.artist;

import core.object.Entity;

public interface PaintableEntity extends Entity {
	public void setGraphicalRepresentation(GraphicalObject representation);
	public GraphicalObject getGraphicalRepresentation();
}
