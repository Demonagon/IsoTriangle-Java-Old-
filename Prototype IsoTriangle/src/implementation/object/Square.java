package implementation.object;

import java.awt.geom.Point2D;

import core.artist.GraphicalObject;
import core.artist.PaintableEntity;
import core.object.Entity;
import core.world.WorldMaster;
import implementation.factory.MovingEntityFactory;
import implementation.world.IndexMaster;
import implementation.world.indexers.ObjectIndexer;

public class Square extends MovingEntity implements IterableEntity, PaintableEntity, TaggedEntity {

	public static final int alpha_family = 0;
	public static final int beta_family = 1;
	public static final int gamma_family = 2;
	public static final int delta_family = 3;
	public static final int epsilon_family = 4;
	
	public static final double basic_radius = 15;
	
	private GraphicalObject representation;
	private WorldMaster master;
	private TagManager tags;
	
	//Optimisations
	private Booster current_booster;
	private Floor current_floor;
	
	//Indexers
	private ObjectIndexer<Booster> booster_index;
	private ObjectIndexer<Floor> floor_index;
	private ObjectIndexer<Square> square_index;
	
	public Square(WorldMaster master, IndexMaster index_master, int family) {
		super();
		this.master = master;
		this.setRadius(basic_radius);
		tags = new TagManager();
		setTag("family", new Integer(family));
		current_booster = null;
		current_floor = null;
		
		booster_index = new IndexMaster.Accessor<Booster>(index_master).getIndexer(Booster.class);
		floor_index = new IndexMaster.Accessor<Floor>(index_master).getIndexer(Floor.class);
		square_index = new IndexMaster.Accessor<Square>(index_master).getIndexer(Square.class);
		
		addMoveListener( new BoosterLossDetector() );
		addMoveListener( new FloorLossDetector() );
	}

	@Override
	public void setGraphicalRepresentation(GraphicalObject representation) {
		this.representation = representation;
	}

	@Override
	public GraphicalObject getGraphicalRepresentation() {
		return representation;
	}
	
	public Booster getCurrentBooster() {
		if( current_booster != null )
			return current_booster;
		
		
		for(Booster b : booster_index.getList() )
			if( b.isUnderEntity(this) )
				return current_booster = b;
				
		return null;
	}
	
	public class BoosterLossDetector implements EntityMoveListener {

		@Override
		public void onMove(Entity entity, double old_x, double old_y, double old_a) {
			if( ! ( entity instanceof Square ) ) {
				System.err.println("Error : BoosterLossDetector can't work on class " + entity.getClass().getName() +
								   " (only Square)");
				return;
			}
			
			Square square = (Square) entity;
			if( square.getCurrentBooster() == null ) return;
			if( square.getCurrentBooster().isUnderEntity(square) ) return;
			
			square.current_booster = null;
		}
		
	}
	
	public Floor getCurrentFloor() {
		if( current_floor != null )
			return current_floor;
		
		for(Floor f : floor_index.getList())
			if( f.isIn(this.getX(), this.getY()) )
				return current_floor = f;
		
		return null;
	}
	
	public class FloorLossDetector implements EntityMoveListener {

		@Override
		public void onMove(Entity entity, double old_x, double old_y, double old_a) {
			if( ! ( entity instanceof Square ) ) {
				System.err.println("Error : FloorLossDetector can't work on class " + entity.getClass().getName() +
								   " (only Square)");
				return;
			}
			
			Square square = (Square) entity;
			if( square.getCurrentFloor() == null ) return;
			if( square.getCurrentFloor().isIn(square.getX(), square.getY()) ) return;
			
			square.current_floor = null;
		}
		
	}

	@Override
	public void iterate() {
		
		Floor f = getCurrentFloor();
			
		if( f == null ) {
			destroy();
			return;
		}
		
		boolean on_square = false;
		
		for(Square s : square_index.getList())
			if( s != this && new Point2D.Double(s.getX(), s.getY()).distance(getX(), getY()) <= this.getRadius()*2 ) {
				on_square = true;
				s.destroy();
				break;
			}
		
		if( on_square ) {
			destroy();
			return;
		}
		
		
		Booster b = getCurrentBooster();
		
		if( b != null ) {
			double new_a_x = Math.cos( Math.toRadians( b.getArrowAngle() ) )
							 * Booster.effect_on_squares;
			double new_a_y = Math.sin( Math.toRadians( b.getArrowAngle() ) )
					 		 * Booster.effect_on_squares;
			this.addXAcceleration(new_a_x);
			this.addYAcceleration(new_a_y);
		}
		
		this.applicAcceleration();
		
		notifyMove();
	}
		
	public void destroy() {
		master.destroyEntity(this);
	}
	
	@Override
	public Object getTag(String name) {
		return tags.getTag(name);
	}

	@Override
	public void setTag(String name, Object value) {
		tags.setTag(name, value);
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
