package info3.game.view;

import java.awt.Graphics;

import game.model.Model;

public class Map {

private int x_max;
private int y_max;
private int x;
private int y;
private Model m_model;


	public Map(int x, int y, Model model) {
		x_max=x;
		y_max=y;
		m_model=model;
	}
	
	
	public void paint(Graphics g) {
		Polygon p;
		g.drawPolygon();
	}
}
