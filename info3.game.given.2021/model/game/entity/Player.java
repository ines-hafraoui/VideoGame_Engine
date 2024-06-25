package game.entity;

import java.util.ArrayList;
import java.util.List;

import game.automaton.Automate;
import game.map.Polygon;
import game.model.Model;

public class Player extends Entity {
	protected Item inventory[];
	protected int nb_item_inventory;

	// add current number of bot field

	public Player(Automate a, Model m, Position p, Absolute_Orientation o, int team, int nb_bot, String name) {
		super(a, m, p, o, team, nb_bot, name);
		inventory = new Item[nb_bot];
		bots = new ArrayList<Entity>();
		type = EntityType.PLAYER;

	}

	public Player(Model m, Position pos, Absolute_Orientation o, int team, int nb_bot, Boolean pickable, HitBox hb,
			String name) {
		super(m, pos, o, team, nb_bot, pickable, hb, name);
		inventory = new Item[nb_bot];
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
			List<Entity> toRemove = new ArrayList<Entity>();
			for (Entity entity : entities) {
				if (entity.getHitBox().get_polygon().intersectsWith(polygon)) {
					if (entity.pickable) {
						this.get_inventory()[nb_item_inventory] = (Item) entity;
						model.addToRemove(entity) ;
						nb_item_inventory++;
					}
				}
			}
			
			
			for (Entity e : toRemove) {
				model.entities.remove(e);
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
		System.out.print("is getting");
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

	public void do_egg(int c) {
		set_state_action(ActionType.EGG);
		Entity e;
		Automate a;
		Position eggPos = new Position(position.getPositionX(), position.getPositionY());
		Absolute_Orientation eggOr = new Absolute_Orientation(abs_or.get_abs_Orientation());
		
		// Temporary just to test
		e = model.newEntity(model, eggPos, eggOr, EntityType.ARROW, team, 0, 0, false, new HitBox(2, 2), "Arrow");
		model.get_entities().add(e);
		e.set_player(this);
		a = model.automates.get(EntityType.ARROW);
		e.set_automate(a);
	}

	@Override
	public boolean do_wizz(int factor) {
		state_action = ActionType.WIZZ;
		newSpeed(factor);
		return true;
	}

	@Override
	public Item[] get_inventory() {
		return inventory;
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
