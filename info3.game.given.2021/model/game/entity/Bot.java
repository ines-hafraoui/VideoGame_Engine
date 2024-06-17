package game.entity;

import game.automaton.Automate;
import game.entity.Absolute_Orientation;
import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;

public class Bot extends Entity{

	
	private int acc_factor;
	
	public Bot(Automate a, Model m,Position p, Absolute_Orientation o,String type, int team) {
		super(a,m,p,o,type, team);
		acc_factor = 3;
	}
	
	public Bot(Model m,Position p, Absolute_Orientation o, String type, int team) {
		super(m,p,o,type, team);
		acc_factor = 3;
	}

	@Override
	public boolean do_move() {
		Position p = newPosition();
		if (p == null) return false;
		position = p;
		return true;
	}

	@Override
	public void do_egg(int cat) {
		
		switch(cat) {
		case FLECHE : 
			model.get_entities().add(model.newEntity(model,position,abs_or, ARROW));
			break;
		case BOULE_FEU : 
			model.get_entities().add(model.newEntity(model,position,abs_or, FIRE_BALL));
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
	public boolean do_hit(Absolute_Orientation o,  String t, int porte) {
		return model.inflict_hit(o, porte, t);
	}

	@Override
	public boolean do_pick(String t ,int distance) {
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
		newSpeed(factor);
		return true;
	}

	@Override
	public boolean do_get() {
		return false;
	}
	
	

}
