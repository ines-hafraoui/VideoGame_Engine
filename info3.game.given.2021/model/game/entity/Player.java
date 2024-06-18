package game.entity;

import java.util.ArrayList;

import game.automaton.Automate;
import game.model.Model;

public class Player extends Entity {
	
	
	//add current number of bot field
	
	public Player(Automate a, Model m,Position p, Absolute_Orientation o, String type, int team) {
		super(a,m,p,o, type, team);
		inventory = new ArrayList<Automate>();
		bots = new ArrayList<Entity>();
	
	}
	
	public Player(Model m,Position p, Absolute_Orientation o, String type, int team) {
		super(m,p,o,type, team);
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
			model.get_entities().add(model.newEntity(model,position,abs_or, EntityType.ARROW,team));
			break;
		case BOULE_FEU : 
			model.get_entities().add(model.newEntity(model,position,abs_or, EntityType.FIREBALL,team));
			break;
		default : 
			break;
		}
	}

	@Override
	public boolean do_hit(Absolute_Orientation o, String t, int porte) {
		return model.inflict_hit(o, porte, t, this.get_x(), this.get_y());
	}

	@Override
	public boolean do_pick(String t,int distance) {
		Entity e = model.get_entity(distance, t, this.get_x(), this.get_y());	// ask the model to give it the entity (whiwh is an item) at the distance d 
		return inventory.add(e.aut);
	}

	@Override
	public Entity do_throw() {
		int index = index_inventory%Model.NB_BOT;
		Automate a = inventory.remove(index);
		Item new_item = new Item(a,model, position,abs_or,"I", NOTEAM);
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
