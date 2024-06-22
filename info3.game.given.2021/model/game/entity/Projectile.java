package game.entity;

import game.automaton.Automate;
import game.model.Model;

public class Projectile extends Entity {
	


	public Projectile(Model m, Position p, Absolute_Orientation o, int team, int nb_bot, Boolean pickable, HitBox hb) {
		super(m, p, o, team, nb_bot,pickable, hb);
		this.HP = 0;
	}
	
	public Projectile(Automate a, Model m, Position p, Absolute_Orientation o, int team, int i) {
		super(a,m, p, o, team, i);
		this.HP = 0;
	}

	public void set_type (String s) {
		switch (s) {
		case "Arrow": 
			this.type = EntityType.ARROW;
			break;
		case "FireBall" :
			this.type = EntityType.FIREBALL;
			break;
		default : 
			System.out.print("mauvais type attribué au projectile");
			this.type = null; 
			break;
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
