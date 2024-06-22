package game.entity;

import java.util.ArrayList;
import java.util.List;

import game.automaton.Automate;
import game.model.Model;

public class Player extends Entity {
	protected Item inventory[];
	protected int nb_item_inventory;
	
	//add current number of bot field

	public Player(Automate a, Model m,Position p, Absolute_Orientation o, int team, int nb_bot) {
		super(a,m,p,o, team, nb_bot);
		inventory = new Item[nb_bot];
		bots = new ArrayList<Entity>();
		type = EntityType.PLAYER;
	
	}
	

	public Player(Model m,Position pos, Absolute_Orientation o,int team, int nb_bot,Boolean pickable, HitBox hb) {
		super(m,pos,o,team, nb_bot,pickable,hb);
		inventory = new Item[nb_bot];
		type = EntityType.PLAYER;
		bots = new ArrayList<Entity>();
	}
	
	@Override
	public boolean do_pick(int distance) {	// a refaire
//		if (nb_item_inventory < this.nb_bot_init) {
//			state_action = ActionType.PICK;
//			Item item = (Item) model.get_entity(distance,"I",this.get_x(), this.get_y());	// ask the model to give it the entity (whiwh is an item) at the distance d
//			inventory[nb_item_inventory] = item;
//			return true;
//		}
		return false;
	}

	@Override
	public Entity do_throw() {
		
	 if (nb_item_inventory != 0) {
		state_action = ActionType.THROW;
        int index = index_inventory % Model.nb_bot_init;
        Item item = inventory[index];
        // move element to the left
        for (int i = index; i < nb_item_inventory - 1; i++) {
            inventory[i] = inventory[i + 1];
        }
        inventory[nb_item_inventory - 1] = null; // last element is null
        nb_item_inventory--;
        return item;
	 }
		 return null;
	}

	@Override
	public boolean do_get() {
		if (bots.size() != 0) {
			Entity e = bots.get(index_bot);
			Item item = inventory[index_inventory];
			if (item != null) {
	            // move element to the left
	            for (int i = index_inventory; i < nb_item_inventory - 1; i++) {
	                inventory[i] = inventory[i + 1];
	            }
	            inventory[nb_item_inventory - 1] = null; // last element is null
	            nb_item_inventory--;
	            
	            e.aut = item.get_automate();
	            index_inventory = 0;
	            index_bot = 0;
	            return true;
	        }
			return false;
		}
		return false;
	}

	@Override
	public boolean do_wizz(int factor) {
		state_action = ActionType.WIZZ;
		newSpeed(factor);
		return true;
	}
	
	public Item[] get_inventory(){
		return inventory;
	}
}
