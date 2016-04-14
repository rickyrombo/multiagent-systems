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
			hatchRats(random(4) + 1){
				age = 0;
				timeSinceLastMeal = 0;
			};
		}
	}
	
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
	
	def getHungerFactor() {
		return timeSinceLastMeal / hungerDeath;
	}
	
	def getSexualDesireFactor() {
		return age < maturityAge ? 0 : age / deathAge;
	}
	
	def getAggressivenessFactor() {
		return (hungerFactor + aggressivenessConstant) / 2.0;
	}
	
	def getPhysicalityFactor() {
		return (age / deathAge + hungerFactor) / 2.0;
	}
	
	def isOnIntersection() {
		def x_err = CityGrid.getAvenueXError(getXcor())
		def y_err = CityGrid.getStreetYError(getYcor())
		if (x_err == 0 && y_err == 0)
			return true;
		return false;
	}
	
	def mate(Rat rat) {
		actionPartner = rat;
		mode = Mode.MATE;
		actionTime = defaultActionTime;
		numMates++;
	}
	
	def fight(Rat rat) {
		actionPartner = rat;
		mode = Mode.FIGHT;
		actionTime = defaultActionTime;
		numFights++;
	}
	
	def eat(Food food){
		if (--food.foodItems <= 0) {
			food.die();
		}
		mode = Mode.EAT;
		actionTime = defaultActionTime;
		numMeals++;
	}
}
