package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.grid.Grid;

public class Apple extends Entity{

	private boolean eaten;
	
	public Apple (Automate aut, Grid grid) {
		this.aut=aut;
		this.g=grid;
		eaten = false;
	}
	
	public boolean eaten () {
		return eaten;
	}

	@Override
	public boolean eval_cell(Direction dir, Category cat) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
