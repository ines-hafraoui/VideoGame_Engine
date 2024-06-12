package game.entity;

import game.automaton.Automate;

import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;
import java.util.ArrayList;
import java.util.List;

public class Player extends Entity {
	
	
	public Player(Automate a, Model m,Position p, Absolute_Orientation o ) {
		super(a,m,p,o);
		inventory = new ArrayList<Automate>();
		bots = new ArrayList<Entity>();
	
	}
	
	public Player(Model m,Position p, Absolute_Orientation o ) {
		super(m,p,o);
		inventory = new ArrayList<Automate>();
		bots = new ArrayList<Entity>();
	}


	@Override
	public boolean do_move(Absolute_Orientation o) {
		abs_or = o;
		return calcul_newPos();
	}

	@Override
	public Entity do_egg(Automate a) {
		return null;
	}

	@Override
	public boolean do_hit() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean do_pick(int distance, Category c) {
		Entity e = model.get_entity(distance, c);
		return inventory.add(e.aut);
	}

	@Override
	public Entity do_throw(int index) {
		Automate a = inventory.remove(index);
		Entity new_e = new Entity(a,model, position,abs_or);
		return new_e;
	}

	@Override
	public void do_explode() {
		explode = true;
	}

	@Override
	public void do_power(int p) {
		HP+=p;
	}

	@Override
	public void do_turn(Absolute_Orientation o) {
		abs_or = o;
	}
	
	@Override
	public boolean do_jump() {
		return false;
	}

	@Override
	public boolean do_wizz() {
		return false;
	}

	@Override
	public boolean do_get(Entity e, int index) {
		Automate a = inventory.remove(index);
		if (a != null) {
			e.aut = a; 
			return true;
		}
		return false;
	}

	
	

}
