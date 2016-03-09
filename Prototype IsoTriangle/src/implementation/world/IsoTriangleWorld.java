package implementation.world;

import java.util.LinkedList;
import java.util.List;

import core.artist.Artist;
import core.object.Entity;
import core.world.World;

public class IsoTriangleWorld implements World {
	private List<Entity> entities;
	
	private IsoTriangleWorldAnalyst analyst;
	
	public IsoTriangleWorld(Artist artist) {
		entities = new LinkedList<Entity>();
		analyst = new IsoTriangleWorldAnalyst(artist);
	}
	
	public void addEntity(Entity entity) {
		entities.add(entity);
		analyst.onSpawn(entity);
	}
	
	public void removeEntity(Entity entity) {
		entities.remove(entity);
		analyst.onRemoval(entity);
	}
	
	public IsoTriangleWorldAnalyst getAnalyst() {
		return analyst;
	}
}
