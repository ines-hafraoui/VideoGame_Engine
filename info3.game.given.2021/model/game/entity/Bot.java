package game.entity;

import game.automaton.Automate;
import game.entity.Absolute_Orientation;
import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;

public class Bot extends Entity{

	
	private int acc_factor;
	private Entity m_player;
	
	public Bot(Automate a, Model m,Position p, Absolute_Orientation o,int team, int nb_bot,String name) {
		super(a,m,p,o,team, nb_bot,name);
		acc_factor = 3;
		type = EntityType.TEAMMATE;
		name = "BOT" + team;
	}

	public Bot(Model m,Position pos, Absolute_Orientation o, int team, int nb_bot,Boolean pickable, HitBox hb, String name) {
		super(m,pos,o,team, nb_bot,pickable,hb,name);
		acc_factor = 3;
		type = EntityType.TEAMMATE;
		name = "BOT" + team;
	}
	
	public void set_player(Entity e) {
		m_player = e;
	}

	@Override
	public boolean do_pick(int distance) {
		return false;
	}

	@Override
	public Entity do_throw() {
		return null;
	}
	
	public boolean do_move() {
		this.position.setPositionX(m_player.get_x());
		this.position.setPositionY(m_player.get_y());
		return true;
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
