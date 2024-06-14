package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;

public class Bot extends Entity{
	
	public Bot(Automate a, Model m,Position p, Absolute_Orientation o ) {
		super(a,m,p,o);
	}
	
	public Bot(Model m,Position p, Absolute_Orientation o ) {
		super(m,p,o);
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

	@Override
	public boolean do_turn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_hit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_wait() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_pick() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_throw() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_store() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_get(int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_explode() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_power() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_jump() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_wizz() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Entity do_egg(Automate a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean do_store(Entity e) {
		return false;
	}
	
	

}
