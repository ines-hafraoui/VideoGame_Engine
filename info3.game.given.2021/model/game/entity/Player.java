package game.entity;

import java.util.ArrayList;
import java.util.List;

import game.automaton.Automate;
import game.model.Model;

public class Player extends Entity {
	protected List<Item> inventory;
	
	//add current number of bot field

	public Player(Automate a, Model m,Position p, Absolute_Orientation o, int team, int nb_bot) {
		super(a,m,p,o, team, nb_bot);
		inventory = new ArrayList<Item>();
		bots = new ArrayList<Entity>();
		type = "P";
	
	}
	

	public Player(Model m,Position pos, Absolute_Orientation o,int team, int nb_bot,int view, Boolean pickable, HitBox hb) {
		super(m,pos,o,team, nb_bot, view, pickable,hb);
		inventory = new ArrayList<Item>();
		type = EntityType.PLAYER;
		bots = new ArrayList<Entity>();
	}

	@Override
	public boolean do_pick(int distance) {
		state_action = ActionType.PICK;
		Item item = (Item) model.get_entity(distance,"I",this.get_x(), this.get_y());	// ask the model to give it the entity (whiwh is an item) at the distance d 
		return inventory.add(item);
	}

	@Override
	public Entity do_throw() {

		state_action = ActionType.THROW;
		int index = index_inventory%Model.nb_bot_init;
		Item item = inventory.remove(index);
		return item;
	}

	@Override
	public boolean do_get() {
		Entity e = bots.get(index_bot);
		Item item = inventory.remove(index_inventory);
		if (item != null) {
			e.aut = item.get_automate(); 
			index_inventory =0;
			index_bot = 0;
			return true;
		}
		return false;
	}

	@Override
	public boolean do_wizz(int factor) {
		state_action = ActionType.WIZZ;
		newSpeed(factor);
		return true;
	}
}
