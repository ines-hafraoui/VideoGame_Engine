package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;

public class Base extends Entity {
	
	boolean picked; 
	
	public Base(Automate a, Model m,Position p, Absolute_Orientation o ) {
		super(a,m,p,o);
		picked = false;
	}
	
	public Base(Model m,Position p, Absolute_Orientation o ) {
		super(m,p,o);
		picked = false;
	}

	@Override
	public void do_egg(int cat) {
		
		switch(cat) {
		case BOT : 
			model.get_entities().add(new Bot(model,position,abs_or));
			break;
		default : 
			break;
		}
	}

	@Override
	public void do_power(int p) {}
	
	@Override
	public Entity do_throw() {
		return null;
	}
	
	@Override
	public boolean do_move() {
		return false;
	}

	@Override
	public boolean do_jump() {
		return false;
	}
	
	@Override
	public boolean do_wizz(int factor) {
		return false;
	}
	
	@Override
	public void do_turn(Absolute_Orientation o) {	}

	@Override
	public boolean do_hit(Absolute_Orientation o, Category c, int porte) {
		return false;
	}

	@Override
	public boolean do_pick(Category c,int distance) {
		return false;
	}

	@Override
	public boolean do_get() {
		return false;
	}
	
	

}
