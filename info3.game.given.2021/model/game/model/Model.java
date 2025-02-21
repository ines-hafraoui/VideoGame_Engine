/*
 * Copyright (C) 2020  Pr. Olivier Gruber
 * Educational software for a basic game development
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Created on: March, 2020
 *      Author: Pr. Olivier Gruber
 */
package game.model;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import gal.demo.test.TestMain;
import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Relative_Orientation;
import game.entity.Absolute_Orientation;
import game.entity.ActionType;
import game.entity.Base;
import game.entity.Bot;
import game.entity.Entity;
import game.entity.EntityType;
import game.entity.HitBox;
import game.entity.Item;
import game.entity.Player;
import game.entity.Position;
import game.entity.Projectile;
import game.map.Map;
import game.map.Polygon;
import info3.game.IFactory;

/**
 * A simple class that holds the images of a sprite for an animated cowbow.
 *
 */
public class Model {

	long m_imageElapsed;
	int m_width, height;

	public Map m_map;
	private Absolute_Orientation m_orientation;
	private List<String> list_touche;
	Entity[] players;
	public java.util.Map<String, java.util.Map<String, Object>> entityConfigurations;
	public java.util.Map<String, Automate> automates;
	public List<Entity> entities;
	public List<Entity> entityToRemove;
	public String aut_projectile[];
	public String aut_bot[];
	IFactory factory;
	public static int nb_bot_init;
	public static int nb_item;
	public int timer;
	public boolean cooperative;
	public int viscosity;
	public Parser configParse;
	private static long TIMER = 0;
	private boolean gameover = false;

	public Model(int w, int h, Parser parse, IFactory f)
			throws IOException, InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {

		m_width = w;
		height = h;
		configParse = parse;
		nb_bot_init = parse.nb_bot_init;
		viscosity = parse.viscosity;
		cooperative = parse.coop;
		timer = parse.timer;
		list_touche = new ArrayList<String>();
		factory = f;
		entities = new ArrayList<Entity>();
		entityToRemove = new ArrayList<Entity>();
		automates = new HashMap<>();
		entityConfigurations = parse.entities;
		players = new Entity[parse.nb_player];
		aut_projectile = parse.aut_projectile;
		aut_bot = parse.aut_bot;
		nb_item = parse.nb_item;
		int i = 0;

		// create all entities from the info that gave us the Parser

		for (java.util.Map.Entry<String, java.util.Map<String, Object>> entry : entityConfigurations.entrySet()) {
			String entityName = entry.getKey();
			java.util.Map<String, Object> properties = entry.getValue();

			// Extract common properties
			String direction = (String) properties.get("direction");
			Position pos = (Position) properties.get("position");
			int team = ((Number) properties.get("team")).intValue();
			boolean pickable = (Boolean) properties.get("pickable");
			String behaviour = (String) properties.get("behaviour");
			HitBox hb = (HitBox) properties.get("hitbox");
			String type = (String) properties.get("type");
			Class<?> features = Class.forName((String) properties.get("features"));
//			System.out.println(features);
			Constructor<?> cons = features.getConstructor(Model.class, Position.class, Absolute_Orientation.class,
					String.class, int.class, int.class, Boolean.class, HitBox.class, String.class);
			Object object = cons.newInstance(this, pos, new Absolute_Orientation(direction), type, team, nb_bot_init,
					pickable, hb, entityName);
			System.out.println(entityName + "  " + object);

			Entity entity = (Entity) object;
			if (type.equals(EntityType.TEAMMATE) || type.equals(EntityType.PARASITE)) {
				if (nb_bot_init <= 0) {
					entity = null;
				} else if (behaviour != null) {
					Automate automate = TestMain.loadAutomata(new File("gal/gal/" + behaviour).getAbsolutePath());
					if (automate != null) {
						for (int j = 1; j < nb_bot_init; j++) {
							entity.set_automate(automate);
							entities.add(entity);
							automates.put(entity.get_type(), automate);
							float x = pos.getPositionX();
							float y = pos.getPositionY();
							pos = new Position(x + 2, y + 2);
							entity = (Entity) cons.newInstance(this, pos, new Absolute_Orientation(direction), type,
									team, nb_bot_init, pickable, hb, entityName);
						}
					}
				}
			}

			if (type.equals(EntityType.ITEM)) {
				if (nb_item <= 0) {
					entity = null;
				} else if (behaviour != null) {
					Automate automate = TestMain.loadAutomata(new File("gal/gal/" + behaviour).getAbsolutePath());
					if (automate != null) {
						for (int j = 1; j < nb_item; j++) {
							entity.set_automate(automate);
							entities.add(entity);
							automates.put(entity.get_type(), automate);
							float x = pos.getPositionX();
							float y = pos.getPositionY();
							pos = new Position(x + 2, y + 2);
							hb = new HitBox(50, 50);
							entity = (Entity) cons.newInstance(this, pos, new Absolute_Orientation(direction), type,
									team, nb_bot_init, pickable, hb, entityName);
						}
					}
				}
			}

			if (entity != null) {
				if (behaviour != null) {

					String galPath = new File("gal/gal/" + behaviour).getAbsolutePath();

					Automate automate = TestMain.loadAutomata(galPath);

					if (automate != null) {
						entity.set_automate(automate);
						if (type.equals(EntityType.PLAYER)) {
							players[i] = entity;
							i++;
						}
						if (!(entity instanceof Projectile)) {
							entities.add(entity);
						}
						automates.put(entity.get_type(), automate);

					}
				}
			}
		}

		for (Entity e : entities) {
			if (e instanceof Bot || e instanceof Base) {
				int team = e.get_team();
				for (int j = 0; j < players.length; j++) {
					if (players[j].get_team() == team) {
						e.set_player(players[j]);
					}
				}

			}
		}

		List<Position> poss = new ArrayList<Position>();

		Position pos1 = new Position(0, 0);
		Position pos2 = new Position(0, h);
		Position pos3 = new Position(w, 0);
		Position pos4 = new Position(w, h);

		poss.add(pos1);
		poss.add(pos2);
		poss.add(pos4);
		poss.add(pos3);

		Polygon p = new Polygon(poss);
		m_map = new Map(p, this);
		m_map.generateMap(m_map.getSeed());
	}

	public Map getMap() {
		return m_map;
	}

	public void tick(long elapsed) {
		TIMER += elapsed;
		m_imageElapsed += elapsed;
		if (m_imageElapsed > 200) {
			m_imageElapsed = 0;
		}
		m_map.tick(elapsed);
		if (gameover) {
			GameOverboucle();
		}
	}

	public void GameOver() {
		gameover = true;
	}

	private void GameOverboucle() {
		System.exit(1);
	}

	public Entity get_entity(int distance, String t, float f, float g) {
		Entity e = m_map.get_entity(distance, t, f, g);
		return e;
	}

	public List<String> get_list_touche() {
		return this.list_touche;
	}

	public List<Item> getItems() {
		List<Item> l_items = new ArrayList<Item>();

		for (Entity e : entities) {
			if (e instanceof Item) {
				l_items.add((Item) e);
			}
		}

		return l_items;
	}

	public void add_entity(Entity e) {
		entities.add(e);
	}

	/*
	 * method give the list of entity that are on the map
	 */
	public List<Entity> get_entities() {
		return entities;
	}

	/*
	 * method give the list of players in the world
	 */
	public Entity[] get_players() {
		return players;
	}

	public Entity newEntity(Model model, Position position, Absolute_Orientation abs_or, String type, int team,
			int nb_bot, int view, Boolean pickable, HitBox hb, String name) {
		Entity e = factory.newEntity(model, position, abs_or, type, team, nb_bot, pickable, hb, name);
//		System.out.println("new Entity : " + name);
		if (m_ml != null)
			m_ml.addedEntity(e);
		return e;
	}

	/*
	 * The Interface of the ModelListener that enables other parts of the
	 * architecture whether there has been updates to the entities in the world
	 */
	public interface ModelListener {
		void addedEntity(Entity e);

		void removedEntity(Entity e);
	}

	public ModelListener m_ml;

	public void setListener(ModelListener l) {
		m_ml = l;
	}

	public int get_width() {
		return m_width;
	}

	public int get_height() {
		return height;
	}

	public List<Entity> list_cat(Category cat, int team) {
		List<Entity> list = new ArrayList<Entity>();
		switch (cat.toString()) {

		case "A":
			for (Entity e : entities) {
				if ((e.get_team() != team) && (e.get_team() != 0)
						&& ((e.get_type() != "FB") || (e.get_type() != "A"))) {
					list.add(e);
				}
			}
			break;
		case "M":
			for (Entity e : entities) {
				if ((e.get_team() != team) && ((e.get_type() != "FB") || (e.get_type() != "A"))) {
					list.add(e);
				}
			}
			break;
		case "T":
			for (Entity e : entities) {
				if ((e.get_team() == team) && (e.get_type() != "FB") && (e.get_type() != "A")) {
					list.add(e);
				}
			}
			break;
		case "@":
			for (Entity e : entities) {
				if ((e.get_team() == team) && (e.get_type() == "J")) {
					list.add(e);
				}
			}
			break;
		case "#":
			for (Entity e : entities) {
				if ((e.get_team() != team) && (e.get_type() == "J")) {
					list.add(e);
				}
			}
			break;
		case "_":
			for (Entity e : entities) {
				list.add(e);
			}
			break;
		default:
			for (Entity e : entities) {
				if (e.get_category().toString() == cat.toString()) {
					list.add(e);
				}
			}
			break;
		}
		return list;

	}

	public boolean eval_closest(List<Entity> list_cat, Absolute_Orientation d, Entity entity, float portee) {
		double p_x = entity.get_x();
		double p_y = entity.get_y();
		double closest_x = 0;
		double closest_y = 0;
		select_closest(list_cat, p_x, p_y, closest_x, closest_y);
		double angle1 = 0, angle2 = 0;
		eval_angle(d, angle1, angle2);
		Polygon polygon = create_polygon_direction(p_x, p_y, portee, angle1, angle2);
		Position closest = new Position((float) closest_x, (float) closest_y);
		System.out.print(polygon.containsPosition(closest));

		return polygon.containsPosition(closest);
	}

	private void select_closest(List<Entity> list_cat, double p_x, double p_y, double closest_x, double closest_y) {
		for (Entity e : list_cat) {
			float tmp_x = e.get_x();
			float tmp_y = e.get_y();
			float min = Integer.MAX_VALUE;
			float dist = distance(tmp_x, tmp_y, (float) p_x, (float) p_y);
			if (dist < min) {
				closest_x = tmp_x;
				closest_y = tmp_y;
				min = dist;
			}
		}
	}

	private float distance(float tmp_x, float tmp_y, float p_x, float p_y) {
		Position p1 = new Position(p_x, p_y);
		Position p2 = new Position(tmp_x, tmp_y);
		return p1.distance(p2);
	}

	public Polygon create_polygon_direction(double p_x, double p_y, float portee, double angle1, double angle2) {
		double pb_x = p_x + portee;
		double pb_y = p_y;
		double p1_x = (pb_x * Math.cos(angle1)) - (pb_y * Math.sin(angle1));
		double p1_y = (pb_x * Math.sin(angle1)) + (pb_y * Math.cos(angle1));
		double p2_x = (pb_x * Math.cos(angle2)) - (pb_y * Math.sin(angle2));
		double p2_y = (pb_x * Math.sin(angle2)) + (pb_y * Math.cos(angle2));
		Position p1 = new Position((float) p1_x, (float) p1_y);
		Position p2 = new Position((float) p2_x, (float) p2_y);
		Position p3 = new Position((float) p_x, (float) p_y);
		List<Position> list_points = new ArrayList();
		list_points.add(p1);
		list_points.add(p2);
		list_points.add(p3);
		Polygon polygon = new Polygon(list_points);
		return polygon;
	}

	public void eval_angle(Absolute_Orientation d, double angle1, double angle2) {
		switch (d.get_abs_Orientation()) {
		case Absolute_Orientation.EAST:
			angle1 = 337.5;
			angle2 = 22.5;
			break;
		case Absolute_Orientation.SOUTH_E:
			angle1 = 22.5;
			angle2 = 67.5;
			break;
		case Absolute_Orientation.SOUTH:
			angle1 = 67.5;
			angle2 = 112.5;
			break;
		case Absolute_Orientation.SOUTH_W:
			angle1 = 112.5;
			angle2 = 157.5;
			break;
		case Absolute_Orientation.WEST:
			angle1 = 157.5;
			angle2 = 202.5;
			break;
		case Absolute_Orientation.NORTH_W:
			angle1 = 202.5;
			angle2 = 247.5;
			break;
		case Absolute_Orientation.NORTH:
			angle1 = 247.5;
			angle2 = 292.5;
			break;
		case Absolute_Orientation.NORTH_E:
			angle1 = 292.5;
			angle2 = 337.5;
			break;
		default:
			System.out.print("Aucune Orientation !\n");
			// throw new Exception();
		}
	}

	public void set_key(String string) {
		if (!list_touche.contains(string)) {
			list_touche.add(string);
		}
	}

	public void suppr_key(String string) {
		list_touche.remove(string);
	}

	public long get_timer() {
		return TIMER;
	}

	public boolean do_hit(Absolute_Orientation o, String type, int porte, Entity e) {
		double p_x = e.get_x();
		double p_y = e.get_y();
		double angle1 = 0, angle2 = 0;
		eval_angle(o, angle1, angle2);
		Polygon polygon = create_polygon_direction(p_x, p_y, porte, angle1, angle2);
		for (Entity entity : entities) {
			if (entity.getHitBox().get_polygon().intersectsWith(polygon)) {
				entity.get_injured();
				if (entity.get_state_action() == ActionType.EXPLODE) {
					addToRemove(entity);
				}
			}
		}

		return true;
	}

	public void addToRemove(Entity entity) {
		entityToRemove.add(entity);
	}

	public String from_rel_to_abs_orientation(Absolute_Orientation abs, Relative_Orientation rel) {
		switch (rel.rel_or) {
		case "H":
			return abs.get_abs_Orientation();
		case "F":
			return abs.get_abs_Orientation();
		case "B":
			switch (abs.abs_or) {
			case "N":
				return "S";
			case "NE":
				return "SW";
			case "E":
				return "W";
			case "SE":
				return "NW";
			case "S":
				return "N";
			case "SW":
				return "NE";
			case "W":
				return "E";
			case "NW":
				return "SE";
			}
		case "R":
			switch (abs.abs_or) {
			case "N":
				return "E";
			case "NE":
				return "SE";
			case "E":
				return "S";
			case "SE":
				return "SW";
			case "S":
				return "W";
			case "SW":
				return "NW";
			case "W":
				return "N";
			case "NW":
				return "NE";
			}
		case "L":
			switch (abs.abs_or) {
			case "N":
				return "W";
			case "NE":
				return "NW";
			case "E":
				return "N";
			case "SE":
				return "NE";
			case "S":
				return "W";
			case "SW":
				return "SE";
			case "W":
				return "S";
			case "NW":
				return "SW";
			}
		}
		return null;
	}

	public boolean eval_cell(Absolute_Orientation dir, Category cat, int porte, Entity e) {

		double p_x = e.get_x();
		double p_y = e.get_y();
		double angle1 = 0, angle2 = 0;
		eval_angle(dir, angle1, angle2);
		Polygon polygon = create_polygon_direction(p_x, p_y, porte, angle1, angle2);
		for (Entity entity : entities) {
			if (entity.getHitBox().get_polygon().intersectsWith(polygon)) {
				return true;
			}
		}
		return false;
	}

	public boolean isValidPosition(Entity entity, Position newPosition) {

		for (Entity e : entities) {
			if (e.equals(entity)) {
				continue;
			}

			if (e.getHitBox().get_polygon().containsPosition(newPosition)
					|| (e.get_x() == newPosition.getPositionX() && e.get_x() == newPosition.getPositionX())) {
				return false;
			}
		}

		return inBounds(newPosition.getPositionX(), newPosition.getPositionY(), entity);
	}

	public boolean inBounds(float x, float y, Entity e) {
		if (x >= 0 && x <= m_map.getBorders().getMaxX() && y >= 0 && y <= m_map.getBorders().getMaxY()) {
			return true;
		} else {
			addToRemove(e);
			return false;
		}
	}

	public Entity get_entity_at(float checkX, float checkY) {
		for (Entity e : entities) {
			if (e.get_x() == checkX && e.get_y() == checkY)
				return e;
		}
		return null;
	}
}
