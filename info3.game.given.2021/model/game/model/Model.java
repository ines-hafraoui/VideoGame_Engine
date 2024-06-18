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
import game.automaton.Automate;
import game.entity.Absolute_Orientation;
import game.entity.Entity;
import game.entity.EntityType;
import game.entity.Player;
import game.entity.Position;
import game.map.Map;
import game.map.Polygon;
import info3.game.IFactory;
import gal.ast.export.*;
import gal.demo.test.TestMain;


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

	public static final int NB_BOT = 5;

	public Model(int w, int h, IFactory f) throws IOException {
		entities = new ArrayList<Entity>();
		factory = f;
		m_width = w;
		height = h;
		List<Position> poss = new ArrayList<Position>();
		players = new Entity[1];
		Absolute_Orientation ao = new Absolute_Orientation(Absolute_Orientation.WEST);
		Entity e = factory.newEntity(this, new Position(500, 200),ao , EntityType.PLAYER, Entity.TEAM1);
		List<Automate> fsm_list = (List<Automate>) TestMain.loadAutomata("/home/nada/Documents/INFO3/S2/Projet_Jeu/g3/info3.game.given.2021/gal/gal/automate.gal");
		//e.set_automate(fsm_list.get(0));
		players[0] = e;
		//Entity e1 = factory.newEntity(this, new Position(900, 400),ao, EntityType.PLAYER, Entity.TEAM1);
		//players[1] = e1;
		Entity e2 = factory.newEntity(this, new Position(600, 200), ao, EntityType.FIREBALL, Entity.TEAM1);
		entities.add(e2);
		Entity e3 = factory.newEntity(this, new Position(200, 200), ao, EntityType.TEAMMATE, Entity.TEAM1);
		e3.set_automate(fsm_list.get(0));
		entities.add(e3);
		Entity e4 = factory.newEntity(this, new Position(400, 400), ao, EntityType.BASE, Entity.TEAM1);
		entities.add(e4);
		Entity e5 = factory.newEntity(this, new Position(600, 600), ao, EntityType.ITEM, Entity.TEAM1);
		entities.add(e5);
		Position pos1 = new Position(0, 0);
		Position pos2 = new Position(0, h);
		Position pos3 = new Position(w, 0);
		Position pos4 = new Position(w, h);
		
		poss.add(pos1);
		poss.add(pos2);
		poss.add(pos3);
		poss.add(pos4);

		Polygon p = new Polygon(poss);
		m_map = new Map(p,this);
		m_map.generateMap(m_map.getSeed());

	}

	public Map getMap() {
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

	public Entity newEntity(Model model, Position position, Absolute_Orientation abs_or, String arrow, int team) {

		return factory.newEntity(model, position, abs_or, arrow, team);
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
