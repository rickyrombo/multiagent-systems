package rats.relogo

import rats.relogo.turtles.*;
import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.engine.environment.RunEnvironment;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import rats.ReLogoObserver;
import rats.relogo.scenario.*;

class UserObserver extends ReLogoObserver{

	/**
	 * Tell our city grid system the grid min coords and world size
	 * clear anything in the grid
	 * Set the shape of our rats and create two pairs on intersections
	 * (they fell through the grate into their environment, two pairs is sufficient to start)
	 * Initialize food every grate (no rats to have it all eaten)
	 * @return
	 */
	@Setup
	def setup() {
		CityGrid.setWorldHeight(worldHeight());
		CityGrid.setWorldWidth(worldWidth())
		CityGrid.setMinXcor(getMinPxcor());
		CityGrid.setMinYcor(getMinPycor());
		clearAll();
		setDefaultShape(Rat, "rabbit");
		setDefaultShape(Food, "plant")
		createRats(2){
			setLabel("Adam")
			setColor(blue());
			sex = Rat.Sex.MALE;
			setupMovement(it);
			age = random(1500)
			aggressivenessConstant = Math.max(0 , Math.min(1, randomNormal(0.5, 0.166666)));
		}
		createRats(2){
			setLabel("Eve")
			setColor(pink());
			sex = Rat.Sex.FEMALE;
			setupMovement(it);
			age = random(1500)
			aggressivenessConstant = Math.max(0 , Math.min(1, randomNormal(0.5, 0.166666)));
		}
		for (int i = 0; i < 6; i++){
			createFood(1){
				def intersection = CityGrid.getIntersection(i);
				setxy(intersection.x, intersection.y);
			}
		}
	}
	
	/**
	 * Initializes rat position and heading
	 * @param rat
	 */
	def setupMovement(Rat rat){
		def x = Math.round(randomXcor() - worldWidth()/6);
		def y = Math.round(randomYcor() - worldHeight()/4);
		def intersection = CityGrid.getIntersection(random(6));
		rat.setxy(intersection.x, intersection.y);
		rat.setHeading(random(4) * 90);
		return;
		if(random(2) == 0) {
			rat.setxy(x, CityGrid.getStreetY(random(2)))
			rat.setHeading(random(2) * 90);
		} else {
			rat.setxy(CityGrid.getAvenueX(random(3)), y);
			rat.setHeading(random(2) * 180);
		}
	}
	
	/**
	 * Ask rats to do their step
	 * Check for food spawn
	 * Count some statistics for analysis
	 * Check for end conditions
	 * @return
	 */
	@Go
	def go() {
		ask(rats()){
			step();
		}
		tick();
		if (ticks() % foodSourceSpawnFrequency == 0) {
			spawnFood();
		}
		ask(rats()){
			if(mode == Rat.Mode.MATE && actionTime == 10) {
				distancesFromFood << Math.abs(CityGrid.getAvenueXError(getXcor()) + CityGrid.getStreetYError(getYcor()));
				matingCount++;
			}
		}
		if (count(filter({ it.sex == Rat.Sex.MALE },rats())) == 0 || 
			count(filter({ it.sex == Rat.Sex.FEMALE }, rats())) == 0 || 
			ticks() >= 50000) 
		{
			RunEnvironment.getInstance().endRun();
		}
	}
	
	def distancesFromFood = [];
	def matingCount = 0;
	
	/**
	 * Used to grab for analysis
	 * @return
	 */
	def getAverageMatingDistanceFromFood() {
		if (matingCount == 0) {
			return 0;
		}
		return distancesFromFood.sum() / matingCount;
	}
	
	/**
	 * Spawns food at empty intersection
	 * @return
	 */
	def spawnFood() {
		def seed = random(6);
		def i = seed;
		def intersection = CityGrid.getIntersection(i);
		//!anyQ(filter({ intersection.x == it.getXcor() && intersection.y == it.getYcor() }, food()))
		def foodAtPatch = foodOn(patch(intersection.x, intersection.y));
		while (anyQ(foodAtPatch) && ++i % 6 != seed) {
			intersection = CityGrid.getIntersection(i % 6);
			foodAtPatch = foodOn(patch(intersection.x, intersection.y));
		}
		if (i == seed || i % 6 != seed) {
			this.createFood(1){
				setxy(intersection.x, intersection.y);
			}
		}
	}
}