package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;

public class Base extends Entity {

	boolean picked;
	protected Entity player;

	public Base(Automate a, Model m, Position p, Absolute_Orientation o, int team, int nb_bot, String name) {
		super(a, m, p, o, team, nb_bot, name);
		HP=1000000;
		picked = false;
		type = EntityType.BASE;
	}

	public Base(Model m, Position pos, Absolute_Orientation o, int team, int nb_bot, Boolean pickable, HitBox hb,
			String name) {
		super(m, pos, o, team, nb_bot, pickable, hb, name);
		picked = false;
		type = EntityType.BASE;
	}
	
	public void set_player(Entity e) {
		player = e;
	}

	@Override
	public boolean do_move() {
		return false;
	}
	
	public void do_egg(int c) {
		
		set_state_action(ActionType.EGG);
		Entity e;
		Automate a;
		Position eggPos = new Position(position.getPositionX(), position.getPositionY());
		Absolute_Orientation eggOr = new Absolute_Orientation(abs_or.get_abs_Orientation());
	
		e = model.newEntity(model, eggPos, eggOr, EntityType.TEAMMATE, team, 0, 0, false, new HitBox(2, 2), "Bot"+team);
		model.get_entities().add(e);
		e.set_player(this.player);
		a = model.automates.get(EntityType.TEAMMATE);
		e.set_automate(a);
	}

	@Override
	public boolean do_rest(int p) {
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
	public boolean do_pick(int distance) {
		return false;
	}

	@Override
	public boolean do_get() {
		return false;
	}

}
