package game.entity;

import game.automaton.Automate;
import game.entity.Absolute_Orientation;
import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;

public class Bot extends Entity{

	
	private int acc_factor;
	
	public Bot(Automate a, Model m,Position p, Absolute_Orientation o ) {
		super(a,m,p,o);
		acc_factor = 3;
	}
	
	public Bot(Model m,Position p, Absolute_Orientation o ) {
		super(m,p,o);
		acc_factor = 3;
	}

	@Override
	public boolean do_move() {
		return newPosition();
	}

	@Override
	public void do_egg(int cat) {
		
		switch(cat) {
		case FLECHE : 
			model.get_entities().add(new Fleche(model,position,abs_or));
			break;
		case BOULE_FEU : 
			model.get_entities().add(new Boule_Feu(model,position,abs_or));
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
	public boolean do_hit(Absolute_Orientation o,  Category c, int porte) {
		return model.inflict_hit(o, porte, c);
	}

	@Override
	public boolean do_pick(Category c,int distance) {
		return false;
	}

	@Override
	public Entity do_throw() {
		return null;
	}


	@Override
	public void do_power(int p) {
		HP += p;
	}

	@Override
	public boolean do_jump() {
		return false;
	}

	@Override
	public boolean do_wizz(int factor) {
		return calcul_newSpeed(acc_factor);
	}

	@Override
	public boolean do_get() {
		return false;
	}
	
	

}
