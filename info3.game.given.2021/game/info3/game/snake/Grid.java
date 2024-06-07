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

	int m_x, m_y, m_width, m_height;
	int m_nboxline, m_nboxcol;
	int m_border;
	private List<Entity> entities;

	public Grid(int x, int y, int width, int height, int nboxline, int nboxcol) {
		m_x = x;
		m_y = y;
		m_width = width;
		m_height = height;
		m_nboxline = nboxline;
		m_nboxcol = nboxcol;
		m_border = 0;
		entities = new ArrayList<Entity>();
	}

	public void setBorder(int border) {
		m_border = border;
	}

	public Grid resize(int h, int w, int nbcase) {
		// TODO
		return null;
	}

	public void paint(Graphics g) {
		int box_width = (m_width + m_border) / m_nboxline;
		int box_height = (m_height - m_border) / m_nboxcol;
		for (int i = 0; i < m_nboxcol; i++) {
			for (int j = 0; j < m_nboxline; j++) {
				g.setColor(Color.GREEN);
				g.fillRect(m_x + j * box_width + m_border, m_y + i * box_height + m_border, box_width - (m_border * 2),
						box_height - m_border);
			}
		}

		for (Entity e : entities) {
			
			Entity[] eList = e.get_entity();
			
			for (Entity e_e : eList) {
				
				g.setColor(Color.DARK_GRAY);
				g.fillRect(m_x + e_e.get_x() * box_width + m_border,m_y + e_e.get_y() * box_height + m_border, box_width - (m_border * 2),
						box_height - m_border);

			}
		}
	}

	
	//METHODE returns an array of colors
	Color[] GridEltPos() {
		Color[] colorpos = new Color[m_nboxcol*m_nboxline];
		//un itérateur sur les élements, on cherche à ce que chacun des elements nous renvoie sa position
		Iterator<Entity> iter = entities.iterator();
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

		for (Entity e : entities) {
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
		entities.add(e);
	}

	public void tick(long elapsed) {
		for (Entity e : entities) {
			e.tick(elapsed);
		}
	}

}
