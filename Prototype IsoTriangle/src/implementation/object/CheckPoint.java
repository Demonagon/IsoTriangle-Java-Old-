package implementation.object;

import java.util.List;

import core.artist.GraphicalObject;
import core.artist.PaintableEntity;
import core.object.Entity;
import implementation.world.IndexMaster;
import implementation.world.indexers.ObjectIndexer;
import util.math.MyMath;

public class CheckPoint extends MovingEntity implements PaintableEntity, EntityMoveListener {
	
	private GraphicalObject representation;
	private String checkpoint_id;
	private int family;
	
	public CheckPoint(IndexMaster index_master, double x, double y, double radius, int family, String cp_id) {
		super();
		setX(x);
		setY(y);
		setRadius(radius);
		
		this.family = family;
		
		this.checkpoint_id = cp_id;
		
		new EntityHooker(this, index_master);
		new ObjectiveHooker(this, index_master);
	}
	
	public String getCheckPointID() {
		return checkpoint_id;
	}

	public int getFamily() {
		return family;
	}
	
	public void setFamily(int family) {
		this.family = family;
	}
	
	@Override
	public void onMove(Entity entity, double old_x, double old_y, double old_a) {
		if( ! ( entity instanceof MovingEntity ) ) return;
		
		MovingEntity m = (MovingEntity) entity;
		
		if( MyMath.distance(m.getX(), m.getY(), getX(), getY()) <= m.getRadius() + this.getRadius() ) {
			if( ! ( m instanceof TaggedEntity ) ) return;
			TaggedEntity t = (TaggedEntity) m;
			t.setTag(checkpoint_id, new Boolean(true));
			m.removeMoveListener(this);
		}
	}

	@Override
	public void setGraphicalRepresentation(GraphicalObject representation) {
		this.representation = representation;
	}

	@Override
	public GraphicalObject getGraphicalRepresentation() {
		return representation;
	}
	
	public static class EntityHooker implements ObjectIndexer.Listener<TaggedEntity> {

		private CheckPoint main;
		
		public EntityHooker(CheckPoint main, IndexMaster index_master) {
			this.main = main;
			index_master.getTaggedIndexer().addListener(this);
			
			List<TaggedEntity> list = index_master.getTaggedIndexer().getList();
			for(TaggedEntity e : list)
				onSpawn(e);
		}
		
		@Override
		public void onSpawn(TaggedEntity t) {
			int t_family = (Integer) t.getTag("family");
			if( t_family != main.family ) return;
			if( ! ( t instanceof MovingEntity ) ) return;
			
			MovingEntity m = (MovingEntity) t;
			
			if( MyMath.distance(m.getX(), m.getY(), main.getX(), main.getY()) <= m.getRadius() + main.getRadius() )
				t.setTag(main.checkpoint_id, new Boolean(true));
			
			m.addMoveListener(main);
		}

		@Override
		public void onRemoval(TaggedEntity t) {}
		
	}
	
	public static class ObjectiveHooker implements ObjectIndexer.Listener<Objective> {

		private CheckPoint main;
		
		public ObjectiveHooker(CheckPoint main, IndexMaster index_master) {
			this.main = main;
			index_master.getObjectiveIndexer().addListener(this);
			
			List<Objective> list = index_master.getObjectiveIndexer().getList();
			for(Objective e : list)
				onSpawn(e);
		}
		
		@Override
		public void onSpawn(Objective b) {
			if( main.family != b.getFamily() ) return;
			b.addCheckPoint(main);
		}

		@Override
		public void onRemoval(Objective b) {}
		
	}

}
