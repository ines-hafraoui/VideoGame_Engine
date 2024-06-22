package game.entity;

import game.automaton.Automate;
import game.entity.Absolute_Orientation;
import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;

public class Bot extends Entity{

	
	private int acc_factor;
	
	public Bot(Automate a, Model m,Position p, Absolute_Orientation o,int team, int nb_bot) {
		super(a,m,p,o,team, nb_bot);
		acc_factor = 3;
		type = EntityType.TEAMMATE;
	}

	public Bot(Model m,Position pos, Absolute_Orientation o, int team, int nb_bot,Boolean pickable, HitBox hb) {
		super(m,pos,o,team, nb_bot,pickable,hb);
		acc_factor = 3;
		type = EntityType.TEAMMATE;
	}

	@Override
	public boolean do_pick(int distance) {
		return false;
	}

	@Override
	public Entity do_throw() {
		return null;
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
