package info3.game.snake;

import java.awt.Color;
import java.awt.Graphics;

public class Grid {

	int m_x, m_y, m_width, m_height;
	int m_nboxline, m_nboxcol;
	int m_border;

	public Grid(int x, int y, int width, int height, int nboxline, int nboxcol) {
		m_x = x;
		m_y = y;
		m_width = width;
		m_height = height;
		m_nboxline = nboxline;
		m_nboxcol = nboxcol;
		m_border = 0;
	}

	public void setBorder(int border) {
		m_border = border;
	}

	public void paint(Graphics g) {
		Color[] colorpos = GetEltsPos();
		int box_width = (m_width + m_border) / m_nboxline;
		int box_height = (m_height - m_border) / m_nboxcol;
		for (int i = 0; i < m_nboxcol; i++) {
			for (int j = 0; j < m_nboxline; j++) {
				if(colorpos[i*j] == null)
					g.setColor(Color.DARK_GRAY);
				g.fillRect(m_x + j * box_width + m_border, m_y + i * box_height + m_border, box_width - (m_border * 2),
						box_height - m_border);
			}
		}
	}
	
	/* This method returns an array, size of the grid with the color of the element
	 * present on this box if there is nothing its null
	 */
	Color[] GetEltsPos() {
		Color[] colortab = new Color[m_nboxcol*m_nboxline];
		//un itérateur sur les élements, on cherche à ce que chacun des elements nous renvoie sa position
		//et on remplie le tableau en conséquence
		return colortab;
	}

}
