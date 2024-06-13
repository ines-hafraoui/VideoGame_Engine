package game.entity;

import game.automaton.Category;
import game.model.Model;

public class Fleche extends Entity{
	
	public Fleche(Model m, Position p, Absolute_Orientation o) {
		super(m,p,o);
	}

	@Override
	public boolean do_move(Absolute_Orientation o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void do_egg(int cat) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean do_hit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_pick(int distance, Category c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Entity do_throw(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void do_power(int p) {
		// TODO Auto-generated method stub
		
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
	public boolean do_get(Entity e, int index) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void do_turn(Absolute_Orientation o) {
		// TODO Auto-generated method stub
		
	}
	

}
