package game.entity;

import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;

public class Base extends Entity {

	boolean picked;

	public Base(Automate a, Model m, Position p, Absolute_Orientation o, int team, int nb_bot, String name) {
		super(a, m, p, o, team, nb_bot, name);
		picked = false;
		type = EntityType.BASE;
	}

	public Base(Model m, Position pos, Absolute_Orientation o, int team, int nb_bot, Boolean pickable, HitBox hb,
			String name) {
		super(m, pos, o, team, nb_bot, pickable, hb, name);
		picked = false;
		type = EntityType.BASE;
	}

	@Override
	public boolean do_move() {
		return false;
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
