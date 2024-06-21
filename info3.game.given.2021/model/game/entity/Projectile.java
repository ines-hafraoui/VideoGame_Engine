package game.entity;

import game.automaton.Automate;
import game.model.Model;

public class Projectile extends Entity {
	
	

	public Projectile(Automate a, Model m, Position p, Absolute_Orientation o, int team, int nb_bot) {
		super(a, m, p, o, team, nb_bot);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean do_pick(int distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Entity do_throw() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean do_wizz(int factor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_get() {
		// TODO Auto-generated method stub
		return false;
	}

}
