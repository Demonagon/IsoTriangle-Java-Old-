package implementation.object;

import java.util.List;

import core.object.Entity;
import core.world.WorldMaster;
import implementation.world.IndexMaster;
import implementation.world.indexers.ObjectIndexer;
import util.math.MyMath;

public class Objective extends MovingEntity implements EntityMoveListener, BoosterListener, TaggedEntity {
	
	private WorldMaster master;
	private int family;
	private int counter;
	
	public Objective(WorldMaster master, IndexMaster index_master, double x, double y, double radius, int family) {
		super();
		setX(x);
		setY(y);
		setRadius(radius);
		
		this.family = family;
		this.counter = 0;
		
		this.master = master;
		
		//Ces deux classes ramènent les nouveaux TaggedEntity et Booster depuis le flux principal d'informations et ajoutent cette classe comme listener
		new EntityHooker(this, index_master);
		new BoosterHooker(this, index_master);
	}

	@Override
	public void onMove(Entity entity, double old_x, double old_y, double old_a) {
		if( ! ( entity instanceof MovingEntity ) ) return;
		
		MovingEntity m = (MovingEntity) entity;
		
		if( MyMath.distance(m.getX(), m.getY(), getX(), getY()) <= m.getRadius() + this.getRadius() ) {
			master.destroyEntity(m);
			this.counter++;
			System.out.println(counter);
		}
	}

	@Override
	public int getTag() {
		return family;
	}

	@Override
	public void setTag(int tag) {
		this.family = tag;
	}

	@Override
	public void onRotation(Booster booster, double old_angle, double new_angle) {
		this.counter = 0;
	}

	@Override
	public void onActivation(boolean active) {
		this.counter = 0;
	}
	
	public static class EntityHooker implements ObjectIndexer.Listener<TaggedEntity> {

		private Objective main;
		
		public EntityHooker(Objective main, IndexMaster index_master) {
			this.main = main;
			index_master.getTaggedIndexer().addListener(this);
			
			List<TaggedEntity> list = index_master.getTaggedIndexer().getList();
			for(TaggedEntity e : list)
				onSpawn(e);
		}
		
		@Override
		public void onSpawn(TaggedEntity t) {
			if( t == this ) return;
			if( t.getTag() != main.getTag() ) return;
			if( ! ( t instanceof MovingEntity ) ) return;
			
			MovingEntity m = (MovingEntity) t;
			
			if( MyMath.distance(m.getX(), m.getY(), main.getX(), main.getY()) <= m.getRadius() + main.getRadius() ) {
				main.master.destroyEntity(m);
				main.counter++;
				System.out.println(main.counter);
			}
			else
				m.addMoveListener(main);
		}

		@Override
		public void onRemoval(TaggedEntity t) {}
		
	}
	
	public static class BoosterHooker implements ObjectIndexer.Listener<Booster> {

		private Objective main;
		
		public BoosterHooker(Objective main, IndexMaster index_master) {
			this.main = main;
			index_master.getBoostersIndexer().addListener(this);
			
			List<Booster> list = index_master.getBoostersIndexer().getList();
			for(Booster e : list)
				onSpawn(e);
		}
		
		@Override
		public void onSpawn(Booster b) {
			b.addListener(main);
		}

		@Override
		public void onRemoval(Booster b) {}
		
	}
	
	

}
