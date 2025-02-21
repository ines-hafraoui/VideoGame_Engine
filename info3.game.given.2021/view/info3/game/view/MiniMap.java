package info3.game.view;

import java.awt.Color;
import java.awt.Graphics;

import java.util.List;

import game.entity.Position;
import game.map.Biome;
import game.map.Polygon;
import game.map.Biomes.*;
import game.model.Model;
import info3.game.avatar.Avatar;

public class MiniMap {

	// OVERALL SIZE OF THE MINIMAP
	static final int MINIMAPSCALE = 10;

	private int minimapWidth;
	private int minimapHeight;
	private int minimapX;
	private int minimapY;
	private Model m_model;
	private View m_view;

	public MiniMap(View v, Model model) {
		m_view = v;
		m_model = model;
		minimapWidth = m_model.get_width() / MINIMAPSCALE;
		minimapHeight = m_model.get_height() / MINIMAPSCALE;

		// To center the x axis on the window
		minimapX = (v.m_d.width - minimapWidth) / 2;
		// To fix it to the bottom of the window
		minimapY = v.m_d.height - minimapHeight - 50;
	}

	public void paint(Graphics g) {
		if (m_view.Changed) {
			setpositions();
		}
		AffichageMiniMap(g);
		AffichageEntity(g);

	}

	private void setpositions() {
		minimapWidth = m_model.get_width() / MINIMAPSCALE;
		minimapHeight = m_model.get_height() / MINIMAPSCALE;

		// To center the x axis on the window
		minimapX = (m_view.m_d.width - minimapWidth) / 2;
		// To fix it to the bottom of the window
		minimapY = m_view.m_d.height - minimapHeight - 50;
	}

	private void AffichageMiniMap(Graphics g) {
		Color color_prairie = new Color(95, 225, 20);
		Color color_volcano = new Color(206, 215, 5);
		Color color_ocean = new Color(5, 193, 215);
		g.setColor(color_prairie);
		g.fillRect(minimapX, minimapY, minimapWidth, minimapHeight);

		List<Biome> biomes = m_model.m_map.getBiome();
		for (Biome biome : biomes) {
			if (biome instanceof Volcano) {
				g.setColor(color_volcano);
			} else if (biome instanceof Ocean) {
				g.setColor(color_ocean);
			} else {
				g.setColor(color_prairie);
			}
			Polygon polygon = biome.getBorders();
			List<Position> vertices = polygon.getVertices();
			int[] xPoints = new int[vertices.size()];
			int[] yPoints = new int[vertices.size()];

			int i = 0;
			for (Position pos : vertices) {
				xPoints[i] = (int) (pos.getPositionX() / MINIMAPSCALE + minimapX);
				yPoints[i] = (int) (pos.getPositionY() / MINIMAPSCALE + minimapY);
				i++;
			}
			g.fillPolygon(xPoints, yPoints, vertices.size());
		}
	}

	private void AffichageEntity(Graphics g) {
		g.setColor(Color.MAGENTA);
		List<Avatar> List_av = m_view.getAvatars();
		for (Avatar avatar : List_av) {
			g.fillRect((int) (minimapX + avatar.m_entity.get_x() / MINIMAPSCALE),
					(int) (minimapY + avatar.m_entity.get_y() / MINIMAPSCALE), 4, 4);
		}
		g.setColor(Color.MAGENTA);
		List<Avatar> List_pl = m_view.getPlayers();
		for (Avatar player : List_pl) {
			if (player.m_entity.get_team() == 1) {
				g.setColor(Color.RED);
				g.fillRect((int) (minimapX + player.m_entity.get_x() / MINIMAPSCALE),
						(int) (minimapY + player.m_entity.get_y() / MINIMAPSCALE), 4, 4);
			} else {
				g.setColor(Color.CYAN);
				g.fillRect((int) (minimapX + player.m_entity.get_x() / MINIMAPSCALE),
						(int) (minimapY + player.m_entity.get_y() / MINIMAPSCALE), 4, 4);
			}
		}
	}
}
