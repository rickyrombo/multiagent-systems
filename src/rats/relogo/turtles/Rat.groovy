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
import rats.relogo.scenario.*;

/**
 * Main intelligent agent
 */
class Rat extends ReLogoTurtle {

	public enum Sex {
		MALE, FEMALE
	}
	
	enum Mode {
		EXPLORE, EAT, MATE, FIGHT
	}
	
	enum Goal {
		FOOD, MATE
	}
	
	def mode = Mode.EXPLORE;
	def actionTime = 0;
	
	def final deathAge = 5000;
	def final hungerDeath = 100;
	def final maturityAge = 750;
	def final defaultActionTime = 10;
	
	def numFights = 0;
	def numMeals = 0;
	def numMates = 0;
	
	def actionPartner;
	
	def age = 0;
	def timeSinceLastMeal = 0;
	def aggressivenessConstant;
	def Sex sex;
	def Goal goal;
	
	/**
	 * First checks death
	 * Then checks for whether search goal is sex or food
	 * Then if explore, explore
	 * Check for action end conditions (IE is fight/eat/mate concluded?) and resolve as necessary
	 * @return
	 */
	def step() {
		if (age++ > deathAge || timeSinceLastMeal++ > hungerDeath){
			die();
			return;
		}
		if (hungerFactor >= sexualDesireFactor) {
			goal = Goal.FOOD;
		} else {
			goal = Goal.MATE;
		}
		if (mode == Mode.EXPLORE) {
			explore();
		} else if(mode == Mode.FIGHT && actionTime-- <= 0){
			resolveFight();
		} else if(mode == Mode.EAT && actionTime-- <= 0) {
			timeSinceLastMeal = 0;
			mode = Mode.EXPLORE;
		} else if(mode == Mode.MATE && actionTime-- == 0) {
			mode = Mode.EXPLORE;
			// each rat will spawn at least 1 but as many as 5 rats of the same gender as themselves
			// this is what rats are like in real life (though not necessarily ensuring two members of opposite sex, granted)
			hatchRats(random(4) + 1){
				age = 0;
				timeSinceLastMeal = 0;
			};
		}
	}
	
	/**
	 * Moves rat, check for other rats, and for food
	 * @return
	 */
	def explore() {
		forward(1);
		if (isOnIntersection()) {
			heading += random(3) * 90 - 90;
		}
		for (neighbor in neighbors()) {
			def neighborRats = neighbor.ratsOn(neighbor);
			if (goal == Goal.MATE){
				checkForMates(neighborRats);
			}
			if (aggressivenessFactor > aggressivenessThreshold) {
				def opponent = maxOneOf(neighborRats){ Rat rat -> rat.aggressivenessFactor };
				if (opponent){
					fight(opponent);
					opponent.fight(this);
				}
			}
		}
		if (goal == Goal.FOOD) {
			def food = first(foodHere());
			if (food) {
				eat(food);
			}
		}
	}
	
	/**
	 * Check physicality and run probability to determine winner
	 * Kill loser, spawn food
	 * @return
	 */
	def resolveFight(){
		if (random(physicalityFactor + actionPartner.physicalityFactor) > physicalityFactor){
			actionPartner.die();
			mode = Mode.EXPLORE;
		} else {
			hatchFood(1){Food f -> f.foodItems = 1};
			die();
			actionPartner.mode = Mode.EXPLORE;
		}
	}
	
	/**
	 * Find neihboring rats seeking mates
	 */
	def checkForMates(rats) {
		for (Rat rat in rats) {
			if (rat.sex != sex && rat.goal == Goal.MATE && rat.mode == Mode.EXPLORE) {
				mate(rat);
				rat.mate(this);
				setLabel("<3");
				break;
			}
		}
	}
	
	/**
	 * See proposal
	 * @return
	 */
	def getHungerFactor() {
		return timeSinceLastMeal / hungerDeath;
	}
	
	/**
	 * See proposal
	 * @return
	 */
	def getSexualDesireFactor() {
		return age < maturityAge ? 0 : age / deathAge;
	}
	
	/**
	 * See proposal
	 * @return
	 */
	def getAggressivenessFactor() {
		return (hungerFactor + aggressivenessConstant) / 2.0;
	}
	
	/**
	 * See proposal
	 * @return
	 */
	def getPhysicalityFactor() {
		return (age / deathAge + hungerFactor) / 2.0;
	}
	
	/**
	 * Check if the agent is on an intersection
	 * because we spawn starting rats on intersection,
	 * and because the intersections are all on whole pixels,
	 * and because rats move in whole pixels,
	 * can assume that error will be exactly 0 on top of intersection.
	 * This is used for turning
	 */
	def isOnIntersection() {
		def x_err = CityGrid.getAvenueXError(getXcor())
		def y_err = CityGrid.getStreetYError(getYcor())
		if (x_err == 0 && y_err == 0)
			return true;
		return false;
	}
	
	/**
	 * Mate with the given rat
	 * @param rat
	 * @return
	 */
	def mate(Rat rat) {
		actionPartner = rat;
		mode = Mode.MATE;
		actionTime = defaultActionTime;
		numMates++;
	}
	
	/**
	 * Fight the given rat
	 * @param rat
	 * @return
	 */
	def fight(Rat rat) {
		actionPartner = rat;
		mode = Mode.FIGHT;
		actionTime = defaultActionTime;
		numFights++;
	}
	
	/**
	 * Eat the food
	 * @param food
	 * @return
	 */
	def eat(Food food){
		if (--food.foodItems <= 0) {
			food.die();
		}
		mode = Mode.EAT;
		actionTime = defaultActionTime;
		numMeals++;
	}
}
