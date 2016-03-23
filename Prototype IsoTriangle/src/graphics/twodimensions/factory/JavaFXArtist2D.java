package graphics.twodimensions.factory;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import core.artist.Artist;
import core.artist.GraphicalAsset;
import core.artist.GraphicalObject;
import core.artist.PaintableEntity;
import core.world.WorldMaster;
import implementation.object.Square;

public class JavaFXArtist2D extends Artist {

	private Group root;
	private WorldMaster master;

	public JavaFXArtist2D() {
		super(new JavaFXObjectFactory());
		root = new Group();
	}
	
	public void setWorldMaster(WorldMaster master) {
		this.master = master;
	}

	@Override
	public void paintObject(PaintableEntity entity) {
		GraphicalObject o = this.addGraphics(entity);
		master.spawnEntity(o);
	}

	@Override
	public void eraseObject(PaintableEntity entity) {
		GraphicalObject o = entity.getGraphicalRepresentation();
		master.destroyEntity(o);
	}
	
	public Group getRoot() {
		return root;
	}
	
	public static Color getFamilyColor(int family) {
		switch(family) {
			case Square.alpha_family : return Color.BLUE;
			case Square.beta_family : return Color.RED;
			case Square.gamma_family : return Color.GREEN;
			case Square.delta_family : return Color.YELLOW;
			case Square.epsilon_family : return Color.ORANGE;
			default : return Color.CHOCOLATE;
		}
	}

	@Override
	public void addAsset(GraphicalAsset o) {
		if( ! ( o instanceof Node ) ) {
			System.err.println("Error : invalid graphic object type " +
								o.getClass().getName() );
			return;
		}
		
		Node n = (Node) o;
		root.getChildren().add(n);
		o.setDrawingLayer();
	}

	@Override
	public void removeAsset(GraphicalAsset o) {
		if( ! ( o instanceof Node ) ) {
			System.err.println("Error : invalid graphic object type " +
								o.getClass().getName() );
			return;
		}
		
		Node n = (Node) o;
		root.getChildren().remove(n);
	}

}
