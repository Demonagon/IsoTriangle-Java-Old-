package implementation.object;

import java.awt.geom.Point2D;

import core.artist.GraphicalObject;
import core.artist.PaintableEntity;
import core.world.WorldMaster;
import implementation.factory.MovingEntityFactory;
import implementation.world.IndexMaster;

public class Square extends MovingEntity implements IterableEntity, PaintableEntity {

	public static final int alpha_family = 0;
	public static final int beta_family = 1;
	public static final int gamma_family = 2;
	public static final int delta_family = 3;
	public static final int epsilon_family = 4;
	
	public static final double basic_radius = 12;
	
	private GraphicalObject representation;
	private WorldMaster master;
	private IndexMaster index_master;
	
	private int family;
	
	
	public Square(WorldMaster master, IndexMaster index_master, int family) {
		super();
		this.master = master;
		this.index_master = index_master;
		this.setRadius(basic_radius);
		this.family = family;
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
		
		boolean on_floor = false;
		
		for(Floor f : index_master.getFloorIndexer().getList())
			if( f.isIn(this.getX(), this.getY()) ) {
				on_floor = true;
				break;
			}
			
		if( ! on_floor ) {
			destroy();
			return;
		}
		
		boolean on_square = false;
		
		for(Square s : index_master.getSquareIndexer().getList())
			if( s != this && new Point2D.Double(s.getX(), s.getY()).distance(getX(), getY()) <= this.getRadius() ) {
				on_square = true;
				s.destroy();
				break;
			}
		
		if( on_square ) {
			destroy();
			return;
		}
		
		for(Booster b : index_master.getBoostersIndexer().getList()) {
			if( b.isUnderEntity(this) ) {
				double new_a_x = Math.cos( Math.toRadians( b.getArrowAngle() ) )
								 * Booster.effect_on_squares;
				double new_a_y = Math.sin( Math.toRadians( b.getArrowAngle() ) )
						 		 * Booster.effect_on_squares;
				this.addXAcceleration(new_a_x);
				this.addYAcceleration(new_a_y);
			}
		}
		
		this.applicAcceleration();
		
		notifyMove();
	}
		
	public void destroy() {
		master.destroyEntity(this);
	}
	
	public int getFamily() {
		return family;
	}
	
	public void setFamily(int family) {
		this.family = family;
	}
	
	public static class SquareFactory implements MovingEntityFactory {

		WorldMaster master;
		IndexMaster index;
		int family;
		
		public SquareFactory(WorldMaster master, IndexMaster index, int family) {
			this.master = master;
			this.index = index;
			this.family = family;
		}
		
		@Override
		public MovingEntity createEntity() {
			return new Square(master, index, family);
		}
		
	}

}
