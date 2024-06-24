package game.automaton;

import game.entity.Entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Automate {

	private Set<State> states = new HashSet<State>();
	private Set<State> currentStateList = new HashSet<State>();

	private String initial_state;
	private Entity entity;
	
	private List<Action> action_buffer = new ArrayList<Action>();

	public Automate(Entity entity) {

		this.entity = entity;
	}


	public Automate(String initial_state, List<State> states) {
		this.states = new HashSet(states);
		this.initial_state = initial_state;
		currentStateList.add(getState(this.initial_state));

	}


	public void add_state(State s) {
		states.add(s);
	}

	public boolean addCurrentState(State s) {
		if (states.contains(s)) {
			return currentStateList.add(s);
		}

		return false;
	}

	public boolean removeCurrentState(State s) {
		return currentStateList.remove(s);
	}

	public List<State> getcurrentstate() {
		return new ArrayList<State>(currentStateList);
	}

	public boolean add_transition(State s1, String s2, Condition cond, List<Action> a) {
		Transition t = new Transition(s2, cond, a);
		return s1.add_transition(t);
	}

	public boolean blocked; // the entity will unblock the automaton once it's transition is over
	
	public void step(Entity e) {
	
	blocked = false;
	Random r = new Random();

		
		if (!blocked) {

			List<State> todelete = new ArrayList<>();
			List<State> toadd = new ArrayList<>();

			for ( State state : currentStateList) {
				for ( Transition transition : state.get_transitionList()) {
					
					if (transition.c.percent != -1 && !(r.nextInt(Integer.MAX_VALUE) <= transition.c.percent))
						continue;
					
					if (transition.c.eval(entity)) {
						blocked = true;

						for (Action a : transition.actionList) {
							
							if (a.percent == -1 || r.nextInt(Integer.MAX_VALUE) <= a.percent) {
								a.exec(e);
							}

							action_buffer.add(a);
						}
					State s = this.getState(transition.cible);
					toadd.add(s);
					for (State st : toadd) {
						currentStateList.add(st);
					}
				}
			}
		}
	}
}

//public void step(Entity e) {
//		
//		if (!blocked) {
//			List<State> todelete = new ArrayList<>();
//			List<State> toadd = new ArrayList<>();
//
//			for ( State state : currentStateList) {
//				for ( Transition transition : state.get_transitionList()) {
//					if (transition.c.eval(entity)) {
//						blocked = true;
//
//						for (Action a : transition.actionList) {
//							action_buffer.add(a);
//						}
//						State s = this.getState(transition.cible);
//						for (Action a : action_buffer) {
//							a.exec(e);
//						}
//						toadd.add(s);
//						todelete.add(state);
//						
//					}
//				}
//			}
//
//			for (State state : todelete) {
//				currentStateList.remove(state);
//			}
//			for (State state : toadd) {
//				currentStateList.add(state);
//			}
//			}
//	}


	public State getState(String name) {
		for (State s : this.states) {
			if (s.getName().equals(name)) {
				return s;
			}
		}
		return null;
	}
	
	public void set_entity(Entity e) {
		this.entity = e;
	}
	public Entity get_entity() {
		return this.entity;
	}
	

}
