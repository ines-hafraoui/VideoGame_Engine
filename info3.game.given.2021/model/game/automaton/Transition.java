package game.automaton;

import java.util.ArrayList;
import java.util.List;

public class Transition {
	State cible;
	Condition c;
	List<Action> actionList;
	
	public Transition(State cible, Condition condition, List<Action> a) {
		
		actionList = a;
		this.cible = cible;
		this.c = condition;
	}
	
}
