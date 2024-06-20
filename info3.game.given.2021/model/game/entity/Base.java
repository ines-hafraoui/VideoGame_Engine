package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;

public class Base extends Entity {
	
	boolean picked; 
	
	public Base(Automate a, Model m,Position p, Absolute_Orientation o , int team, int nb_bot) {
		super(a,m,p,o,team, nb_bot);
		picked = false;
		type = "BA";
	}

	public Base(Model m,Position pos, Absolute_Orientation o, int team, int nb_bot,int view, Boolean pickable, HitBox hb) {
		super(m,pos,o,team,nb_bot,view,pickable,hb);
		picked = false;
		type = "BA";
	}

	@Override
	public void do_egg(int cat) {
		state_action = ActionType.EGG;
		
		switch(cat) {
		case BOT : 
			model.get_entities().add(model.newEntity(model,position,abs_or, "BO",team));
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
	public boolean do_pick(int distance) {
		return false;
	}

	@Override
	public boolean do_get() {
		return false;
	}
	
	

}
