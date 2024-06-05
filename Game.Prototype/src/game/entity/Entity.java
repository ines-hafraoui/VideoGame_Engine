package game.entity;

import game.automaton.Automate;
import game.automaton.State;

public abstract class Entity {
	Automate aut;
	State current;
	
	public abstract boolean eval_cell(Direction dir, Category cat);
}
