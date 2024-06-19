package game.entity;

import game.automaton.Automate;
import game.entity.Absolute_Orientation;
import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;

public class Bot extends Entity{

	
	private int acc_factor;
	
	public Bot(Automate a, Model m,Position p, Absolute_Orientation o,String type, int team, int nb_bot) {
		super(a,m,p,o,type, team, nb_bot);
		acc_factor = 3;
	}
	
	public Bot(Model m,Position p, Absolute_Orientation o, String type, int team, int nb_bot) {
		super(m,p,o,type, team, nb_bot);
		acc_factor = 3;
	}

	@Override
	public boolean do_move() {
		Position p = newPosition();
		if (p == null) return false;
		position = p;
		state_action = ActionType.MOVE;
		return true;
	}

	@Override
	public void do_egg(int cat) {
		
		switch(cat) {
		case FLECHE : 
			model.get_entities().add(model.newEntity(model,position,abs_or, EntityType.ARROW,team));
			state_action = ActionType.EGG;
			break;
		case BOULE_FEU : 
			model.get_entities().add(model.newEntity(model,position,abs_or, EntityType.FIREBALL,team));
			state_action = ActionType.EGG;
			break;
		default : 
			break;
		}
	}

	@Override
	public void do_turn(Absolute_Orientation o) {
		state_action = ActionType.TURN;
		abs_or = o;
	}

	@Override
	public boolean do_hit(Absolute_Orientation o,  String t, int porte) {
		state_action = ActionType.HIT;
		return model.inflict_hit(o, porte, t, this.get_x(), this.get_y());
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
		state_action = ActionType.POWER;
	}

	@Override
	public boolean do_jump() {
		return false;
	}

	@Override
	public boolean do_wizz(int factor) {
		state_action = ActionType.WIZZ;
		newSpeed(factor);
		return true;
	}

	@Override
	public boolean do_get() {
		return false;
	}
	
	

}
