package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;

public class Base extends Entity {
	
	boolean picked; 
	
	public Base(Automate a, Model m,Position p, Absolute_Orientation o , String t) {
		super(a,m,p,o,t);
		picked = false;
	}
	
	public Base(Model m,Position p, Absolute_Orientation o ,String t) {
		super(m,p,o,t);
		picked = false;
	}

	@Override
	public void do_egg(int cat) {
		
		switch(cat) {
		case BOT : 
			model.get_entities().add(model.newEntity(model,position,abs_or, "BO"));
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
	public boolean do_hit(Absolute_Orientation o, String t, int porte) {
		return false;
	}

	@Override
	public boolean do_pick(String t,int distance) {
		return false;
	}

	@Override
	public boolean do_get() {
		return false;
	}
	
	

}
