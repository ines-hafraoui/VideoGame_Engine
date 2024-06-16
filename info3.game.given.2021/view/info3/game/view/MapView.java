package info3.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;

import game.entity.Position;
import game.map.Biome;
import game.map.Map;
import game.map.Plot;
import game.model.Model;
import game.map.Polygon;

public class MapView {

	private int x_max;
	private int y_max;
	private int x;
	private int y;
	private Model m_model;
	Viewport m_parent;

	public MapView(int x, int y, Model model, Viewport parent) {
		x_max = x;
		y_max = y;
		m_model = model;
		m_parent = parent;
	}

	public void paint(Graphics g) {
		List<Biome> b = m_model.m_map.getBiome();
		Iterator<Biome> iterator = b.iterator();
		while (iterator.hasNext()) {
			Biome biome = iterator.next();
			AffichageBiome(g, biome);
		}
	}

	private void AffichageBiome(Graphics g, Biome biome) {
		Polygon p = biome.getBorders();
		// Dessiner le polygone
//		if (biome.getName().equals("volcan")) {
//			g.setColor(Color.RED);
//			g.fillPolygon(Polygon_coordX(p), Polygon_coordY(p), p.getVertices().size());
//			AffichagePlots(g, biome);
//		}
//		if (biome.getName().equals("jungle")) {
//			g.setColor(Color.GREEN);
//			g.fillPolygon(Polygon_coordX(p), Polygon_coordY(p), p.getVertices().size());
//			AffichagePlots(g, biome);
//		} else {
		
		
		
		g.setColor(Color.BLACK);
		g.fillPolygon(Polygon_coordX(p), Polygon_coordY(p), p.getVertices().size());
//			AffichagePlots(g, biome);
//		}
	}

	private void AffichagePlots(Graphics g, Biome biome) {
		List<Plot> plots = biome.getPlots();
		Iterator<Plot> iterator = plots.iterator();
		while (iterator.hasNext()) {
			Plot plot = iterator.next();
			Polygon p = plot.getBorders();
			g.setColor(Color.CYAN);
			g.fillPolygon(Polygon_coordX(p), Polygon_coordY(p), p.getVertices().size());
		}

	}

	private int[] Polygon_coordX(Polygon p) {
		List<Position> l = p.getVertices();
		int[] tab_x = new int[l.size()];
		for (int i = 0; i < l.size(); i++) {
			Position pos = l.get(i);
			tab_x[i] = (int) (pos.getPositionX()*View.MAPDISPLAYSCALE);
		}
		return tab_x;
	}

	private int[] Polygon_coordY(Polygon p) {
		List<Position> l = p.getVertices();
		int[] tab_y = new int[l.size()];
		for (int i = 0; i < l.size(); i++) {
			Position pos = l.get(i);
			tab_y[i] = (int) (pos.getPositionY()*View.MAPDISPLAYSCALE);
		}
		return tab_y;
	}
}
