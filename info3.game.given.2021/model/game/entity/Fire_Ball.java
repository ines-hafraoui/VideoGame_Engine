package game.entity;

import game.automaton.Category;
import game.model.Model;

public class Fire_Ball extends Entity{
	
	public Fire_Ball(Model m, Position p, Absolute_Orientation o,String t) {
		super(m,p,o,t);
	}
	
	@Override
	public boolean do_move() {
		return false;
	}

	@Override
	public void do_egg(int c) {
	}

	@Override
	public boolean do_hit(Absolute_Orientation o,  String t, int porte) {
		return false;
	}

	@Override
	public boolean do_pick(String t,int distance) {
		return false;
	}

	@Override
	public Entity do_throw() {
		return null;
	}

	@Override
	public void do_explode() {}

	@Override
	public void do_power(int p) {	}

	@Override
	public boolean do_jump() {
		return false;
	}

	@Override
	public boolean do_wizz(int factor) {
		return false;
	}

	@Override
	public boolean do_get() {
		return false;
	}

	@Override
	public void do_turn(Absolute_Orientation o) {	}

	
	

}
