package rats.relogo

import rats.relogo.turtles.*;
import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;
import rats.ReLogoObserver;
import rats.relogo.scenario.*;

class UserObserver extends ReLogoObserver{

	@Setup
	def setup() {
		clearAll();
		setDefaultShape(Rat, "rabbit");
		setDefaultShape(Food, "plant")
		createRats(1){
			setLabel("Adam")
			setColor(blue());
			sex = Rat.Sex.MALE;
			setupMovement(it);
			age = random(1500)
			aggressivenessConstant = Math.max(0 , Math.min(1, randomNormal(0.5, 0.166666)));
		}
		createRats(1){
			setLabel("Eve")
			setColor(pink());
			sex = Rat.Sex.FEMALE;
			setupMovement(it);
			age = random(1500)
			aggressivenessConstant = Math.max(0 , Math.min(1, randomNormal(0.5, 0.166666)));
		}
		for (int i = 0; i < 6; i++){
			createFood(1){
				def intersection = CityGrid.getIntersection(i, worldWidth(), worldHeight(), getMinPxcor(), getMinPycor());
				setxy(intersection.x, intersection.y);
			}
		}
	}
	
	def setupMovement(Rat rat){
		def x = Math.round(randomXcor() - worldWidth()/6);
		def y = Math.round(randomYcor() - worldHeight()/4);
		def intersection = CityGrid.getIntersection(random(6), worldWidth(), worldHeight(), getMinPxcor(), getMinPycor());
		rat.setxy(intersection.x, intersection.y);
		rat.setHeading(random(4) * 90);
		return;
		if(random(2) == 0) {
			rat.setxy(x, CityGrid.getStreetY(random(2), worldHeight(), getMinPycor()))
			rat.setHeading(random(2) * 90);
		} else {
			rat.setxy(CityGrid.getAvenueX(random(3), worldWidth(), getMinPxcor()), y);
			rat.setHeading(random(2) * 180);
		}
	}
	
	@Go
	def go() {
		ask(rats()){
			step();
		}
		tick();
		if (ticks() % foodSourceSpawnFrequency == 0) {
			def seed = random(6);
			def i = seed;
			def intersection = CityGrid.getIntersection(i, worldWidth(), worldHeight(), getMinPxcor(), getMinPycor());
			//!anyQ(filter({ intersection.x == it.getXcor() && intersection.y == it.getYcor() }, food()))
			def foodAtPatch = foodOn(patch(intersection.x, intersection.y));
			while (anyQ(foodAtPatch) && ++i % 6 != seed) {
				intersection = CityGrid.getIntersection(i % 6, worldWidth(), worldHeight(), getMinPxcor(), getMinPycor());
				foodAtPatch = foodOn(patch(intersection.x, intersection.y));
			}
			if (i == seed || i % 6 != seed) {
				this.createFood(1){
					setxy(intersection.x, intersection.y);
				}
			}
		}
	}
	
	/**
	 * Add observer methods here. For example:

		@Setup
		def setup(){
			clearAll()
			createTurtles(10){
				forward(random(10))
			}
		}
		
	 *
	 * or
	 * 	
	
		@Go
		def go(){
			ask(turtles()){
				left(random(90))
				right(random(90))
				forward(random(10))
			}
		}

	 */

}