package core.artist;

public abstract class Artist {
	GraphicalCorrespondanceFactory factory;
	
	public Artist(GraphicalCorrespondanceFactory factory) {
		setFactory(factory);
	}

	public GraphicalObject addGraphics(PaintableEntity entity) {
		GraphicalObject o = factory.createCorrespondant(entity);

		if( o == null ) {
			System.err.println("Error : no graphic correspondant for "
								+ entity.getClass().getName()
								+ " in factory "
								+ factory.getClass().getName());
			return null;
		}

		entity.setGraphicalRepresentation(o);
		o.setPhysicRepresentation(entity);
		
		return o;
	}
	
	public void setFactory(GraphicalCorrespondanceFactory factory) {
		this.factory = factory;
	}
	
	public abstract void paintObject(PaintableEntity entity);
	public abstract void eraseObject(PaintableEntity entity);
	public abstract void addAsset(GraphicalAsset entity);
	public abstract void removeAsset(GraphicalAsset entity);
}
