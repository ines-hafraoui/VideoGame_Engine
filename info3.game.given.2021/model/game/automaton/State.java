package game.automaton;

import java.util.ArrayList;
import java.util.List;

public class State {

	private int tirage = 0;
	private List<Transition> transitionList;
	
	public State() {
		transitionList = new ArrayList<Transition>();
	}
	
	State(List<Transition> tList) {
		transitionList = tList;
	}
	
	List<Transition>get_transitionList() {
		return transitionList;
	}
	
	void set_transitionList(List<Transition> transitionList) {
		this.transitionList =  transitionList;
	}
	
	public boolean add_transition(Transition t) {
		return transitionList.add(t);
	}
	
	boolean remove_transition(Transition t) {
		return transitionList.remove(t);
	}
	
}
