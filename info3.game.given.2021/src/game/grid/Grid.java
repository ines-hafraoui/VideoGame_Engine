package game.grid;

import java.util.ArrayList;

import java.util.List;

import game.automaton.Category;
import game.automaton.Direction;
import game.entity.Apple;
import game.entity.Entity;
import game.entity.Orientation;
import game.entity.Snake;

public class Grid {
	
	private int nb_ligne;
	private int nb_colonne;
	private int nb_case;
	private List<Entity> entities;
	
	
	
	public Grid(int c, int l) {
		nb_colonne = c; 
		nb_ligne = l; 
		nb_case = l*c; 
		entities = new ArrayList<>();
	}
	
	public Grid resize(int h, int w, int nbcase) {
		//TODO
		return null;
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
				if (e.y == targetY && e.x == targetX) return 'A';
			}
			else if(e instanceof Snake){
				if (e.y == targetY && e.x == targetX) return 'S';
			}else {
				if (e.y == targetY && e.x == targetX) return 'E';
			}
		}
		return 'X';
	}

}
