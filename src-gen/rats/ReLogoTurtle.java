package rats;

import static repast.simphony.relogo.Utility.*;
import static repast.simphony.relogo.UtilityG.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import groovy.lang.Closure;
import repast.simphony.relogo.*;
import repast.simphony.relogo.builder.GeneratedByReLogoBuilder;
import repast.simphony.relogo.builder.ReLogoBuilderGeneratedFor;
import repast.simphony.space.SpatialException;
import repast.simphony.space.grid.Grid;
import repast.simphony.space.grid.GridPoint;

@GeneratedByReLogoBuilder
@SuppressWarnings({"unused","rawtypes","unchecked"})
public class ReLogoTurtle extends BaseTurtle{

	/**
	 * Makes a number of new rats and then executes a set of commands on the
	 * created rats.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created rats
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Rat")
	public AgentSet<rats.relogo.turtles.Rat> hatchRats(int number, Closure closure) {
		AgentSet<rats.relogo.turtles.Rat> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.hatch(number,closure,"Rat");
		for (Turtle t : createResult){
			if (t instanceof rats.relogo.turtles.Rat){
				result.add((rats.relogo.turtles.Rat)t);
			}
		} 
		return result;
	}

	/**
	 * Makes a number of new rats and then executes a set of commands on the
	 * created rats.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created rats
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Rat")
	public AgentSet<rats.relogo.turtles.Rat> hatchRats(int number) {
		return hatchRats(number,null);
	}

	/**
	 * Returns an agentset of rats from the patch of the caller.
	 * 
	 * @return agentset of rats from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Rat")
	public AgentSet<rats.relogo.turtles.Rat> ratsHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<rats.relogo.turtles.Rat> result = new AgentSet<rats.relogo.turtles.Rat>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"rat")){
			if (t instanceof rats.relogo.turtles.Rat)
			result.add((rats.relogo.turtles.Rat)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of rats on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of rats at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Rat")
	public AgentSet<rats.relogo.turtles.Rat> ratsAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getTurtleLocation(),displacement,getMyObserver());
		AgentSet<rats.relogo.turtles.Rat> result = new AgentSet<rats.relogo.turtles.Rat>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"rat")){
			if (t instanceof rats.relogo.turtles.Rat)
			result.add((rats.relogo.turtles.Rat)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<rats.relogo.turtles.Rat>();
		}
	}

	/**
	 * Returns an agentset of rats on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of rats on patch p
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Rat")
	public AgentSet<rats.relogo.turtles.Rat> ratsOn(Patch p){
		AgentSet<rats.relogo.turtles.Rat> result = new AgentSet<rats.relogo.turtles.Rat>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"rat")){
			if (t instanceof rats.relogo.turtles.Rat)
			result.add((rats.relogo.turtles.Rat)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of rats on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of rats on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Rat")
	public AgentSet<rats.relogo.turtles.Rat> ratsOn(Turtle t){
		AgentSet<rats.relogo.turtles.Rat> result = new AgentSet<rats.relogo.turtles.Rat>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"rat")){
			if (tt instanceof rats.relogo.turtles.Rat)
			result.add((rats.relogo.turtles.Rat)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of rats on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of rats on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Rat")
	public AgentSet<rats.relogo.turtles.Rat> ratsOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<rats.relogo.turtles.Rat>();
		}

		Set<rats.relogo.turtles.Rat> total = new HashSet<rats.relogo.turtles.Rat>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(ratsOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(ratsOn(p));
				}
			}
		}
		return new AgentSet<rats.relogo.turtles.Rat>(total);
	}

	/**
	 * Queries if object is a rat.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a rat
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Rat")
	public boolean isRatQ(Object o){
		return (o instanceof rats.relogo.turtles.Rat);
	}

	/**
	 * Returns an agentset containing all rats.
	 * 
	 * @return agentset of all rats
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Rat")
	public AgentSet<rats.relogo.turtles.Rat> rats(){
		AgentSet<rats.relogo.turtles.Rat> a = new AgentSet<rats.relogo.turtles.Rat>();
		for (Object e : this.getMyObserver().getContext().getObjects(rats.relogo.turtles.Rat.class)) {
			if (e instanceof rats.relogo.turtles.Rat){
				a.add((rats.relogo.turtles.Rat)e);
			}
		}
		return a;
	}

	/**
	 * Returns the rat with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Rat")
	public rats.relogo.turtles.Rat rat(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof rats.relogo.turtles.Rat)
			return (rats.relogo.turtles.Rat) turtle;
		return null;
	}

	/**
	 * Makes a number of new food and then executes a set of commands on the
	 * created food.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created food
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Food")
	public AgentSet<rats.relogo.turtles.Food> hatchFood(int number, Closure closure) {
		AgentSet<rats.relogo.turtles.Food> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.hatch(number,closure,"Food");
		for (Turtle t : createResult){
			if (t instanceof rats.relogo.turtles.Food){
				result.add((rats.relogo.turtles.Food)t);
			}
		} 
		return result;
	}

	/**
	 * Makes a number of new food and then executes a set of commands on the
	 * created food.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created food
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Food")
	public AgentSet<rats.relogo.turtles.Food> hatchFood(int number) {
		return hatchFood(number,null);
	}

	/**
	 * Returns an agentset of food from the patch of the caller.
	 * 
	 * @return agentset of food from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Food")
	public AgentSet<rats.relogo.turtles.Food> foodHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<rats.relogo.turtles.Food> result = new AgentSet<rats.relogo.turtles.Food>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"food")){
			if (t instanceof rats.relogo.turtles.Food)
			result.add((rats.relogo.turtles.Food)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of food on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of food at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Food")
	public AgentSet<rats.relogo.turtles.Food> foodAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getTurtleLocation(),displacement,getMyObserver());
		AgentSet<rats.relogo.turtles.Food> result = new AgentSet<rats.relogo.turtles.Food>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"food")){
			if (t instanceof rats.relogo.turtles.Food)
			result.add((rats.relogo.turtles.Food)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<rats.relogo.turtles.Food>();
		}
	}

	/**
	 * Returns an agentset of food on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of food on patch p
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Food")
	public AgentSet<rats.relogo.turtles.Food> foodOn(Patch p){
		AgentSet<rats.relogo.turtles.Food> result = new AgentSet<rats.relogo.turtles.Food>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"food")){
			if (t instanceof rats.relogo.turtles.Food)
			result.add((rats.relogo.turtles.Food)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of food on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of food on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Food")
	public AgentSet<rats.relogo.turtles.Food> foodOn(Turtle t){
		AgentSet<rats.relogo.turtles.Food> result = new AgentSet<rats.relogo.turtles.Food>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"food")){
			if (tt instanceof rats.relogo.turtles.Food)
			result.add((rats.relogo.turtles.Food)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of food on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of food on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Food")
	public AgentSet<rats.relogo.turtles.Food> foodOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<rats.relogo.turtles.Food>();
		}

		Set<rats.relogo.turtles.Food> total = new HashSet<rats.relogo.turtles.Food>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(foodOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(foodOn(p));
				}
			}
		}
		return new AgentSet<rats.relogo.turtles.Food>(total);
	}

	/**
	 * Queries if object is a food.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a food
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Food")
	public boolean isFoodQ(Object o){
		return (o instanceof rats.relogo.turtles.Food);
	}

	/**
	 * Returns an agentset containing all food.
	 * 
	 * @return agentset of all food
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Food")
	public AgentSet<rats.relogo.turtles.Food> food(){
		AgentSet<rats.relogo.turtles.Food> a = new AgentSet<rats.relogo.turtles.Food>();
		for (Object e : this.getMyObserver().getContext().getObjects(rats.relogo.turtles.Food.class)) {
			if (e instanceof rats.relogo.turtles.Food){
				a.add((rats.relogo.turtles.Food)e);
			}
		}
		return a;
	}

	/**
	 * Returns the food with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.turtles.Food")
	public rats.relogo.turtles.Food food(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof rats.relogo.turtles.Food)
			return (rats.relogo.turtles.Food) turtle;
		return null;
	}

	/**
	 * Makes a number of new userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserTurtle")
	public AgentSet<rats.relogo.UserTurtle> hatchUserTurtles(int number, Closure closure) {
		AgentSet<rats.relogo.UserTurtle> result = new AgentSet<>();
		AgentSet<Turtle> createResult = this.hatch(number,closure,"UserTurtle");
		for (Turtle t : createResult){
			if (t instanceof rats.relogo.UserTurtle){
				result.add((rats.relogo.UserTurtle)t);
			}
		} 
		return result;
	}

	/**
	 * Makes a number of new userTurtles and then executes a set of commands on the
	 * created userTurtles.
	 * 
	 * @param number
	 *            a number
	 * @param closure
	 *            a set of commands
	 * @return created userTurtles
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserTurtle")
	public AgentSet<rats.relogo.UserTurtle> hatchUserTurtles(int number) {
		return hatchUserTurtles(number,null);
	}

	/**
	 * Returns an agentset of userTurtles from the patch of the caller.
	 * 
	 * @return agentset of userTurtles from the caller's patch
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserTurtle")
	public AgentSet<rats.relogo.UserTurtle> userTurtlesHere(){
	  Grid grid = getMyObserver().getGrid();
	  GridPoint gridPoint = grid.getLocation(this);
	  AgentSet<rats.relogo.UserTurtle> result = new AgentSet<rats.relogo.UserTurtle>();
	  for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"userTurtle")){
			if (t instanceof rats.relogo.UserTurtle)
			result.add((rats.relogo.UserTurtle)t);
		}
		return result;
	}

	/**
	 * Returns the agentset of userTurtles on the patch at the direction (ndx, ndy) from the
	 * caller.
	 * 
	 * @param nX
	 *            a number
	 * @param nY
	 *            a number
	 * @returns agentset of userTurtles at the direction (nX, nY) from the caller
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserTurtle")
	public AgentSet<rats.relogo.UserTurtle> userTurtlesAt(Number nX, Number nY){
		double dx = nX.doubleValue();
		double dy = nY.doubleValue();
		double[] displacement = {dx,dy};

		try{
		GridPoint gridPoint = Utility.getGridPointAtDisplacement(getTurtleLocation(),displacement,getMyObserver());
		AgentSet<rats.relogo.UserTurtle> result = new AgentSet<rats.relogo.UserTurtle>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(gridPoint,getMyObserver(),"userTurtle")){
			if (t instanceof rats.relogo.UserTurtle)
			result.add((rats.relogo.UserTurtle)t);
		}
		return result;
		}
		catch(SpatialException e){
			return new AgentSet<rats.relogo.UserTurtle>();
		}
	}

	/**
	 * Returns an agentset of userTurtles on a given patch.
	 * 
	 * @param p
	 *            a patch
	 * @return agentset of userTurtles on patch p
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserTurtle")
	public AgentSet<rats.relogo.UserTurtle> userTurtlesOn(Patch p){
		AgentSet<rats.relogo.UserTurtle> result = new AgentSet<rats.relogo.UserTurtle>();						
		for (Turtle t : Utility.getTurtlesOnGridPoint(p.getGridLocation(),getMyObserver(),"userTurtle")){
			if (t instanceof rats.relogo.UserTurtle)
			result.add((rats.relogo.UserTurtle)t);
		}
		return result;
	}

	/**
	 * Returns an agentset of userTurtles on the same patch as a turtle.
	 * 
	 * @param t
	 *            a turtle
	 * @return agentset of userTurtles on the same patch as turtle t
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserTurtle")
	public AgentSet<rats.relogo.UserTurtle> userTurtlesOn(Turtle t){
		AgentSet<rats.relogo.UserTurtle> result = new AgentSet<rats.relogo.UserTurtle>();						
		for (Turtle tt : Utility.getTurtlesOnGridPoint(Utility.ndPointToGridPoint(t.getTurtleLocation()),getMyObserver(),"userTurtle")){
			if (tt instanceof rats.relogo.UserTurtle)
			result.add((rats.relogo.UserTurtle)tt);
		}
		return result;
	}

	/**
	 * Returns an agentset of userTurtles on the patches in a collection or on the patches
	 * that a collection of turtles are.
	 * 
	 * @param a
	 *            a collection
	 * @return agentset of userTurtles on the patches in collection a or on the patches
	 *         that collection a turtles are
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserTurtle")
	public AgentSet<rats.relogo.UserTurtle> userTurtlesOn(Collection c){

		if (c == null || c.isEmpty()){
			return new AgentSet<rats.relogo.UserTurtle>();
		}

		Set<rats.relogo.UserTurtle> total = new HashSet<rats.relogo.UserTurtle>();
		if (c.iterator().next() instanceof Turtle){
			for (Object o : c){
				if (o instanceof Turtle){
					Turtle t = (Turtle) o;
					total.addAll(userTurtlesOn(t));
				}
			}
		}
		else {
			for (Object o : c){
				if (o instanceof Patch){
					Patch p = (Patch) o;
					total.addAll(userTurtlesOn(p));
				}
			}
		}
		return new AgentSet<rats.relogo.UserTurtle>(total);
	}

	/**
	 * Queries if object is a userTurtle.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a userTurtle
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserTurtle")
	public boolean isUserTurtleQ(Object o){
		return (o instanceof rats.relogo.UserTurtle);
	}

	/**
	 * Returns an agentset containing all userTurtles.
	 * 
	 * @return agentset of all userTurtles
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserTurtle")
	public AgentSet<rats.relogo.UserTurtle> userTurtles(){
		AgentSet<rats.relogo.UserTurtle> a = new AgentSet<rats.relogo.UserTurtle>();
		for (Object e : this.getMyObserver().getContext().getObjects(rats.relogo.UserTurtle.class)) {
			if (e instanceof rats.relogo.UserTurtle){
				a.add((rats.relogo.UserTurtle)e);
			}
		}
		return a;
	}

	/**
	 * Returns the userTurtle with the given who number.
	 * 
	 * @param number
	 *            a number
	 * @return turtle number
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserTurtle")
	public rats.relogo.UserTurtle userTurtle(Number number){
		Turtle turtle = Utility.turtleU(number.intValue(), getMyObserver());
		if (turtle instanceof rats.relogo.UserTurtle)
			return (rats.relogo.UserTurtle) turtle;
		return null;
	}

	/**
	 * Makes a directed userLink from a turtle to the caller then executes a set of
	 * commands on the created userLink.
	 * 
	 * @param t
	 *            a turtle
	 * @param closure
	 *            a set of commands
	 * @return created userLink
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public rats.relogo.UserLink createUserLinkFrom(Turtle t, Closure closure){
		rats.relogo.UserLink link = (rats.relogo.UserLink)this.getMyObserver().getNetwork("UserLink").addEdge(t,this);
		if (closure != null){
			this.ask(link,closure);
		}
		return link;
	}

	/**
	 * Makes a directed userLink from a turtle to the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return created userLink
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public rats.relogo.UserLink createUserLinkFrom(Turtle t){
			return createUserLinkFrom(t,null);
	}

	/**
	 * Makes directed userLinks from a collection to the caller then executes a set
	 * of commands on the created userLinks.
	 * 
	 * @param a
	 *            a collection
	 * @param closure
	 *            a set of commands
	 * @return created userLinks
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public AgentSet<rats.relogo.UserLink> createUserLinksFrom(Collection<? extends Turtle> a, Closure closure){
		AgentSet<rats.relogo.UserLink> links = new AgentSet<rats.relogo.UserLink>();
		for(Turtle t : a){
			links.add((rats.relogo.UserLink)this.getMyObserver().getNetwork("UserLink").addEdge(t,this));
		}
		if (closure != null){
			this.ask(links,closure);
		}
		return links;
	}

	/**
	 * Makes directed userLinks from a collection to the caller.
	 * 
	 * @param a
	 *            a collection
	 * @return created userLinks
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public AgentSet<rats.relogo.UserLink> createUserLinksFrom(Collection<? extends Turtle> a){
		return createUserLinksFrom(a,null);
	}

	/**
	 * Makes a directed userLink to a turtle from the caller then executes a set of
	 * commands on the created userLink.
	 * 
	 * @param t
	 *            a turtle
	 * @param closure
	 *            a set of commands
	 * @return created userLink
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public rats.relogo.UserLink createUserLinkTo(Turtle t, Closure closure){
		rats.relogo.UserLink link = (rats.relogo.UserLink)this.getMyObserver().getNetwork("UserLink").addEdge(this,t);
		if (closure != null){
			this.ask(link,closure);
		}
		return link;
	}

	/**
	 * Makes a directed userLink to a turtle from the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return created userLink
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public rats.relogo.UserLink createUserLinkTo(Turtle t){
			return createUserLinkTo(t,null);
	}

	/**
	 * Makes directed userLinks to a collection from the caller then executes a set
	 * of commands on the created userLinks.
	 * 
	 * @param a
	 *            a collection
	 * @param closure
	 *            a set of commands
	 * @return created userLinks
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public AgentSet<rats.relogo.UserLink> createUserLinksTo(Collection<? extends Turtle> a, Closure closure){
		AgentSet<rats.relogo.UserLink> links = new AgentSet<rats.relogo.UserLink>();
		for(Object t : a){
			if (t instanceof Turtle){
				links.add((rats.relogo.UserLink)this.getMyObserver().getNetwork("UserLink").addEdge(this,(Turtle)t));
			}
		}
		if (closure != null){
			this.ask(links,closure);
		}
		return links;
	}

	/**
	 * Makes directed userLinks to a collection from the caller.
	 * 
	 * @param a
	 *            a collection
	 * @return created userLinks
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public AgentSet<rats.relogo.UserLink> createUserLinksTo(Collection<? extends Turtle> a){
		return createUserLinksTo(a,null);
	}

	/**
	 * Queries if there is a directed userLink from a turtle to the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return true or false based on whether there is a directed userLink from
	 *         turtle t to the caller
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public boolean inUserLinkNeighborQ(Turtle t){
		return this.getMyObserver().getNetwork("UserLink").isPredecessor(t, this);
	}

	/**
	 * Returns the agentset with directed userLinks to the caller.
	 * 
	 * @return agentset with directed userLinks to the caller
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public AgentSet inUserLinkNeighbors(){
		AgentSet result = new AgentSet();
		for(Object o : this.getMyObserver().getNetwork("UserLink").getPredecessors(this)){
			result.add(o);
		}
		return result;
	}

	/**
	 * Returns the directed userLink from a turtle to the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return directed userLink from turtle t to the caller
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public rats.relogo.UserLink inUserLinkFrom(Turtle t){
		return (rats.relogo.UserLink)this.getMyObserver().getNetwork("UserLink").getEdge(t,this);
	}

	/**
	 * Returns an agentset of directed userLinks from other turtles to the caller.
	 * 
	 * @return agentset of directed userLinks from other turtles to the caller
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public AgentSet<rats.relogo.UserLink> myInUserLinks(){
		AgentSet<rats.relogo.UserLink> result = new AgentSet<rats.relogo.UserLink>();
		for(Object o :  this.getMyObserver().getNetwork("UserLink").getInEdges(this)){
			if (o instanceof rats.relogo.UserLink){
				result.add((rats.relogo.UserLink) o);
			}
		}
		return result;
	}

	/**
	 * Returns an agentset of directed userLinks to other turtles from the caller.
	 * 
	 * @return agentset of directed userLinks to other turtles from the caller
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public AgentSet<rats.relogo.UserLink> myOutUserLinks(){
		AgentSet<rats.relogo.UserLink> result = new AgentSet<rats.relogo.UserLink>();
		for(Object o :  this.getMyObserver().getNetwork("UserLink").getOutEdges(this)){
			if (o instanceof rats.relogo.UserLink){
				result.add((rats.relogo.UserLink) o);
			}
		}
		return result;
	}

	/**
	 * Queries if there is a directed userLink to a turtle from the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return true or false based on whether there is a directed userLink to
	 *         turtle t from the caller
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public boolean outUserLinkNeighborQ(Turtle t){
		return this.getMyObserver().getNetwork("UserLink").isPredecessor(this, t);
	}

	/**
	 * Returns the agentset with directed userLinks from the caller.
	 * 
	 * @return agentset with directed userLinks from the caller
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public AgentSet outUserLinkNeighbors(){
		AgentSet result = new AgentSet();
		for(Object o : this.getMyObserver().getNetwork("UserLink").getSuccessors(this)){
			result.add(o);
		}
		return result;
	}

	/**
	 * Returns the directed userLink to a turtle from the caller.
	 * 
	 * @param t
	 *            a turtle
	 * @return directed userLink to turtle t from the caller
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public rats.relogo.UserLink outUserLinkTo(Turtle t){
		return (rats.relogo.UserLink)this.getMyObserver().getNetwork("UserLink").getEdge(this, t);
	}

	/**
	 * Reports true if there is a userLink connecting t and the caller.
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public boolean userLinkNeighborQ(Turtle t){
		return this.getMyObserver().getNetwork("UserLink").isAdjacent(this, t);
	}

	/**
	 * Returns the agentset of all turtles found at the other end of
	 * userLinks connected to the calling turtle.
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public AgentSet userLinkNeighbors(){
		AgentSet result = new AgentSet();
		for(Object o : this.getMyObserver().getNetwork("UserLink").getAdjacent(this)){
			result.add(o);
		}
		return result;
	}

	/**
	 * Returns an agentset of the caller's userLinks.
	 * 
	 * @return agentset of the caller's userLinks
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public AgentSet<rats.relogo.UserLink> myUserLinks(){
		AgentSet<rats.relogo.UserLink> result = new AgentSet<rats.relogo.UserLink>();
		for(Object o : this.getMyObserver().getNetwork("UserLink").getEdges(this)){
			if (o instanceof rats.relogo.UserLink){
				result.add((rats.relogo.UserLink)o);
			}
		}
		return result;
	}

	/**
	 * Queries if object is a userLink.
	 * 
	 * @param o
	 *            an object
	 * @return true or false based on whether the object is a userLink
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public boolean isUserLinkQ(Object o){
		return (o instanceof rats.relogo.UserLink);
	}

	/**
	 * Returns an agentset containing all userLinks.
	 * 
	 * @return agentset of all userLinks
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public AgentSet<rats.relogo.UserLink> userLinks(){
		AgentSet<rats.relogo.UserLink> a = new AgentSet<rats.relogo.UserLink>();
		for (Object e : this.getMyObserver().getContext().getObjects(rats.relogo.UserLink.class)) {
			if (e instanceof rats.relogo.UserLink){
				a.add((rats.relogo.UserLink)e);
			}
		}
		return a;
	}

	/**
	 * Returns the userLink between two turtles.
	 * 
	 * @param oneEnd
	 *            an integer
	 * @param otherEnd
	 *            an integer
	 * @return userLink between two turtles
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public rats.relogo.UserLink userLink(Number oneEnd, Number otherEnd) {
		return (rats.relogo.UserLink)(this.getMyObserver().getNetwork("UserLink").getEdge(turtle(oneEnd),turtle(otherEnd)));
	}

	/**
	 * Returns the userLink between two turtles.
	 * 
	 * @param oneEnd
	 *            a turtle
	 * @param otherEnd
	 *            a turtle
	 * @return userLink between two turtles
	 */
	@ReLogoBuilderGeneratedFor("rats.relogo.UserLink")
	public rats.relogo.UserLink userLink(Turtle oneEnd, Turtle otherEnd) {
		return userLink(oneEnd.getWho(), otherEnd.getWho());
	}

	/**
	 * Returns the value of the global variable aggressivenessThreshold.
	 *
	 * @return the value of the global variable aggressivenessThreshold
	 */
	@ReLogoBuilderGeneratedFor("global: aggressivenessThreshold")
	public Object getAggressivenessThreshold(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("aggressivenessThreshold");
	}

	/**
	 * Sets the value of the global variable aggressivenessThreshold.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: aggressivenessThreshold")
	public void setAggressivenessThreshold(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("aggressivenessThreshold",value);
	}

	/**
	 * Returns the value of the global variable foodCapacity.
	 *
	 * @return the value of the global variable foodCapacity
	 */
	@ReLogoBuilderGeneratedFor("global: foodCapacity")
	public Object getFoodCapacity(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("foodCapacity");
	}

	/**
	 * Sets the value of the global variable foodCapacity.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: foodCapacity")
	public void setFoodCapacity(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("foodCapacity",value);
	}

	/**
	 * Returns the value of the global variable foodSourceSpawnFrequency.
	 *
	 * @return the value of the global variable foodSourceSpawnFrequency
	 */
	@ReLogoBuilderGeneratedFor("global: foodSourceSpawnFrequency")
	public Object getFoodSourceSpawnFrequency(){
		return repast.simphony.relogo.ReLogoModel.getInstance().getModelParam("foodSourceSpawnFrequency");
	}

	/**
	 * Sets the value of the global variable foodSourceSpawnFrequency.
	 *
	 * @param value
	 *            a value
	 */
	@ReLogoBuilderGeneratedFor("global: foodSourceSpawnFrequency")
	public void setFoodSourceSpawnFrequency(Object value){
		repast.simphony.relogo.ReLogoModel.getInstance().setModelParam("foodSourceSpawnFrequency",value);
	}


}