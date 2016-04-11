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

@GeneratedByReLogoBuilder
@SuppressWarnings({"unused","rawtypes","unchecked"})
public class ReLogoLink<T> extends BaseLink<T>	{

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


}