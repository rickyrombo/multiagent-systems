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
	def mateTime = 0;
	def fightTime = 0;
	def eatTime = 0;
	
	def defaultActionTime = 10;
	def actionPartner;
	
	def age = 0;
	def timeSinceLastMeal = 0;
	def aggressivenessConstant;
	def Sex sex;
	def Goal goal;
	
	def step() {
		if (age++ > 50000 || timeSinceLastMeal++ > 1000){
			die();
		}
		if (hungerFactor() >= sexualDesireFactor()) {
			goal = Goal.FOOD;
		} else {
			goal = Goal.MATE;
		}
		if (mode == Mode.EXPLORE) {
			explore();
		} else if(mode == Mode.FIGHT && fightTime-- == 0){
			resolveFight();
		} else if(mode == Mode.EAT && eatTime-- == 0) {
			timeSinceLastMeal = 0;
			mode = Mode.EXPLORE;
		} else if(mode == Mode.MATE && mateTime-- == 0) {
			hatchRats(random(4));
			mode = Mode.EXPLORE;
		}
	}
	
	def explore() {
		forward(1);
		for (neighbor in neighbors()) {
			def neighborRats = neighbor.ratsOn(neighbor);
			if (goal == Goal.MATE){
				checkForMates(neighborRats);
			}
			if (aggressivenessFactor() > aggressivenessThreshold) {
				def opponent = maxOneOf(neighborRats){ Rat rat -> rat.aggressivenessFactor() };
				if (opponent){
					fight(opponent);
					opponent.fight(this);
				}
			}
		}
		if (goal == Goal.FOOD) {
			for (neighbor in neighbors()) {
				def food = filter({
					Food f -> f.foodItems > f.ratsEating
				}, foodOn(neighbor));
				if (food) {
					eat(food);
				}
			}
		}
	}
	
	def resolveFight(){
		if (random(physicalityFactor() + actionPartner.physicalityFactor()) > physicalityFactor()){
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
			if (rat.sex != sex && rat.goal == Goal.SEX && rat.mode == Mode.EXPLORE) {
				mate(rat);
				rat.mate(this);
				break;
			}
		}
	}
	
	def hungerFactor() {
		return timeSinceLastMeal / 1000.0;
	}
	
	def sexualDesireFactor() {
		return age < 25000 ? 0 : 0.5 + 0.00004 * age;
	}
	
	def aggressivenessFactor() {
		return (hungerFactor() + aggressivenessConstant) / 2.0;
	}
	
	def physicalityFactor() {
		return (age / 50000 + hungerFactor()) / 2.0;
	}
	
	def mate(Rat rat) {
		actionPartner = rat;
		mode = Mode.MATE;
		mateTime = defaultActionTime;
	}
	
	def fight(Rat rat) {
		actionPartner = rat;
		mode = Mode.FIGHT;
		fightTime = defaultActionTime;
	}
	
	def eat(Food food){
		if (food.foodItems-- == 0) {
			food.die();
		}
		mode = Mode.EAT;
		eatTime = defaultActionTime;
	}
}
