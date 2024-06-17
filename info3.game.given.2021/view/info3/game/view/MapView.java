package info3.game.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.entity.Position;
import game.map.Biome;
import game.map.Plot;
import game.map.Polygon;
import game.model.Model;

public class MapView {

	private int x_max, y_max;
	private int[] m_groundsetup;
	private int m_ncols, m_nrows;
	private Model m_model;
	Viewport m_parent;
	View m_view;
	protected BufferedImage[] m_bgimages;

	private BufferedImage[] textureImage;
	private List<Rectangle> squares = new ArrayList<>(); // List to store squares
	private static final int SQUARE_SIZE = 10 * View.DISPLAYSCALE; // Size of each square

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

		try {
			// Load the texture image
			textureImage = View.loadSprite("resources/MiniWorldSprites/Ground/Shore.png", 1, 5);
		} catch (IOException e) {
			e.printStackTrace();
		}

		List<Biome> b = m_model.m_map.getBiome();
		Iterator<Biome> iterator = b.iterator();
		while (iterator.hasNext()) {
			Biome biome = iterator.next();
			Polygon p = biome.getBorders();
			PolygontoTiles(p);
			;
		}

	}

	public void paint(Graphics g) {
		// printing all the basic tiles to be optimized
		for (int i = 0; i < m_ncols; i++) {
			for (int j = 0; j < m_nrows; j++) {
				BufferedImage img = m_bgimages[m_groundsetup[i * j + j]];
				g.drawImage(img, j * (m_bgimages[0].getWidth() * View.DISPLAYSCALE),
						i * (m_bgimages[0].getHeight() * View.DISPLAYSCALE), img.getWidth() * View.DISPLAYSCALE,
						img.getHeight() * View.DISPLAYSCALE, null);
			}
		}

		for (Rectangle square : squares) {
			g.drawImage(textureImage[2], square.x, square.y, SQUARE_SIZE, SQUARE_SIZE, null);
		}
	}
	
	
	public void paint(Graphics g, int x, int y) {
//		g.translate(0, 0);
		// printing all the basic tiles to be optimized
		for (int i = 0; i < m_ncols; i++) {
			for (int j = 0; j < m_nrows; j++) {
				BufferedImage img = m_bgimages[m_groundsetup[i * j + j]];
				g.drawImage(img, j * (m_bgimages[0].getWidth() * View.DISPLAYSCALE),
						i * (m_bgimages[0].getHeight() * View.DISPLAYSCALE), img.getWidth() * View.DISPLAYSCALE,
						img.getHeight() * View.DISPLAYSCALE, null);
			}
		}

		for (Rectangle square : squares) {
			g.drawImage(textureImage[2], square.x, square.y, SQUARE_SIZE, SQUARE_SIZE, null);
		}
	}
	
	

	public void PolygontoTiles(Polygon p) {
		// Calculate the bounds of the polygon
		Rectangle bounds = p.getBounds();

		// Place multiple squares to cover the polygon
		int startX = bounds.x - (bounds.x % SQUARE_SIZE);
		int startY = bounds.y - (bounds.y % SQUARE_SIZE);
		int endX = bounds.x + bounds.width;
		int endY = bounds.y + bounds.height;

		for (int y = startY; y < endY; y += SQUARE_SIZE) {
			for (int x = startX; x < endX; x += SQUARE_SIZE) {
				Rectangle candidateSquare = new Rectangle(x, y, SQUARE_SIZE, SQUARE_SIZE);

				// Check intersection of polygon and square
				if (p.intersects(candidateSquare)) {
					squares.add(candidateSquare);
				}
			}
		}
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
		m_nrows = m_view.m_mheight / (ref.getHeight() / 2 * View.DISPLAYSCALE);
		m_ncols = m_view.m_mwidth / (ref.getWidth() / 2 * View.DISPLAYSCALE);
		m_groundsetup = new int[m_nrows * m_ncols];
		for (int i = 0; i < m_nrows; i++) {
			for (int j = 0; j < m_ncols; j++) {
				m_groundsetup[i * j + j] = m_view.getRandomNumber(0, m_bgimages.length);
			}
		}
	}

}
