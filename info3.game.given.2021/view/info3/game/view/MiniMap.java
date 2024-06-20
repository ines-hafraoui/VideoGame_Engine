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
	static final int MINIMAPSCALE = 8;

	private int minimapWidth;
	private int minimapHeight;
	private int minimapX;
	private int minimapY;
	private Model m_model;
	private View m_view;

	public MiniMap(View v, Model model) {
		m_view = v;
		m_model = model;
		minimapWidth = v.m_d.width / MINIMAPSCALE;
		minimapHeight = v.m_d.height / MINIMAPSCALE;

		// To center the x axis on the window
		minimapX = (v.m_d.width - minimapWidth) / 2;
		// To fix it to the bottom of the window
		minimapY = v.m_d.height - minimapHeight - 50;
	}

	public void paint(Graphics g) {
		AffichageMiniMap(g);
		AffichageEntity(g);

	}

	private void AffichageMiniMap(Graphics g) {
		Color prairieGreen = new Color(77, 164, 9);
		g.setColor(prairieGreen);
		g.fillRect(minimapX, minimapY, minimapWidth, minimapHeight);

		List<Biome> biomes = m_model.m_map.getBiome();
		for (Biome biome : biomes) {
			if (biome instanceof Volcano) {
				g.setColor(Color.YELLOW);
			} else if (biome instanceof Ocean) {
				g.setColor(Color.BLUE);
			} else {
				g.setColor(Color.GREEN);
			}
			Polygon polygon = biome.getBorders();
			List<Position> vertices = polygon.getVertices();
			int[] xPoints = new int[vertices.size()];
			int[] yPoints = new int[vertices.size()];

			int i = 0;
			for (Position pos : vertices) {
				xPoints[i] = m_view.WorldToViewX(pos.getPositionX()) / MINIMAPSCALE + minimapX;
				yPoints[i] = m_view.WorldToViewY(pos.getPositionY()) / MINIMAPSCALE + minimapY;
				i++;
			}
			g.fillPolygon(xPoints, yPoints, vertices.size());
		}
	}

	private void AffichageEntity(Graphics g) {
		g.setColor(Color.MAGENTA);
		List<Avatar> List_av = m_view.getAvatars();
		for (Avatar avatar : List_av) {
			g.fillRect(minimapX +  m_view.WorldToViewX(avatar.m_entity.get_x()) / MINIMAPSCALE, minimapY + m_view.WorldToViewY(avatar.m_entity.get_y()) / MINIMAPSCALE,
					4, 4);
		}
		g.setColor(Color.MAGENTA);
		List<Avatar> List_pl = m_view.getPlayers();
		for (Avatar player : List_pl) {
			if (player.m_entity.get_team() == 1) {
				g.setColor(Color.RED);
				g.fillRect(minimapX +  m_view.WorldToViewX(player.m_entity.get_x()) / MINIMAPSCALE, minimapY + m_view.WorldToViewY(player.m_entity.get_y()) / MINIMAPSCALE,
						4, 4);
			} else {
				g.setColor(Color.CYAN);
				g.fillRect(minimapX +  m_view.WorldToViewX(player.m_entity.get_x()) / MINIMAPSCALE, minimapY + m_view.WorldToViewY(player.m_entity.get_y()) / MINIMAPSCALE,
						4, 4);
			}
		}
	}
}
