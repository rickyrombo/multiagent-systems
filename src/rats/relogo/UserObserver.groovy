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
			setxy(randomXcor(), randomYcor());
			setColor(blue());
			sex = Rat.Sex.MALE;
			aggressivenessConstant = Math.max(0 , Math.min(1, randomNormal(0.5, 0.166666)));
		}
		createRats(5){
			setColor(pink());
			setxy(randomXcor(), randomYcor());
			sex = Rat.Sex.FEMALE;
			aggressivenessConstant = Math.max(0 , Math.min(1, randomNormal(0.5, 0.166666)));
		}
		for (int i = 0; i < 6; i++){
			createFood(1){
				def xFactor = Math.floor(i/2) + 1;
				def yFactor = i%2 + 1;
				def x = worldWidth() / 3.0 * xFactor + getMinPxcor() + worldWidth() / 6.0;
				def y = worldHeight() / 2.0 * yFactor + getMinPycor() + worldHeight() / 4.0;
				setxy(x, y);
			}
		}
	}
	
	@Go
	def go() {
		ask(rats()){
			step();
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