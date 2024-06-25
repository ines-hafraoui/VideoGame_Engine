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

	int TICK = 0;
	int DELAY = -1;

	public boolean blocked; // the entity will unblock the automaton once it's transition is over

	public Automate(Entity entity) {

		this.entity = entity;
		blocked = false;
	}

	public Automate(String initial_state, List<State> states) {
		this.states = new HashSet(states);
		this.initial_state = initial_state;
		currentStateList.add(getState(this.initial_state));
		blocked = false;

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

	public void step(Entity e) {

		System.out.println(TICK + " " + DELAY);
		System.out.println(currentStateList.size());

		if (TICK >= DELAY) {
			blocked = false;
		} else {
			System.out.println("BLOCKED");
			System.out.println(TICK);
			TICK++;
		}

		if (!blocked) {

			if (!action_buffer.isEmpty()) {
				blocked = true;
   				Action a = action_buffer.get(0);
				if (a instanceof game.automaton.Egg) {
 					TICK = 0;
					DELAY = 500;
				}

				a.exec(e);
				action_buffer.remove(0);

			} else {
				Random r = new Random();

				Set<State> toRemove = new HashSet<State>();

				for (State state : currentStateList) {
					for (Transition transition : state.get_transitionList()) {

						if (transition.c.percent != -1 && !(r.nextInt(Integer.MAX_VALUE) <= transition.c.percent))
							continue;

						if (transition.c.eval(entity)) {

							for (Action a : transition.actionList) {
								if (a.percent == -1 || r.nextInt(Integer.MAX_VALUE) <= a.percent) {
									action_buffer.add(a);
								}
							}

							State s = this.getState(transition.cible);
							
							if (!s.equals(state)) {
								currentStateList.add(s);
								toRemove.add(state);
							}

							break;
						}

					}
				}

				for (State s : toRemove) {
					currentStateList.remove(s);
				}
				
				System.out.println(currentStateList.size());

			}
		}

	}

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
