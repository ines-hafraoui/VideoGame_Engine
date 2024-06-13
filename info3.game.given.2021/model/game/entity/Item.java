package game.entity;

import java.util.ArrayList;

import game.automaton.Automate;
import game.automaton.Category;
import game.model.Model;

public class Item extends Entity{
	
	public Item(Automate a, Model m,Position p, Absolute_Orientation o ) {
		super(a,m,p,o);
	}
	
	public Item(Model m,Position p, Absolute_Orientation o ) {
		super(m,p,o);
	}

	@Override
	public boolean do_move(Absolute_Orientation o) {
		return false;
	}

	@Override
	public void do_egg(int c) {
	}

	@Override
	public boolean do_hit() {
		return false;
	}

	@Override
	public boolean do_pick(int distance, Category c) {
		return false;
	}

	@Override
	public Entity do_throw(int index) {
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
	public boolean do_wizz() {
		return false;
	}

	@Override
	public boolean do_get(Entity e, int index) {
		return false;
	}

	@Override
	public void do_turn(Absolute_Orientation o) {	}
	
}
