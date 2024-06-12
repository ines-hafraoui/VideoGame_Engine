package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
	
	
	private List<Entity> items; 
	
	
	public Player(Automate a, Model m,Position p, Absolute_Orientation o ) {
		super(a,m,p,o);
		items = new ArrayList<Entity>();
	}
	
	public Player(Model m,Position p, Absolute_Orientation o ) {
		super(m,p,o);
		items = new ArrayList<Entity>();
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
