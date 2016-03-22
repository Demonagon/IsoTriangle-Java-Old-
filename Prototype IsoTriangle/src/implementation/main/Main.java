package implementation.main;

import graphics.twodimensions.factory.JavaFXArtist2D;
import graphics.twodimensions.loop.JavaFXGameLoop;
import implementation.object.Booster;
import implementation.object.CheckPoint;
import implementation.object.Floor;
import implementation.object.Objective;
import implementation.object.Spawner;
import implementation.object.Square;
import implementation.world.IndexMaster;
import implementation.world.IsoTriangleWorld;
import implementation.world.IsoTriangleWorldMaster;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		
		launch(args);
		//launch(args);
	}

	@Override
	public void start(Stage primary_stage) throws Exception {
		JavaFXArtist2D artist = new JavaFXArtist2D();
		IsoTriangleWorldMaster master = new IsoTriangleWorldMaster(artist);
		
		IsoTriangleWorld world = (IsoTriangleWorld) master.getWorld();
		
		JavaFXGameLoop game_loop = new JavaFXGameLoop(artist, world.getAnalyst().getIndexMaster());
		
		//MovingEntity entity1 = new Square(world.getAnalyst().getIndexMaster());
		//MovingEntity entity2 = new Square(world.getAnalyst().getIndexMaster());
		//MovingEntity entity3 = new Triangle();
		

		
		master.spawnEntity(new Floor(350, 350, 500, 500, 0));
		
		for(int x = 150; x < 650; x += 100)
			for(int y = 150; y < 650; y += 100) {
				Booster booster = new Booster();
				booster.setX(x);
				booster.setY(y);
				master.spawnEntity(booster);
			}
		//master.spawnEntity( new SquareSpawner(master, world.getAnalyst().getIndexMaster(), 150, 150) );
		
		IndexMaster index = world.getAnalyst().getIndexMaster();
		
		Square.SquareFactory alpha_factory = new Square.SquareFactory(master, index, Square.alpha_family);
		Square.SquareFactory beta_factory = new Square.SquareFactory(master, index, Square.beta_family);
		//Square.SquareFactory gamma_factory = new Square.SquareFactory(master, index, Square.gamma_family);

		master.spawnEntity( new Spawner(master, alpha_factory, 150, 150));
		master.spawnEntity( new Spawner(master, beta_factory, 550, 550));
		
		master.spawnEntity( new Objective(master, index, 450, 550, 12, Square.alpha_family) );
		master.spawnEntity( new Objective(master, index, 250, 150, 12, Square.beta_family) );
		
		master.spawnEntity( new CheckPoint(world.getAnalyst().getIndexMaster(), 150, 550, 12, Square.alpha_family, "cp1") );
		master.spawnEntity( new CheckPoint(world.getAnalyst().getIndexMaster(), 550, 150, 12, Square.alpha_family, "cp1") );
		/*world.addEntity(entity1);
		world.addEntity(entity2);
		//world.addEntity(entity3);
		
		entity1.setX(100);
		entity1.setY(100);
		entity1.setAngle(0);
		entity1.notifyMove();
		
		entity2.setX(100);
		entity2.setY(200);
		entity2.addXAcceleration(1);
		entity2.setAngle(0);
		entity2.notifyMove();
		
		/*entity3.setX(100);
		entity3.setY(300);
		entity3.setAngle(1);
		entity3.notifyMove();*/
		
		primary_stage.setTitle("IsoTriangle Prototype");
		primary_stage.setScene(new Scene(artist.getRoot(), 1000, 1000));
		primary_stage.show();
		
		game_loop.start();
		
		/*new Thread(new Runnable() {

			@Override
			public void run() {
				for(;;) {
					entity.setAngle( entity.getAngle() + 1);
					if( entity.getX() < 500 )
						entity.setX( entity.getX() + 1 );
					else
						entity.setX( entity.getX() - 1 );
					entity.notifyMove();
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {	e.printStackTrace(); }
				}
			}
		}).start();*/
	}

}
