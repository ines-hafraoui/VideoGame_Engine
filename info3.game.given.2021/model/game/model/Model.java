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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import game.entity.Entity;
import game.entity.Absolute_Orientation;
import game.entity.Entity;
import game.entity.EntityType;
import game.entity.Player;
import game.entity.Position;
import game.map.Map;
import game.map.Polygon;
import info3.game.Grid;
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
	List<Entity> entities;
	Entity[] players;
	IFactory factory;


	public Model(Map map, int w, int h, IFactory f) throws IOException {
		entities = new ArrayList<Entity>();
		factory = f;
		m_width = w;
		height = h;
		List<Position> poss = new ArrayList<Position>();
		players = new Entity[1];
		Absolute_Orientation ao = new Absolute_Orientation(Absolute_Orientation.WEST);
		Entity e = factory.newEntity(this, new Position(500, 200),ao , EntityType.PLAYER);
		players[0] = e;
//		Entity e1 = factory.newEntity(this, new Position(900, 400),ao, EntityType.PLAYER);
//		players[1] = e1;
		Position pos1 = new Position(0, 0);
		Position pos2 = new Position(0, h);
		Position pos3 = new Position(w, 0);
		Position pos4 = new Position(w, h);
		
		poss.add(pos1);
		poss.add(pos2);
		poss.add(pos3);
		poss.add(pos4);

		Polygon p = new Polygon(poss);
		m_map = new Map(p);
		m_map.generateMap(m_map.getSeed());

	}

	public Model(Grid grid, int w, int h) throws IOException {
		m_width = w;
		height = h;
		List<Position> poss = new ArrayList<>();
		Position pos1 = new Position(0, 0);
		Position pos2 = new Position(0, h);
		Position pos3 = new Position(w, 0);
		Position pos4 = new Position(w, h);

		poss.add(pos1);
		poss.add(pos2);
		poss.add(pos3);
		poss.add(pos4);

		Polygon p = new Polygon(poss);
		m_map = new Map(p);
		m_map.generateMap(m_map.getSeed());

//		m_orientation = new Orientation('H');
//		Head s_head = new Head(new Orientation('S'), this.get_grid(), new Position(0, 0));
//		Snake snake = new Snake(null, this.get_grid(), s_head);
//		Apple apple = new Apple(this.get_grid(), new Position(5, 5), null);
//
//		// AUTOMATON
//		State s0 = new State();
//		State s1 = new State();
//
//		Condition t = new TrueFalse(true);
//
//		List<Action> LA = new ArrayList<Action>();
//		LA.add(new Move(apple));
//
//		Transition t01 = new Transition(s1, t, LA);
//		Transition t10 = new Transition(s0, t, LA);
//
//		s0.add_transition(t01);
//		s1.add_transition(t10);
//
//		Automate a_aut = new Automate(apple);
//		a_aut.add_state(s0);
//		a_aut.add_state(s1);
//
//		a_aut.addCurrentState(s0);
//
//		apple.set_automate(a_aut);
//
//		get_grid().add_entity(snake);
//		get_grid().add_entity(apple);

	}


	public Map get_map() {
		return m_map;
	}

	public void tick(long elapsed) {
		m_imageElapsed += elapsed;
		if (m_imageElapsed > 200) {
			m_imageElapsed = 0;
		}

		m_map.tick(elapsed);
	}


	public Entity get_entity(int distance, String t, float f, float g) {
		Entity e = m_map.get_entity(distance, t, f, g);
		return e;
	}

	public void add_entity(Entity e) {
		entities.add(e);
	}

	public boolean inflict_hit(Absolute_Orientation o, int porte, String t, float currentx, float currenty) {
		float newX = currentx; 
		float newY = currenty;
		
		switch (o.get_abs_Orientation()) {
		case "N" :
			newY += porte;
			break;
		case "S" : 
			newY -= porte;
			break;
		case "W" : 
			newX -= porte;
			break; 
		case "E" : 
			newX += porte;
			break;
		default : 
			break;
		}
		
		for (Entity entity : entities) {
			if (entity.get_type().equals(t)) {
				if (entity.get_x() == newX && entity.get_y() == newY) {
					entity.get_injured();
					return true;
				}	
			}
		}
		return false;
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

	public Entity newEntity(Model model, Position position, Absolute_Orientation abs_or, String arrow) {

		return factory.newEntity(model, position, abs_or, arrow);
	}

	public interface ModelListener {
		void addedEntity(Entity e) throws IOException;

		void removedEntity(Entity e);
	}

	ModelListener m_ml;

	public void setListener(ModelListener l) {
		m_ml = l;
	}

	public int get_width() {
		return m_width;
	}

	public int get_height() {
		return height;
	}

}
