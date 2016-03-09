package implementation.world.listeners;

import core.object.Entity;
import core.world.WorldAnalyst;

public interface EntitySpawnAnalyst extends WorldAnalyst {
	public void onSpawn(Entity entity);
}
