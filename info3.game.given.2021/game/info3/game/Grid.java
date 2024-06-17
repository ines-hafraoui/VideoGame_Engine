package info3.game;

import java.awt.Color;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.automaton.Direction;
import game.automaton.Relative_Orientation;

import game.entity.Entity;

import game.entity.Absolute_Orientation;


public class Grid {

	int m_nboxline, m_nboxcol;
	private List<Entity> entities;

	public Grid(int nboxline, int nboxcol) {
		m_nboxline = nboxline;
		m_nboxcol = nboxcol;
		entities = new ArrayList<Entity>();
	}


	public Grid resize(int h, int w, int nbcase) {
		// TODO
		return null;
	}

	
	//METHODE returns an array of colors
	Color[] GridEltPos() {
		Color[] colorpos = new Color[getnboxcol()*getnboxline()];
		//un itérateur sur les élements, on cherche à ce que chacun des elements nous renvoie sa position
		Iterator<Entity> iter = getEntities().iterator();
		while(iter.hasNext()) {
			Entity e = iter.next();
			if(e instanceof Apple)
				colorpos[e.get_x()*e.get_y()] = Color.GREEN;
			if(e instanceof Head || e instanceof Block) 
				colorpos[e.get_x()*e.get_y()] = Color.BLUE;
		}
		return colorpos;
	}

	public boolean eval_abs(Absolute_Orientation d, float x, float y, int porte) {
		return false;
		
	}
	
	public boolean eval_rel(Relative_Orientation d, float x, float y, int porte) {
		return false;
	}

	public void add_entity(Entity e) {
		getEntities().add(e);
	}

	public void tick(long elapsed) {
		for (Entity e : getEntities()) {
			e.tick(elapsed);
		}
	}


	public int getnboxline() {
		return m_nboxline;
	}


	public int getnboxcol() {
		return m_nboxcol;
	}


	public List<Entity> getEntities() {
		return entities;
	}


	public Entity get_entity(int distance, String t) {
		// TODO Auto-generated method stub
		return null;
	}

}
