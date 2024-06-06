package game.automaton;


import game.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class Automate {

	private List<State> states;
	private List<State> currentStateList;
	private Entity entity;
	
	public Automate(Entity entity){
		states = new ArrayList<>();
		this.entity = entity;
	}
	
	
	public boolean blocked; //the entity will unblock the automaton once it's transition is over
	public void step(Entity e) {
		
		if (!blocked) {
			
			List<State> todelete = new ArrayList<>();
			List<State> toadd = new ArrayList<>();
			
			for (State state : currentStateList) {
				for (Transition transition : state.get_transitionList()) {
					if(transition.c.eval(entity)) {
						blocked = true;
						
						toadd.add(transition.cible);
						todelete.add(state);
					}
				}
			}
			
			for (State state:todelete) {
				currentStateList.remove(state);
			}
			for (State state: toadd) {
				currentStateList.add(state);
			}
			
		}
	}
	
	
}
