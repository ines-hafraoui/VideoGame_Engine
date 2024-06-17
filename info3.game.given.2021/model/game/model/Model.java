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

import java.awt.Graphics;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import game.automaton.Action;
import game.automaton.Automate;
import game.automaton.Category;
import game.automaton.Condition;
import game.automaton.Direction;
import game.automaton.Move;
import game.automaton.State;
import game.automaton.Transition;
import game.automaton.TrueFalse;

import game.entity.Base;
import game.entity.Entity;

import game.entity.Absolute_Orientation;
import game.entity.Position;
import game.map.Map;
import info3.game.Grid;
import info3.game.IFactory;
import info3.game.avatar.Avatar;
import info3.game.view.View;

/**
 * A simple class that holds the images of a sprite for an animated cowbow.
 *
 */
public class Model {

	long m_imageElapsed;
	int m_width, height;
	private Map m_map;
	private Absolute_Orientation m_orientation;
	List<Entity> entities;
	IFactory factory;

	public Model(Map map, int w, int h, IFactory f) throws IOException {
		entities = new ArrayList<Entity>();
		factory = f;
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

	public void paint(Graphics g, int width, int height) {
		m_width = width;
		System.out.println("orientation :" + m_orientation.abs_or + "\n");
	}

	public Absolute_Orientation getOrientation() {
		return m_orientation;
	}

	public Entity get_entity(int distance, String t, float f, float g) {
		Entity e = m_map.get_entity(distance, t, f, g);
		return e;
	}
	
	public void add_entity(Entity e) {
		entities.add(e);
	}

	public boolean inflict_hit(Absolute_Orientation o, int porte, String t, int currentx, int currenty) {
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

	public Entity newEntity(Model model, Position position, Absolute_Orientation abs_or, String arrow) {
	
		return factory.newEntity(model, position, abs_or, arrow);
	}

}
