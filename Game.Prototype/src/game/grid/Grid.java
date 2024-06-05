package game.grid;

import java.util.ArrayList;
import java.util.List;

import game.entity.Entity;

public class Grid {
	
	private int height; 
	private int width;
	private int nb_case; 
	private List<Entity> entities;
	
	
	
	public Grid(int h, int w, int nbcase) {
		height = h; 
		width = w; 
		nb_case = nbcase; 
		entities = new ArrayList<>();
	}
	
	public Grid resize(int h, int w, int nbcase) {
		//TODO
		return null;
		
	}

}
