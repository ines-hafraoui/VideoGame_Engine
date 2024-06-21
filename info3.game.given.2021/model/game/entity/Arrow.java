package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.model.Model;

public class Arrow extends Entity{
	
	public Arrow(Automate a,Model m, Position p, Absolute_Orientation o,int team, int nb_bot) {
		super(m,p,o,team, nb_bot);
		type = EntityType.ARROW;
	}
	
	public Arrow(Model m, Position p, Absolute_Orientation o,int team, int nb_bot) {
		super(m,p,o,team,nb_bot);
		type = EntityType.ARROW;
	}
	
	public Arrow(Model m,Position p, Absolute_Orientation o, int team,int nb_bot, int view,boolean pickable,HitBox hb) {
		super(m,p,o,team,nb_bot,view,pickable,hb);
		type = EntityType.FIREBALL;
	}

	@Override
	public boolean do_hit(Absolute_Orientation o,  String t, int porte) {
		return false;
	}

	@Override
	public Entity do_throw() {
		return null;
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
	public boolean do_pick(int distance) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
