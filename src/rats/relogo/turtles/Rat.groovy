package rats.relogo.turtles

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import rats.ReLogoTurtle;
import repast.simphony.relogo.Plural;
import repast.simphony.relogo.Stop;
import repast.simphony.relogo.Utility;
import repast.simphony.relogo.UtilityG;
import repast.simphony.relogo.schedule.Go;
import repast.simphony.relogo.schedule.Setup;

class Rat extends ReLogoTurtle {

	def age = 0;
	def timeSinceLastMeal = 0;
	def agressivenessConstant;
	
	def step() {
		age++;
		timeSinceLastMeal++;
	}
	
	def hungerFactor() {
		return timeSinceLastMeal / 1000;
	}
	
	def sexualDesireFactor() {
		return age < 25000 ? 0 : 0.5 + 0.00004 * age;
	}
	
}
