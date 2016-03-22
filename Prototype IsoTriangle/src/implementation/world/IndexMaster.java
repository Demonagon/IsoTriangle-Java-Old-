package implementation.world;

import implementation.object.Booster;
import implementation.object.Floor;
import implementation.object.IterableEntity;
import implementation.object.Objective;
import implementation.object.Square;
import implementation.object.TaggedEntity;
import implementation.world.indexers.ObjectIndexer;

public class IndexMaster {
	private ObjectIndexer<Booster> booster_indexer;
	private ObjectIndexer<Floor> floor_indexer;
	private ObjectIndexer<IterableEntity> iterable_indexer;
	private ObjectIndexer<Square> square_indexer;
	private ObjectIndexer<TaggedEntity> tagged_indexer;
	private ObjectIndexer<Objective> objective_indexer;
	
	public IndexMaster(IsoTriangleWorldAnalyst main_analyst) {
		booster_indexer = new ObjectIndexer<Booster>(Booster.class);
		floor_indexer = new ObjectIndexer<Floor>(Floor.class);
		iterable_indexer = new ObjectIndexer<IterableEntity>(IterableEntity.class);
		square_indexer = new ObjectIndexer<Square>(Square.class);
		tagged_indexer = new ObjectIndexer<TaggedEntity>(TaggedEntity.class);
		objective_indexer = new ObjectIndexer<Objective>(Objective.class);
		
		main_analyst.addAnalyst(booster_indexer);
		main_analyst.addAnalyst(floor_indexer);
		main_analyst.addAnalyst(iterable_indexer);
		main_analyst.addAnalyst(square_indexer);
		main_analyst.addAnalyst(tagged_indexer);
		main_analyst.addAnalyst(objective_indexer);
	}
	
	public ObjectIndexer<Booster> getBoostersIndexer() {
		return booster_indexer;
	}
	
	public ObjectIndexer<Floor> getFloorIndexer() {
		return floor_indexer;
	}
	
	public ObjectIndexer<IterableEntity> getIterableEntityIndexer() {
		return iterable_indexer;
	}
	
	public ObjectIndexer<Square> getSquareIndexer() {
		return square_indexer;
	}
	
	public ObjectIndexer<TaggedEntity> getTaggedIndexer() {
		return tagged_indexer;
	}
	
	public ObjectIndexer<Objective> getObjectiveIndexer() {
		return objective_indexer;
	}
}
