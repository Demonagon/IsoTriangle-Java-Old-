package implementation.world;

import java.util.LinkedList;
import java.util.List;

import core.artist.Artist;
import core.object.Entity;
import core.world.WorldAnalyst;
import implementation.world.listeners.EntityRemovalAnalyst;
import implementation.world.listeners.EntitySpawnAnalyst;

public class IsoTriangleWorldAnalyst implements EntityRemovalAnalyst, EntitySpawnAnalyst {
	//Listeners
	private List<EntityRemovalAnalyst> removal_analysts;
	private List<EntitySpawnAnalyst> spawn_analysts;
	
	//Gestionnaire graphique
	private GraphicWorldAnalyst graphic_analyst;
	
	//Gestionnaires d'index
	private IndexMaster index_master;
	private CatchMaster catch_master;
	
	//initialise l'ensemble des analystes avec l'artiste en paramètre relié par un analyste graphique
	public IsoTriangleWorldAnalyst(Artist artist) {
		removal_analysts = new LinkedList<EntityRemovalAnalyst>();
		spawn_analysts = new LinkedList<EntitySpawnAnalyst>();
		index_master = new IndexMaster(this);
		catch_master = new CatchMaster(this);
		
		graphic_analyst = new GraphicWorldAnalyst(catch_master, artist);
	}
	
	public GraphicWorldAnalyst getGraphicAnalyst() {
		return graphic_analyst;
	}
	
	public IndexMaster getIndexMaster() {
		return index_master;
	}
	
	public void addAnalyst(WorldAnalyst analyst) {
		if( analyst instanceof EntityRemovalAnalyst )
			removal_analysts.add((EntityRemovalAnalyst) analyst);
		if( analyst instanceof EntitySpawnAnalyst )
			spawn_analysts.add((EntitySpawnAnalyst) analyst);
	}
	
	public void removeAnalyst(WorldAnalyst analyst) {
		if( analyst instanceof EntityRemovalAnalyst )
			removal_analysts.remove((EntityRemovalAnalyst) analyst);
		if( analyst instanceof EntitySpawnAnalyst )
			spawn_analysts.remove((EntitySpawnAnalyst) analyst);
	}
	
	@Override
	public void onRemoval(Entity entity) {
		for(EntityRemovalAnalyst analyst : removal_analysts)
			analyst.onRemoval(entity);
	}

	@Override
	public void onSpawn(Entity entity) {
		for(EntitySpawnAnalyst analyst : spawn_analysts)
			analyst.onSpawn(entity);
	}
}
