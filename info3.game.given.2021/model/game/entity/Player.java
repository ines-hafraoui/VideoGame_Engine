package game.entity;

import java.util.ArrayList;
import java.util.List;

import game.automaton.Automate;
import game.automaton.Category;
import game.map.Polygon;
import game.model.Model;

public class Player extends Entity {
	protected List<Item> inventory;
	protected Item[] inv = new Item[model.nb_bot_init];
	protected int nb_item_inventory;
	protected int index=-1;

	// add current number of bot field

	public Player(Automate a, Model m, Position p, Absolute_Orientation o, int team, int nb_bot, String name) {
		super(a, m, p, o, team, nb_bot, name);
		inventory = new ArrayList<Item>();
		bots = new ArrayList<Entity>();
		type = EntityType.PLAYER;

	}

	public Player(Model m, Position pos, Absolute_Orientation o, int team, int nb_bot, Boolean pickable, HitBox hb,
			String name) {
		super(m, pos, o, team, nb_bot, pickable, hb, name);
		inventory = new ArrayList<Item>();
		type = EntityType.PLAYER;
		bots = new ArrayList<Entity>();
	}

	@Override
	public boolean do_pick(int distance) {
		if (nb_item_inventory < this.nb_bot_init) {
			state_action = ActionType.PICK;
			

			double angle1 = 0, angle2 = 0;
			model.eval_angle(abs_or, angle1, angle2);
			Polygon polygon = model.create_polygon_direction(position.getPositionX(), position.getPositionY(), distance,
					angle1, angle2);
			List<Entity> entities = model.get_entities();
			for (Entity entity : entities) {
				if (entity.getHitBox().get_polygon().intersectsWith(polygon)) {
					if (entity.pickable) {
						this.inventory.add((Item)entity);
						update_inv();
						
						model.addToRemove(entity) ;
						nb_item_inventory++;
					}
				}
			}
			
			

			return true;
		}
		return false;
	}

	@Override
	public Entity do_throw() {

		if (nb_item_inventory != 0) {
			state_action = ActionType.THROW;
			int index = index_inventory % Model.nb_bot_init;
			Item item = inventory.get(index);
			inventory.remove(index);
			update_inv();

			nb_item_inventory--;
			return item;
		}
		return null;
	}

	@Override
	public boolean do_get() {
		
		if (nb_item_inventory == 0) return false;

		Item item = inventory.get(index);
		
		Entity entity = null;
		float min_dst = Float.MAX_VALUE;
		for (Entity e : model.get_entities()) {
			
			if (!(e instanceof Bot)) {
				continue;
			}
			
			if (this.position.distance(e.position)< min_dst) {
				min_dst = this.position.distance(e.position);
				entity = e;
			}
		}
		entity.aut = item.get_automate();
		
		inventory.remove(index);
		update_inv();

		nb_item_inventory--;
		index =0;
	
		//problem with moving every automaton in the inventory to first positions
		return true;
	}

	public void do_egg(int c) {
		

		aut.setDELAY(50);
		
		set_state_action(ActionType.EGG);
		Entity e;
		Automate a;
		Position eggPos = new Position(position.getPositionX(), position.getPositionY());
		Absolute_Orientation eggOr = new Absolute_Orientation(abs_or.get_abs_Orientation());
		
		// Temporary just to test
		e = model.newEntity(model, eggPos, eggOr, EntityType.PROJECTILE, team, 0, 0, false, new HitBox(2, 2), "Arrow");
		model.get_entities().add(e);
		e.set_player(this);
		a = model.automates.get(EntityType.PROJECTILE);
		e.set_automate(a);
	}

	@Override
	//select
	public boolean do_wizz(int factor) {
		
		aut.setDELAY(100);
		if (nb_item_inventory == 0) return false;
		
		state_action = ActionType.WIZZ;
		if(index==-1) {
			index=0;
			inventory.get(index).selected=true;
		}
		else {
			inventory.get(index).selected=false;
			index = (1 + index)%nb_item_inventory ;
			inventory.get(index).selected=true;
		}
		return true;
	}

	public void update_inv() {
		get_inventory();
	}
	
	@Override
	public Item[] get_inventory() {
		
		for (int i = 0; i < model.nb_bot_init; i++) {
			inv[i] = null;
		}
		
		int r = 0;
		for (Item i : inventory) {
			inv[r] = i;
			r++;
		}
		
		
		return inv;
	}

	public boolean do_hit(Absolute_Orientation o, String type, int porte) {
		set_state_action(ActionType.HIT);
		do_egg(0);
		return model.do_hit(o, type, porte, this);
	}

	@Override
	public void addbots(Entity e) {
		bots.add(e);

	}
}
