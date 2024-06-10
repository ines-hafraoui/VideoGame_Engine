package info3.game.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.automaton.Direction;
import game.entity.Apple;
import game.entity.Block;
import game.entity.Entity;
import game.entity.Head;
import game.entity.Orientation;
import game.entity.Snake;

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

	public char eval(Direction d, int x, int y, Orientation o) {
		int targetX = x;
		int targetY = y;

		switch (o.getOrientation()) {
		case 'N':
			switch (d.getDirection()) {
			case 'F':
				targetY--;
				break;
			case 'R':
				targetX++;
				break;
			case 'L':
				targetX--;
				break;
			}
			break;
		case 'S':
			switch (d.getDirection()) {
			case 'F':
				targetY++;
				break;
			case 'R':
				targetX++;
				break;
			case 'L':
				targetX--;
				break;
			}
			break;
		case 'E':
			switch (d.getDirection()) {
			case 'F':
				targetX++;
				break;
			case 'R':
				targetY++;
				break;
			case 'L':
				targetY--;
				break;
			}
			break;
		case 'W':
			switch (d.getDirection()) {
			case 'F':
				targetX--;
				break;
			case 'R':
				targetY--;
				break;
			case 'L':
				targetY++;
				break;
			}
			break;
		}

		for (Entity e : getEntities()) {
			if (e instanceof Apple) {
				if (e.get_y() == targetY && e.get_x() == targetX)
					return 'A';
			} else if (e instanceof Snake) {
				if (e.get_y() == targetY && e.get_x() == targetX)
					return 'S';
			} else {
				if (e.get_y() == targetY && e.get_x() == targetX)
					return 'E';
			}
		}
		return 'X';

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

}
