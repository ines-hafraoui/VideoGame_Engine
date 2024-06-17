package game.automaton;

import java.util.ArrayList;
import java.util.List;

public class State {

	private int tirage = 0;
	private String name;
	private List<Transition> transitionList;
	
	public State() {
		transitionList = new ArrayList<Transition>();
	}
	
	public State(List<Transition> tList, String name) {
		transitionList = tList;
		this.name = name;
	}
	public State(String name){
		this.name = name;
		transitionList = new ArrayList<Transition>();
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
	public String getName() {
		return this.name;
	}
	
}
