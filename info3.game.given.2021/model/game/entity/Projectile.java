package game.entity;

import game.automaton.Automate;
import game.model.Model;

public class Projectile extends Entity {
	


	public Projectile(Model m, Position p, Absolute_Orientation o, int team, int nb_bot,int view, Boolean pickable, HitBox hb, String sprite) {
		super(m, p, o, team, nb_bot,view, pickable, hb,sprite);
	}
	
	public void set_type (String s) {
		switch (s) {
		case "A": 
			this.type = EntityType.ARROW;
		case "FB" :
			this.type = EntityType.FIREBALL;
		default : 
			System.out.print("mauvais type attribué au projectile");
			this.type = null; 
		}
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
