package info3.game.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.entity.Position;
import game.map.Biome;
import game.map.Plot;
import game.map.Polygon;
import game.map.Biomes.Ocean;
import game.map.Biomes.Volcano;
import game.model.Model;
import info3.game.Game;

public class MapView {

	private int x_max, y_max;
	private int[] m_groundsetup;
	private int m_ncols, m_nrows;
	private Model m_model;
	AViewport m_parent;
	View m_view;
	protected BufferedImage[] m_bgimages;
	protected BufferedImage[] m_textureimages;

	private List<Squares> squares = new ArrayList<>(); // List to store the biomes

	public MapView(int x, int y, Model model, View v, AViewport parent) {
		x_max = x;
		y_max = y;
		m_model = model;
		m_view = v;
		m_parent = parent;

		m_bgimages = Game.configParse.bg_sprite;
		m_textureimages = View.loadSprite("resources/MiniWorldSprites/Ground/Grass.png", 1, 5);
		m_nrows = (m_view.m_mheight / (m_bgimages[0].getHeight() * View.DISPLAYSCALE));
		m_ncols = (m_view.m_mwidth / (m_bgimages[0].getWidth() * View.DISPLAYSCALE));
		if(Game.configParse.bg_rand) {
			/* Generates the default ground tiles */
			genDefaultGround();
		}
		

		/* Generates the different views of the biomes */
		List<Biome> b = m_model.m_map.getBiome();
		Iterator<Biome> iterator = b.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			Biome bio = iterator.next();
			Polygon p = bio.getBorders();

			if (bio instanceof Volcano) {
				squares.add(new Squares(p, m_textureimages[4], m_parent));
			} else if (bio instanceof Ocean) {
				squares.add(new Squares(p, m_textureimages[0], m_parent));
			} else {
				squares.add(new Squares(p, m_textureimages[1], m_parent));

			}
		}
	}

	public void paint(Graphics g, int x, int y) {
//		g.translate(0, 0);
		// printing all the basic tiles to be optimized
		if(Game.configParse.bg_rand) {
			for (int i = 0; i < m_ncols; i++) {
				for (int j = 0; j < m_nrows; j++) {
					BufferedImage img = m_bgimages[m_groundsetup[i * j + j]];
					int tilex = i * (m_bgimages[0].getWidth() * View.DISPLAYSCALE) + x;
					int tiley = j * (m_bgimages[0].getHeight() * View.DISPLAYSCALE) + y;
					if (m_parent.withinbounds(tilex, tiley)) {
						g.drawImage(img, tilex, tiley, img.getWidth() * View.DISPLAYSCALE,
								img.getHeight() * View.DISPLAYSCALE, null);
					}
				}
			}
		}
		else {
			int spritenb = 0;
			for (int i = 0; i < m_ncols; i++) {
				for (int j = 0; j < m_nrows; j++) {
					if(spritenb > m_bgimages.length-1) {
						spritenb = 0;
					}
					BufferedImage img = m_bgimages[spritenb];
					int tilex = i * (m_bgimages[0].getWidth() * View.DISPLAYSCALE) + x;
					int tiley = j * (m_bgimages[0].getHeight() * View.DISPLAYSCALE) + y;
					if (m_parent.withinbounds(tilex, tiley)) {
						g.drawImage(img, tilex, tiley, img.getWidth() * View.DISPLAYSCALE,
								img.getHeight() * View.DISPLAYSCALE, null);
					}
					spritenb++;
				}
			}
		}
		

		for (Squares square : squares) {
			square.paint(g, x, y);
		}
	}

	// TO BE MOVED... UNTIL WE GET THE FINAL VERSION OF PLOTS
	private void AffichagePlots(Graphics g, List<Plot> L_plot) {
		Iterator<Plot> iterator = L_plot.iterator();
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
	// END OF TO BE MOVED...

	/*
	 * Here we generate the default tiling of the ground for the entire world
	 */
	public void genDefaultGround() {
		BufferedImage ref = m_bgimages[0];
		m_groundsetup = new int[m_nrows * m_ncols];
		for (int i = 0; i < m_ncols; i++) {
			for (int j = 0; j < m_nrows; j++) {
				m_groundsetup[i * j + j] = getRandomNumber(0, m_bgimages.length);
			}
		}
	}

	public static int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}
}
