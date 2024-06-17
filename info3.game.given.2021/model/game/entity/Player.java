package game.entity;

import game.automaton.Automate;


import game.automaton.Category;
import game.automaton.Direction;
import game.model.Model;
import java.util.ArrayList;
import java.util.List;
import game.entity.Entity;

public class Player extends Entity {
	
	
	public Player(Automate a, Model m,Position p, Absolute_Orientation o, String t) {
		super(a,m,p,o, t);
		inventory = new ArrayList<Automate>();
		bots = new ArrayList<Entity>();
	
	}
	
	public Player(Model m,Position p, Absolute_Orientation o, String t) {
		super(m,p,o,t);
		inventory = new ArrayList<Automate>();
		bots = new ArrayList<Entity>();
	}


	@Override
	public boolean do_move() {
		Position p = newPosition();
		if (p == null) return false;
		position = p;
		return true;
	}

	@Override
	public void do_egg(int cat) {
		
		switch(cat) {
		case FLECHE : 
			model.get_entities().add(model.newEntity(model,position,abs_or, ARROW));
			break;
		case BOULE_FEU : 
			model.get_entities().add(model.newEntity(model,position,abs_or, FIRE_BALL));
			break;
		default : 
			break;
		}
	}

	@Override
	public boolean do_hit(Absolute_Orientation o, String t, int porte) {
		return model.inflict_hit(o, porte, t);
	}

	@Override
	public boolean do_pick(String t,int distance) {
		Entity e = model.get_entity(distance, t);	// ask the model to give it the entity (whiwh is an item) at the distance d 
		return inventory.add(e.aut);
	}

	@Override
	public Entity do_throw() {
		int index = index_inventory%nb_bot;
		Automate a = inventory.remove(index);
		Item new_item = new Item(a,model, position,abs_or,"I");
		return new_item;
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
	public boolean do_get() {
		Entity e = bots.get(index_bot);
		Automate a = inventory.remove(index_inventory);
		if (a != null) {
			e.aut = a; 
			index_inventory =0;
			index_bot = 0;
			return true;
		}
		return false;
	}
	
	@Override
	public boolean do_jump() {
		return false;
	}

	@Override
	public boolean do_wizz(int factor) {
		newSpeed(factor);
		return true;
	}

	
	

}
