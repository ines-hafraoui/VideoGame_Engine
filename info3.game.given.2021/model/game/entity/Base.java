package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;

public class Base extends Entity {
	
	public Base(Automate a, Model m,Position p, Absolute_Orientation o ) {
		super(a,m,p,o);
	}
	
	public Base(Model m,Position p, Absolute_Orientation o ) {
		super(m,p,o);
	}

	@Override
	public boolean eval_cell(Direction dir, Category cat) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_move() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_egg() {
		// TODO Auto-generated method stub
		return false;
	}
	
	

}
