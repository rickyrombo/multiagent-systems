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

class UserObserver extends ReLogoObserver{

	@Setup
	def setup() {
		clearAll();
		setDefaultShape(Rat, "rabbit");
		setDefaultShape(Food, "plant")
		createRats(5){
			setColor(blue());
			sex = Rat.Sex.MALE;
			setupMovement(it);
			aggressivenessConstant = Math.max(0 , Math.min(1, randomNormal(0.5, 0.166666)));
		}
		createRats(5){
			setColor(pink());
			sex = Rat.Sex.FEMALE;
			setupMovement(it);
			aggressivenessConstant = Math.max(0 , Math.min(1, randomNormal(0.5, 0.166666)));
		}
		for (int i = 0; i < 6; i++){
			createFood(1){
				def intersection = getIntersection(i);
				setxy(intersection.x, intersection.y);
			}
		}
	}
	
	def setupMovement(Rat rat){
		if(random(2) == 0) {
			rat.setxy(randomXcor(), getAvenueY(random(2)))
			rat.setHeading(random(2) * 90);
		} else {
			rat.setxy(getStreetX(random(3)), randomYcor());
			rat.setHeading(random(2) * 180);
		}
	}
	
	def getIntersection(i) {
		return [x: getStreetX(Math.floor(i/2)), y: getAvenueY(i % 2)];
	}
	
	def getStreetX(streetNumber) {
		return worldWidth() / 3.0 * (streetNumber) + getMinPxcor() + worldWidth() / 6.0;
	}
	
	def getAvenueY(avenueNumber) {
		return worldHeight() / 2.0 * (avenueNumber) + getMinPycor() + worldHeight() / 4.0;
	}
	
	@Go
	def go() {
		ask(rats()){
			step();
		}
		tick();
		if (ticks() % 10 == 0) {
			def seed = random(6);
			def i = seed;
			def intersection = getIntersection(i);
			//!anyQ(filter({ intersection.x == it.getXcor() && intersection.y == it.getYcor() }, food()))
			//foodOn(patch(intersection.x, intersection.y)) > 0
			while (anyQ(food()) && !anyQ(filter({ intersection.x == it.getXcor() && intersection.y == it.getYcor() && it }, food())) && ++i % 6 != seed) {
				intersection = getIntersection(i % 6);
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