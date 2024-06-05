package game.entity;

import game.automaton.Automate;
import game.automaton.Direction;
import game.automaton.Category;
import game.automaton.State;
import game.grid.*;

public abstract class Entity {
	Automate aut;
	Grid g;
	
	public abstract boolean eval_cell(Direction dir, Category cat);
}
