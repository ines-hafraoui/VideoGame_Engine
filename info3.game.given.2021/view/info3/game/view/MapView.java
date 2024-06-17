package info3.game.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import game.entity.Position;
import game.map.Biome;
import game.map.Plot;
import game.map.Polygon;
import game.model.Model;

public class MapView {

	private int x_max,y_max;
	private int[] m_groundsetup;
	private int m_ncols,m_nrows;
	private Model m_model;
	Viewport m_parent;
	View m_view;
	protected BufferedImage[] m_bgimages;

	public MapView(int x, int y, Model model, View v) {
		x_max = x;
		y_max = y;
		m_model = model;
		m_view = v;
		try {
			m_bgimages = View.loadSprite("resources/MiniWorldSprites/Ground/TexturedGrass.png", 2, 3);
		} catch (IOException e) {
			e.printStackTrace();
		}
		genDefaultGround();
		
	}

	public void paint(Graphics g) {
		//printing all the basic tiles to be optimized
		for (int i = 0; i < m_ncols; i ++) {
			for (int j = 0; j < m_nrows; j ++) {
				BufferedImage img = m_bgimages[m_groundsetup[i*j+j]];
				g.drawImage(img, j*(m_bgimages[0].getWidth() * View.DISPLAYSCALE), i*(m_bgimages[0].getHeight() * View.DISPLAYSCALE), img.getWidth() * View.DISPLAYSCALE, img.getHeight() * View.DISPLAYSCALE, null);
			}
		}
		
		List<Biome> b = m_model.m_map.getBiome();
		Iterator<Biome> iterator = b.iterator();
		while (iterator.hasNext()) {
			Biome biome = iterator.next();
			AffichageBiome(g, biome);
		}
	}
	
	public void paint(Graphics g, Rectangle bounds) {
		//printing all the basic tiles to be optimized
		
		int h = m_bgimages[0].getHeight() * View.DISPLAYSCALE;
		int w = m_bgimages[0].getWidth() * View.DISPLAYSCALE;
		for (int i = 0; i < m_ncols; i++) {
//			System.out.println(""+bounds.height+bounds.y +"   "+ i*w);
//			if((bounds.width+bounds.x) >= i*w) {
				for (int j = 0; j < m_nrows; j ++) {
//					System.out.println(""+bounds.height+bounds.y);
//					if((bounds.height+bounds.y) >= j*h) {
						BufferedImage img = m_bgimages[m_groundsetup[i*j+j]];
						g.drawImage(img, j*w, i*h, w, h, null);
//					}
//					else {
//						break;
//					}
				}
//			}
//			else {
//				break;
//			}
		}
		
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
			tab_x[i] = (int) (pos.getPositionX() * View.DISPLAYSCALE);
		}
		return tab_x;
	}

	private int[] Polygon_coordY(Polygon p) {
		List<Position> l = p.getVertices();
		int[] tab_y = new int[l.size()];
		for (int i = 0; i < l.size(); i++) {
			Position pos = l.get(i);
			tab_y[i] = (int) (pos.getPositionY() * View.DISPLAYSCALE);
		}
		return tab_y;
	}

	public void genDefaultGround() {
		BufferedImage ref = m_bgimages[0];
		m_nrows =  m_view.m_mheight/(ref.getHeight()/2 * View.DISPLAYSCALE);
		m_ncols =  m_view.m_mwidth/(ref.getWidth()/2 * View.DISPLAYSCALE);
		m_groundsetup = new int[m_nrows*m_ncols];
		for (int i = 0; i <m_nrows; i ++) {
			for (int j = 0; j <m_ncols; j ++) {
				m_groundsetup[i*j+j] = m_view.getRandomNumber(0, m_bgimages.length);	
			}
		}
	}

}
