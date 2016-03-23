package implementation.object;

import java.util.LinkedList;
import java.util.List;

import core.artist.GraphicalObject;
import core.artist.PaintableEntity;
import core.object.Entity;
import core.world.WorldMaster;
import implementation.world.IndexMaster;
import implementation.world.indexers.ObjectIndexer;
import util.math.MyMath;

public class Objective extends MovingEntity implements PaintableEntity, EntityMoveListener, BoosterListener {
	
	private WorldMaster master;
	private int counter;
	private GraphicalObject representation;
	private int family;
	private List<CheckPoint> related_checkpoints;
	
	private List<ScoreListener> score_listeners;
	
	public Objective(WorldMaster master, IndexMaster index_master, double x, double y, double radius, int family) {
		super();
		setX(x);
		setY(y);
		setRadius(radius);
		
		this.counter = 0;
		this.family = family;
		
		related_checkpoints = new LinkedList<CheckPoint>();
		
		this.master = master;
		
		score_listeners = new LinkedList<ScoreListener>();
		
		//Ces deux classes ramènent les nouveaux TaggedEntity et Booster depuis le flux principal d'informations et ajoutent cette classe comme listener
		new EntityHooker(this, index_master);
		new BoosterHooker(this, index_master);
	}
	
	public void onContact(MovingEntity entity) {
		if( ! ( entity instanceof TaggedEntity ) ) return;
		TaggedEntity t = (TaggedEntity) entity;
		
		for( CheckPoint cp : related_checkpoints )
			if( t.getTag(cp.getCheckPointID()) == null ) return;
		
		master.destroyEntity(entity);
		updateScore(counter + 1);
	}

	@Override
	public void onMove(Entity entity, double old_x, double old_y, double old_a) {
		if( ! ( entity instanceof MovingEntity ) ) return;
		
		MovingEntity m = (MovingEntity) entity;
		
		if( MyMath.distance(m.getX(), m.getY(), getX(), getY()) <= m.getRadius() + this.getRadius() )
			onContact(m);
	}
	
	public int getFamily() {
		return family;
	}
	
	public void setFamily(int family) {
		this.family = family;
	}

	@Override
	public void onRotation(Booster booster, double old_angle, double new_angle) {
		updateScore(0);
	}

	@Override
	public void onActivation(boolean active) {
		updateScore(0);
	}
	
	public void addScoreListener(ScoreListener l) {
		score_listeners.add(l);
	}
	
	public void updateScore(int new_score) {
		int old_score = counter;
		counter = new_score;
		for(ScoreListener l : score_listeners)
			l.onScore(old_score, new_score);
	}
	
	public int getScore() {
		return counter;
	}

	@Override
	public void setGraphicalRepresentation(GraphicalObject representation) {
		this.representation = representation;
	}

	@Override
	public GraphicalObject getGraphicalRepresentation() {
		return representation;
	}
	
	public void addCheckPoint(CheckPoint cp) {
		related_checkpoints.add(cp);
	}
	
	public static class EntityHooker implements ObjectIndexer.Listener<TaggedEntity> {

		private Objective main;
		
		public EntityHooker(Objective main, IndexMaster index_master) {
			this.main = main;
			
			ObjectIndexer<TaggedEntity> indexer = new IndexMaster.Accessor<TaggedEntity>(index_master)
														.getIndexer(TaggedEntity.class);
			
			indexer.addListener(this);
			
			List<TaggedEntity> list = indexer.getList();
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
				main.onContact(m);
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
			
			ObjectIndexer<Booster> booster_indexer = new IndexMaster.Accessor<Booster>(index_master).getIndexer(Booster.class);
			
			booster_indexer.addListener(this);
			
			List<Booster> list = booster_indexer.getList();
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
	
	public interface ScoreListener {
		public void onScore(int old_score, int new_score);
	}

}
