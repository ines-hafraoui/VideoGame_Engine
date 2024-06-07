package game.automaton;

import game.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Automate {

	private List<State> states;
	private List<State> currentStateList;
	private Entity entity;

	public Automate(Entity entity) {
		states = new ArrayList<>();
		currentStateList = new ArrayList<>();

		this.entity = entity;
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

	public boolean blocked; // the entity will unblock the automaton once it's transition is over

	public void step(Entity e) {
		//to delete when view will be able to handle animations
		blocked = false;
		
		if (!blocked) {

			List<State> todelete = new ArrayList<>();
			List<State> toadd = new ArrayList<>();

			for (State state : currentStateList) {
				for (Transition transition : state.get_transitionList()) {
					if (transition.c.eval(entity)) {
						blocked = true;

						for(Action a : transition.actionList) {
							a.exec(e);
						}
							
						toadd.add(transition.cible);
						todelete.add(state);
					}
				}
			}

			for (State state : todelete) {
				currentStateList.remove(state);
			}
			for (State state : toadd) {
				currentStateList.add(state);
			}

		}
	}

}
