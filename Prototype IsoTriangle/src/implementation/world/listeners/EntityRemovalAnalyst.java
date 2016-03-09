package implementation.world.listeners;

import core.object.Entity;
import core.world.WorldAnalyst;

public interface EntityRemovalAnalyst extends WorldAnalyst {
	public void onRemoval(Entity entity);
}
