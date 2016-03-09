package implementation.world;

import core.artist.Artist;
import core.object.Entity;
import core.world.World;
import core.world.WorldMaster;

public class IsoTriangleWorldMaster implements WorldMaster {

	IsoTriangleWorld world;
	
	public IsoTriangleWorldMaster(Artist artist) {
		setWorld(new IsoTriangleWorld(artist));
	}
	
	public IsoTriangleWorldMaster(IsoTriangleWorld world) {
		setWorld(world);
	}
	
	@Override
	public void setWorld(World world) {
		if( ! (world instanceof IsoTriangleWorld) ) {
			System.err.println("Error : IsoTriangleWorldMaster can't manage worlds of type "
								+ world.getClass().getName());
			return;
		}
		
		this.world = (IsoTriangleWorld) world;
	}
	
	@Override
	public World getWorld() {
		return world;
	}

	@Override
	public void spawnEntity(Entity entity) {
		if( world == null ) return;
		world.addEntity(entity);
	}

	@Override
	public void destroyEntity(Entity entity) {
		if( world == null ) return;
		world.removeEntity(entity);
	}
}
