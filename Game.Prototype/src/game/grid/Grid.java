package game.grid;

import java.util.ArrayList;
import java.util.List;

import game.automaton.Category;
import game.automaton.Direction;
import game.entity.Entity;
import game.entity.Orientation;

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
	
	public boolean eval(Direction d, Category c, int x, int y, Orientation o) {
		
	
		switch(o.getOrientation()) {
		case 'N' : 
			switch(d.getDirection()) {
			case 'F':
				for (Entity e : entities) {
				}
				break; 
			case 'R':
				for (Entity e : entities) {
				}
				break; 
			case 'L': 
				for (Entity e : entities) {
				}
				break; 
			}
			check_orientation();
			
			}
	
		return false;
	}
	
	public boolean check_orientation() {
		return true;
	}

}
