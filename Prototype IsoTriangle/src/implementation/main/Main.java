package implementation.main;

import core.artist.GraphicalAsset;
import core.artist.PaintableEntity;
import graphics.twodimensions.factory.JavaFXArtist2D;
import graphics.twodimensions.loop.JavaFXGameLoop;
import graphics.twodimensions.object.Graphic2DFloor;
import graphics.twodimensions.object.Graphic2DFloorCover;
import implementation.object.Booster;
import implementation.object.CheckPoint;
import implementation.object.Floor;
import implementation.object.IterableEntity;
import implementation.object.Objective;
import implementation.object.Spawner;
import implementation.object.Square;
import implementation.object.TaggedEntity;
import implementation.world.CatchMaster;
import implementation.world.IndexMaster;
import implementation.world.IsoTriangleWorld;
import implementation.world.IsoTriangleWorldMaster;
import implementation.world.catcher.ObjectCatcher;
import implementation.world.indexers.ObjectIndexer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {

		
		// -> wow !
		System.gc();
		
		launch(args);
	}

	@Override
	public void start(Stage primary_stage) throws Exception {
		JavaFXArtist2D artist = new JavaFXArtist2D();
		IsoTriangleWorldMaster master = new IsoTriangleWorldMaster(artist);
		artist.setWorldMaster(master);
		
		IsoTriangleWorld world = (IsoTriangleWorld) master.getWorld();

		CatchMaster catcher = world.getAnalyst().getCatchMaster();

		catcher.addCatcher(PaintableEntity.class, new ObjectCatcher<PaintableEntity>(PaintableEntity.class));
		catcher.addCatcher(GraphicalAsset.class, new ObjectCatcher<GraphicalAsset>(GraphicalAsset.class));

		IndexMaster index = world.getAnalyst().getIndexMaster();

		index.addIndexer(Booster.class, new ObjectIndexer<Booster>(Booster.class));
		index.addIndexer(Floor.class, new ObjectIndexer<Floor>(Floor.class));
		index.addIndexer(IterableEntity.class, new ObjectIndexer<IterableEntity>(IterableEntity.class));
		index.addIndexer(Square.class, new ObjectIndexer<Square>(Square.class));
		index.addIndexer(TaggedEntity.class, new ObjectIndexer<TaggedEntity>(TaggedEntity.class));
		index.addIndexer(Objective.class, new ObjectIndexer<Objective>(Objective.class));
		index.addIndexer(Graphic2DFloor.class, new ObjectIndexer<Graphic2DFloor>(Graphic2DFloor.class));
		
		world.getAnalyst().accessIndexers();
		
		JavaFXGameLoop game_loop = new JavaFXGameLoop(artist, world.getAnalyst().getIndexMaster());

		master.spawnEntity(new Graphic2DFloorCover(index));
		
		// Début des objets
		
		TestLevels.level_grand_quadruble(master, index);
		
		// Fin des objets
		
		primary_stage.setTitle("IsoTriangle Prototype");
		primary_stage.setScene(new Scene(artist.getRoot(), 1000, 1000));
		primary_stage.show();
		
		game_loop.start();
	}

}
