package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.grid.*;

public class Snake extends Entity{
	
	
	public Snake(Automate aut, Grid grille) {
		this.aut = aut; 
		this.g = grille;
		
		
	}

	@Override
	public boolean eval_cell(Direction dir, Category cat) {
		// TODO Auto-generated method stub
		return false;
	}

}
