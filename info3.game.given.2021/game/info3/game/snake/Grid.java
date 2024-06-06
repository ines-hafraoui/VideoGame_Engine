package info3.game.snake;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import game.automaton.Direction;
import game.entity.Apple;
import game.entity.Entity;
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
		//TODO
		return null;
	}
	

	public void paint(Graphics g) {
		Color[] boxcolor = GridEltPos();
		int box_width = (m_width + m_border) / m_nboxline;
		int box_height = (m_height - m_border) / m_nboxcol;
		for (int i = 0; i < m_nboxcol; i++) {
			for (int j = 0; j < m_nboxline; j++) {
				if(boxcolor.length > i*m_nboxline +j && (boxcolor[i*m_nboxline +j] == null))
					g.setColor(Color.DARK_GRAY);
				else
					g.setColor(Color.GREEN);
				
				g.fillRect(m_x + j * box_width + m_border, m_y + i * box_height + m_border, box_width - (m_border * 2),
						box_height - m_border);
			}
		}
	}
	
	
	//METHODE returns an array of colors
	Color[] GridEltPos() {
		Color[] colorpos = new Color[entities.size()];
		//un itérateur sur les élements, on cherche à ce que chacun des elements nous renvoie sa position
	
		return colorpos;
	}
	
	public char eval(Direction d, int x, int y, Orientation o) {
		int targetX = x; 
		int targetY = y; 
		
	
		switch(o.getOrientation()) {
		case 'N' : 
			switch(d.getDirection()) {
			case 'F':
				targetY--; 
				break; 
			case 'R':
				targetX ++;
				break; 
			case 'L': 
				targetX--;
				break; 
			}
			break;
		case 'S':
			switch(d.getDirection()) {
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
			switch(d.getDirection()) {
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
			switch(d.getDirection()) {
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
		
		for(Entity e : entities) {
			if (e instanceof Apple) {
				if (e.get_y() == targetY && e.get_x() == targetX) return 'A';
			}
			else if(e instanceof Snake){
				if (e.get_y() == targetY && e.get_x() == targetX) return 'S';
			}else {
				if (e.get_y() == targetY && e.get_x() == targetX) return 'E';
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
