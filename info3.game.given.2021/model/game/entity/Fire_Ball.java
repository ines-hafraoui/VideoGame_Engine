package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.model.Model;

public class Fire_Ball extends Entity{
	
	
	public Fire_Ball(Automate a,Model m, Position p, Absolute_Orientation o,int team, int nb_bot) {
		super(a,m,p,o, team, nb_bot);
		type = EntityType.FIREBALL;
	}
	 
	
	public Fire_Ball(Model m, Position p, Absolute_Orientation o,int team, int nb_bot) {
		super(m,p,o,team, nb_bot);
		type = EntityType.FIREBALL;
	}
	
	public Fire_Ball(Model m,Position p, Absolute_Orientation o, int team,int nb_bot, int view,boolean pickable,HitBox hb) {
		super(m,p,o,team,nb_bot,view,pickable,hb);
		type = EntityType.FIREBALL;
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
	public void do_explode() {
		state_action = ActionType.EXPLODE;
	}

	@Override
	public boolean do_wizz(int factor) {
		return false;
	}

	@Override
	public boolean do_get() {
		return false;
	}
	

}
