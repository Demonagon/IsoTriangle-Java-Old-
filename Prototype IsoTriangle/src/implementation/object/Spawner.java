package implementation.object;

import core.world.WorldMaster;
import implementation.factory.MovingEntityFactory;

public class Spawner extends MovingEntity implements IterableEntity {
	public static final int basic_spawn_rate = 50;
	
	WorldMaster master;
	MovingEntityFactory factory;
	int clock_size;
	int clock_state;
	
	public Spawner(WorldMaster master, MovingEntityFactory factory, double x, double y) {
		this.master = master;
		this.factory = factory;
		this.setX(x);
		this.setY(y);
		this.clock_size = basic_spawn_rate;
		this.clock_state = 0;
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
}
