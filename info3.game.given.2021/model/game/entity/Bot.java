package game.entity;

import game.automaton.Automate;
import game.entity.Absolute_Orientation;
import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;

public class Bot extends Entity {

	private int acc_factor;
	private Entity m_player;
	private float offsetx, offsety;

	public Bot(Automate a, Model m, Position p, Absolute_Orientation o, int team, int nb_bot, String name) {
		super(a, m, p, o, team, nb_bot, name);
		acc_factor = 3;
		type = EntityType.TEAMMATE;
		name = "BOT" + team;
	}

	public Bot(Model m, Position pos, Absolute_Orientation o, int team, int nb_bot, Boolean pickable, HitBox hb,
			String name) {
		super(m, pos, o, team, nb_bot, pickable, hb, name);
		acc_factor = 3;
		type = EntityType.TEAMMATE;
		name = "BOT" + team;
	}

	public void set_player(Entity e) {
		m_player = e;
		this.offsetx = 10 * m_player.bots.size();
		this.offsety = 2 * m_player.bots.size();
		m_player.addbots(this);
	}

	public void do_egg(int c) {
		set_state_action(ActionType.EGG);
		Entity e;
		Automate a;
		Position eggPos = new Position(position.getPositionX(), position.getPositionY());
		Absolute_Orientation eggOr = new Absolute_Orientation(abs_or.get_abs_Orientation());
		switch (c) {
		case FLECHE:
			// Temporary just to test
			e = model.newEntity(model, eggPos, eggOr, EntityType.PROJECTILE, team, 0, 0, false, new HitBox(2, 2), "Arrow");
			model.get_entities().add(e);
			a = model.automates.get(EntityType.PROJECTILE);
			e.set_player(this.m_player);
			e.set_automate(a);
			break;
		case BOULE_FEU:
			// Temporary just to test
			e = model.newEntity(model, eggPos, eggOr, EntityType.PROJECTILE, team, 0, 0, false, new HitBox(2, 2),
					"Fireball");
			model.get_entities().add(e);
			e.set_player(this.m_player);
			a = model.automates.get(EntityType.PROJECTILE);
			e.set_automate(a);
			break;
		default:
			break;
		}
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
		do_turn(m_player.abs_or);
		this.position.setPositionX(m_player.get_x() + 10 + offsetx);
		this.position.setPositionY(m_player.get_y() + 10 + offsety);
		set_state_action(ActionType.MOVE);
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
