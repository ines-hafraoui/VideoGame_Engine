package game.entity;

import game.automaton.Automate;
import game.entity.Absolute_Orientation;
import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;

public class Bot extends Entity{
	
	public final static int FLECHE = 1;
	public final static int BOULE_FEU = 2;
	
	public Bot(Automate a, Model m,Position p, Absolute_Orientation o ) {
		super(a,m,p,o);
	}
	
	public Bot(Model m,Position p, Absolute_Orientation o ) {
		super(m,p,o);
	}

	@Override
	public boolean do_move(Absolute_Orientation o) {
		abs_or = o;
		return calcul_newPos();
	}

	@Override
	public void do_egg(int cat) {
		
		switch(cat) {
		case FLECHE : 
			break;
		case BOULE_FEU : 
			break;
		default : 
			break;
		}
	}

	@Override
	public void do_turn(Absolute_Orientation o) {
		abs_or = o;
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
	public void do_power(int p) {
		HP += p;
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
	
	

}
