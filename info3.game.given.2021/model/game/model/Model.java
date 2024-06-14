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
import game.map.Plot;
import game.map.Polygon;
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
	private Grid m_grid;
	private Absolute_Orientation m_orientation;
	List<Entity> entities;
	IFactory factory;

	public Model(Grid grid, int w, int h, IFactory f) throws IOException {
		entities = new ArrayList<Entity>();
		factory = f;
	}
	//private Orientation m_orientation;
	public Map m_map;

	public Model(Grid grid, int w, int h) throws IOException {
		m_grid = grid;
		List<Position> poss = new ArrayList<>();
		Position pos1=new Position(0,0);
		Position pos2=new Position(0,1000);
		Position pos3=new Position(1000,0);
		Position pos4=new Position(1000,1000);
		
		poss.add(pos1);
		poss.add(pos2);
		poss.add(pos3);
		poss.add(pos4);
		
		Polygon p=new Polygon(poss);
		m_map=new Map(p);
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

	public Grid get_grid() {
		return m_grid;
	}

	public void tick(long elapsed) {
		m_imageElapsed += elapsed;
		if (m_imageElapsed > 200) {
			m_imageElapsed = 0;
		}

		m_grid.tick(elapsed);
	}

	public void paint(Graphics g, int width, int height) {
		m_width = width;
		System.out.println("orientation :" + m_orientation.orientation + "\n");
	}

	public Orientation getOrientation() {
		return m_orientation;
	}

	public Entity get_entity(int distance, String t) {
		Entity e = m_grid.get_entity(distance, t);
		return e;
	}
	
	public void add_entity(Entity e) {
		entities.add(e);
	}

	public boolean inflict_hit(Absolute_Orientation o, int porte, String t) {
		// TODO Auto-generated method stub
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

	
	public interface ModelListener {
		 void addedEntity(Entity e);
		 void removedEntity(Entity e);
	}
	
	ModelListener m_ml;
	public void setListener(ModelListener l) {
		m_ml=l;
	}
}
