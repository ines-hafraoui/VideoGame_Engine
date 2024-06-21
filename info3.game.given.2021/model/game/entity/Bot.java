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

	public Bot(Model m,Position pos, Absolute_Orientation o, int team, int nb_bot,int view, Boolean pickable, HitBox hb) {
		super(m,pos,o,team, nb_bot, view, pickable,hb);
		acc_factor = 3;
		type = EntityType.TEAMMATE;
	}

	@Override
	public void do_egg(int cat) {
		
		Entity e;
		
		switch(cat) {
		case FLECHE : 
			e = model.newEntity(model,position,abs_or, EntityType.ARROW,team,0,0,false,new HitBox(10,10));
			model.get_entities().add(e);
			state_action = ActionType.EGG;
			break;
		case BOULE_FEU :
			e = model.newEntity(model,position,abs_or, EntityType.FIREBALL,team,0,0,false,new HitBox(10,10));
			model.get_entities().add(e);
			state_action = ActionType.EGG;
			break;
		default : 
			break;
		}
	}

	@Override
	public boolean do_hit(Absolute_Orientation o,  String t, int porte) {
		state_action = ActionType.HIT;
		return model.inflict_hit(o, porte, t, this.get_x(), this.get_y());
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
