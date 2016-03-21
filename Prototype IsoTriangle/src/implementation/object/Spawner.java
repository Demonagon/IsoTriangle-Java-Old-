package implementation.object;

import core.artist.GraphicalObject;
import core.artist.PaintableEntity;
import core.world.WorldMaster;
import implementation.factory.MovingEntityFactory;

public class Spawner extends MovingEntity implements IterableEntity, PaintableEntity {
	public static final int basic_spawn_rate = 50;
	
	WorldMaster master;
	MovingEntityFactory factory;
	int clock_size;
	int clock_state;
	GraphicalObject representation;
	
	public Spawner(WorldMaster master, MovingEntityFactory factory, double x, double y) {
		this.master = master;
		this.factory = factory;
		this.setX(x);
		this.setY(y);
		this.clock_size = basic_spawn_rate;
		this.clock_state = 0;
	}
	
	public void setSpawnClockState(int clock_state) {
		this.clock_state = clock_state;
	}
	
	public int getSpawnClockState() {
		return clock_state;
	}
	
	public void setSpawnRate(int spawn_rate) {
		this.clock_size = spawn_rate;
	}
	
	public int getSpawnRate() {
		return this.clock_size;
	}

	@Override
	public void iterate() {
		clock_state++;
		if( clock_state >= clock_size ) {
			clock_state = 0;
			MovingEntity spawn = factory.createEntity();
			spawn.setX(getX());
			spawn.setY(getY());
			master.spawnEntity(spawn);
		}
	}

	@Override
	public void setGraphicalRepresentation(GraphicalObject representation) {
		this.representation = representation;
	}

	@Override
	public GraphicalObject getGraphicalRepresentation() {
		// TODO Auto-generated method stub
		return null;
	}
}
