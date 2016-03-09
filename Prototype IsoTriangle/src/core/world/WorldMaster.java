package core.world;

import core.object.Entity;

public interface WorldMaster {
	public void setWorld(World world);
	public World getWorld();
	
	public void spawnEntity(Entity entity);
	public void destroyEntity(Entity entity);
}
