package info3.game.view;

import java.awt.Color;
import java.awt.Graphics;

import java.util.List;

import game.entity.Position;
import game.map.Biome;
import game.map.Polygon;
import game.map.Biomes.*;
import game.model.Model;

public class MiniMap {

	private int minimapWidth;
	private int minimapHeight;
	private int minimapX;
	private int minimapY;
	private Model m_model;

	public MiniMap(View v, Model model) {
		m_model = model;
		minimapWidth = v.m_d.width/10;
		minimapHeight = v.m_d.height/10;
		minimapX = 500;
		minimapY = 500;
	}

	public void paint(Graphics g) {
		AffichageMiniMap(g);
		AffichageEntity(g);

	}

	private void AffichageMiniMap(Graphics g) {
//		g.setColor(Color.CYAN);
//        g.fill3DRect(minimapX, minimapY, minimapWidth, minimapHeight, true);

        List<Biome> biomes = m_model.m_map.getBiome();
        for (Biome biome : biomes) {
        	if(biome instanceof Volcano) {
        		g.setColor(Color.YELLOW);
        	}else if(biome instanceof Ocean) {
        		g.setColor(Color.BLUE);
        	}else {
        		g.setColor(Color.GREEN);
        	}
            Polygon polygon = biome.getBorders();
            List<Position> vertices = polygon.getVertices();
            int[] xPoints = new int[vertices.size()];
            int[] yPoints = new int[vertices.size()];

            int i = 0;
            for (Position pos : vertices) {
                xPoints[i] = (int) (pos.getPositionX() / 5) + minimapX;
                yPoints[i] = (int) (pos.getPositionY() / 5) + minimapY;
                i++;
            }
            g.fillPolygon(xPoints, yPoints, vertices.size());
        }
		}


	private void AffichageEntity(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(minimapX, minimapY, 4, 200);
	}
}
