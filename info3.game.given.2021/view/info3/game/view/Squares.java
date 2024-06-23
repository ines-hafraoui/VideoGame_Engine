package info3.game.view;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import game.map.Polygon;

public class Squares {
	int m_x, m_y;
	private BufferedImage m_textureImage;
	private List<Rectangle> m_squares = new ArrayList<>(); // List to store squares
	private Polygon m_poly;
	int m_squaresize;
	AViewport m_vp;

	Squares(Polygon p, BufferedImage textureImage, AViewport vp) {
		m_poly = p.scale(View.DISPLAYSCALE);
		m_textureImage = textureImage;
		// Gives the images its new height and width determined by the scaling
		m_squaresize = m_textureImage.getHeight() * View.DISPLAYSCALE;
		PolygontoTiles(m_poly);
		m_vp = vp;
	}

	public void PolygontoTiles(Polygon p) {
		// Calculate the bounds of the polygon
		Rectangle bounds = p.getBounds();

		// Place multiple squares to cover the polygon
		int startX = bounds.x - (bounds.x % m_squaresize);
		int startY = bounds.y - (bounds.y % m_squaresize);
		int endX = bounds.x + bounds.width;
		int endY = bounds.y + bounds.height;

		for (int y = startY; y < endY - (10 * View.DISPLAYSCALE); y += m_squaresize) {
			for (int x = startX; x < endX - (10 * View.DISPLAYSCALE); x += m_squaresize) {
				Rectangle candidateSquare = new Rectangle(x, y, m_squaresize, m_squaresize);
				// Check intersection of polygon and square
				if (p.intersects(candidateSquare)) {
					m_squares.add(candidateSquare);
				}
			}
		}
	}

	public void paint(Graphics g, int x, int y) {
		for (Rectangle square : m_squares) {
			int sx = square.x + x;
			int sy = square.y + y;
			if (m_vp.withinbounds(sx, sy)) {
				g.drawImage(m_textureImage, sx, sy, m_squaresize, m_squaresize, null);
			}
		}
	}

}
