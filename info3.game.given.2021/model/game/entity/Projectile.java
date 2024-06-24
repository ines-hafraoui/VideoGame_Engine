package game.entity;

import game.automaton.Automate;
import game.model.Model;

public class Projectile extends Entity {
	


	public Projectile(Model m, Position p, Absolute_Orientation o,String type, int team, int nb_bot, Boolean pickable, HitBox hb, String name) {
		super(m, p, o, team, nb_bot,pickable, hb,name);
		this.set_type(type);
		this.HP = 0;
	}
	
	public Projectile(Automate a, Model m, Position p, Absolute_Orientation o, String type, int team, int i, String name) {
		super(a,m, p, o, team, i,name);
		this.HP = 0;
		this.set_type(type);
	}

	public void set_type (String s) {
		switch (s) {
		case "Arrow": 
		case "A" :
			this.type = EntityType.ARROW;
			break;
		case "FireBall" :
		case "FB":
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
