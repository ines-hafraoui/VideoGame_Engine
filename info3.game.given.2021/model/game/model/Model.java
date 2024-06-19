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

import java.util.HashMap;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import game.entity.Entity;
import game.automaton.Automate;
import game.entity.Absolute_Orientation;
import game.entity.Base;
import game.entity.Bot;
import game.entity.Entity;
import game.entity.EntityType;
import game.entity.HitBox;
import game.entity.Item;
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
	public java.util.Map<String, java.util.Map<String, Object>> entityConfigurations;
	public List<Entity> entities;
	IFactory factory;
	public static int nb_bot_init;
	public int timer; 
	public boolean cooperative;
	public int viscosity;
	public Parser configParse;

	
	public Model(int w, int h, Parser parse)throws IOException {
		
		m_width = w;
		height = h;
		configParse = parse;
		nb_bot_init = parse.nb_bot_init;
		viscosity = parse.viscosity;
		cooperative = parse.coop;
		timer = parse.timer;
		
		entities = new ArrayList<Entity>();
		entityConfigurations = parse.entities;
		
		// create all entities from the info that gave us the Parser
		
		 for (java.util.Map.Entry<String, java.util.Map<String, Object>> entry : entityConfigurations.entrySet()) {
	            String entityName = entry.getKey();
	            java.util.Map<String, Object> properties = entry.getValue();

	            // Extract common properties
	            String direction = (String) properties.get("direction");
	            Position pos = (Position) properties.get("position");
	            int team = ((Number) properties.get("team")).intValue();
	            int view = ((Number) properties.get("view")).intValue();
	            boolean pickable = (Boolean) properties.get("pickable");
	            String behaviour = (String) properties.get("behaviour");
	            String sprite = (String) properties.get("sprite");
	            HitBox hb = (HitBox) properties.get("hitbox");
	            
	            Entity entity;
	            switch (entityName) {
	            case "Player1":
	            case "Player2":
	            	entity = new Player(this,pos, new Absolute_Orientation(direction), team, nb_bot_init, view, pickable,hb);
                    break;
	            case "Bot1":
	            case "Bot2":
	            case "Parasite":
	            case "Dasher":
	            case "Arsher":
	            	entity = new Bot(this,pos, new Absolute_Orientation(direction), team, 0, view, pickable,hb);
                    break;
	            case "Base1":
	            case "Base2":
	            case "Base":
	            	entity = new Base(this,pos, new Absolute_Orientation(direction), team, 0, view, pickable,hb);
                    break;
	            case "Power":
	            case "Capacity":
	            case "Plant" : 
	            	entity = new Item(this,pos, new Absolute_Orientation(direction), team, 0, view, pickable,hb);
                    break;
                default : 
                	entity = null;
                	break;
	            }
	            
	            if (entity != null) {
	            	if (behaviour != null) {
	            		String galPath = new File("/gal/gal/"+ behaviour).getAbsolutePath();
		        		Automate automate = TestMain.loadAutomata(galPath);
		        		
		        		entity.set_automate(automate);
		        		entities.add(entity);
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
		case "NE" : 
			newY += porte;
			newX += porte;
			break;
		case "NW" : 
			newY += porte;
			newX -= porte;
			break;
		case "SE" : 
			newY -= porte;
			newX += porte;
			break;
		case "SW" : 
			newY -= porte;
			newX -= porte;
			break;
		default : 
			break;
		}
		
		for (Entity entity : entities) {
			 if (isWithinHitbox(newX, newY, entity)) {
		            entity.get_injured();
		            return true;
		        }
		}
		return false;
	}

	private boolean isWithinHitbox(float x, float y, Entity entity) {
		float entityX = entity.get_x();
	    float entityY = entity.get_y();
	    float hitboxWidth = entity.getHitBox().getHbWidth(); // Largeur de la hitbox
	    float hitboxHeight = entity.getHitBox().getHbHeight(); // Hauteur de la hitbox

	    return (x >= entityX && x <= entityX + hitboxWidth) && 
	           (y >= entityY && y <= entityY + hitboxHeight);
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
	public List<Entity> get_players() {
		return entities;
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
