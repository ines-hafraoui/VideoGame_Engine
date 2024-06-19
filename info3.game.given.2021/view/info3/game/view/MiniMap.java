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

	private int minimapWidth;
	private int minimapHeight;
	private int minimapX;
	private int minimapY;
	private Model m_model;
	private View m_view;

	public MiniMap(View v, Model model) {
		m_view = v;
		m_model = model;
		minimapWidth = v.m_d.width / 10;
		minimapHeight = v.m_d.height / 10;
		minimapX = (v.m_d.width - minimapWidth) / 2;
		minimapY = v.m_d.height - minimapHeight - 50;
	}

	public void paint(Graphics g) {
		AffichageMiniMap(g);
		AffichageEntity(g);

	}

	private void AffichageMiniMap(Graphics g) {
		g.setColor(Color.GREEN);
		g.fill3DRect(minimapX, minimapY, minimapWidth, minimapHeight, true);

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
				xPoints[i] = (int) (pos.getPositionX() / 10) + minimapX;
				yPoints[i] = (int) (pos.getPositionY() / 10) + minimapY;
				i++;
			}
			g.fillPolygon(xPoints, yPoints, vertices.size());
		}
	}

	private void AffichageEntity(Graphics g) {
		g.setColor(Color.RED);
		List<Avatar> List_av = m_view.getAvatars();
		for (Avatar avatar : List_av) {
			g.fillRect(minimapX + (int) (avatar.m_entity.get_x()) / 10, minimapY + (int) (avatar.m_entity.get_y()) / 10,
					4, 4);
		}

	}
}
