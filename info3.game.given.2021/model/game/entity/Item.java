package game.entity;

import java.util.ArrayList;

import game.automaton.Automate;
import game.automaton.Category;
import game.model.Model;

public class Item extends Entity{
	
	public Item(Automate a, Model m,Position p, Absolute_Orientation o,String type, int team, int nb_bot) {
		super(a,m,p,o,type, team,nb_bot);
	}
	
	public Item(Model m,Position p, Absolute_Orientation o, String type, int team, int nb_bot) {
		super(m,p,o,type, team,nb_bot);
	}

	public Item(Position pos, Absolute_Orientation o, String type, int team, int nb_bot,int view, Boolean pickable) {
		super(pos,o,type, team,nb_bot, view, pickable);
	}

	@Override
	public boolean do_move() {
		return false;
	}

	@Override
	public void do_egg(int c) {
		state_action = ActionType.EGG;
	}

	@Override
	public boolean do_hit(Absolute_Orientation o,  String t, int porte) {
		return false;
	}

	@Override
	public boolean do_pick(String t,int distance) {
		return false;
	}

	@Override
	public Entity do_throw() {
		return null;
	}

	@Override
	public void do_explode() {
		state_action = ActionType.EXPLODE;
	}

	@Override
	public void do_power(int p) {}

	@Override
	public boolean do_jump() {
		return false;
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
	public void do_turn(Absolute_Orientation o) {	
		abs_or = o;
		state_action = ActionType.TURN;
	}
	
}
