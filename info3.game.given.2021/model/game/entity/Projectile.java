package game.entity;

import game.automaton.Automate;
import game.model.Model;

public class Projectile extends Entity {
	


	public Projectile(Model m, Position p, Absolute_Orientation o,String type, int team, int nb_bot, Boolean pickable, HitBox hb, String name) {
		super(m, p, o, team, nb_bot,pickable, hb,name);
		this.set_type(type);
		this.HP = 10;
	}
	
	public Projectile(Automate a, Model m, Position p, Absolute_Orientation o, String type, int team, int i, String name) {
		super(a,m, p, o, team, i,name);
		this.HP = 10;
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
	
	protected Position newPosition() {
		Position newPosition = null;

		float speed = newSpeed(1);
		int angle = speed_vct_abs_or.get_abs_Angle();
		double angleRad = Math.toRadians(angle);

		float X = (float) (Math.cos(angleRad) * speed);
		float Y = (float) (Math.sin(angleRad) * speed);

		float newX = this.position.getPositionX() + X;
		float newY = this.position.getPositionY() + Y;

		newPosition = new Position(newX, newY);
		return newPosition;
	}
	
	public boolean do_move() {
		Position p = newPosition();
		if (!model.inBounds(p.getPositionX(), p.getPositionY(), this))
			this.do_explode();
		System.out.print("mooving");
		if (p== null) return false;
		this.position = p; 
		
		return true;
		
	}
	
	
	public boolean do_hit() {
		return false;
	}

}
