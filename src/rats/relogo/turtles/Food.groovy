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

@Plural("Food")
class Food extends ReLogoTurtle {
	def foodItems = foodCapacity;
}
