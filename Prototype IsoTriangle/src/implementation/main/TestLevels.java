package implementation.main;

import graphics.twodimensions.object.Graphic2DFloorCover;
import implementation.object.Booster;
import implementation.object.CheckPoint;
import implementation.object.Floor;
import implementation.object.Objective;
import implementation.object.Spawner;
import implementation.object.Square;
import implementation.world.IndexMaster;
import core.world.WorldMaster;

public abstract class TestLevels {
	public static void level_diago_mauvais(WorldMaster master, IndexMaster index) {
		master.spawnEntity(new Floor(250, 250, 560, 560, 45));
		master.spawnEntity(new Floor(500, 500, 200, 200, 45));
		
		for(int x = 150; x < 650; x += 100)
			for(int y = 150; y < 650; y += 100) {
				Booster booster = new Booster();
				booster.setX(x);
				booster.setY(y);
				master.spawnEntity(booster);
			}
		
		Square.SquareFactory alpha_factory = new Square.SquareFactory(master, index, Square.alpha_family);
		Square.SquareFactory beta_factory = new Square.SquareFactory(master, index, Square.beta_family);

		master.spawnEntity( new Spawner(master, alpha_factory, 150, 150));
		master.spawnEntity( new Spawner(master, beta_factory, 550, 550));
		
		master.spawnEntity( new Objective(master, index, 450, 550, 20, Square.alpha_family) );
		master.spawnEntity( new Objective(master, index, 250, 150, 20, Square.beta_family) );
		
		master.spawnEntity( new CheckPoint(index, 150, 550, 20, Square.alpha_family, "cp1") );
		master.spawnEntity( new CheckPoint(index, 550, 150, 20, Square.alpha_family, "cp2") );
	}
	
	public static void level_virage_simple(WorldMaster master, IndexMaster index) {
		master.spawnEntity(new Floor(525, 275, 846, 350, 0));
		master.spawnEntity(new Floor(275, 525, 350, 850, 0));
		
		for(int x = 175; x < 950; x += 100)
			for(int y = 175; y < 950; y += 100) {
				if( x > 400 && y > 400 ) continue;
				Booster booster = new Booster();
				booster.setX(x);
				booster.setY(y);
				master.spawnEntity(booster);
			}
		
		Square.SquareFactory alpha_factory = new Square.SquareFactory(master, index, Square.alpha_family);

		master.spawnEntity( new Spawner(master, alpha_factory, 275, 775));
		master.spawnEntity( new Objective(master, index, 775, 275, 20, Square.alpha_family) );
	}
}
