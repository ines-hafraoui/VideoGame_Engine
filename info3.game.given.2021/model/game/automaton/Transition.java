package game.automaton;

import java.util.ArrayList;
import java.util.List;

public class Transition {
	String cible;
	Condition c;
	List<Action> actionList;
	
	public Transition(String cible, Condition condition, List<Action> a) {
		
		actionList = a;
		this.cible = cible;
		this.c = condition;
	}
	
	
}
