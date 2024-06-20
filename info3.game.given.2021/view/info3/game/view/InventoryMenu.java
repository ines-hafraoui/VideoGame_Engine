package info3.game.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class InventoryMenu {

	// How much of the world we will be showing in each Viewport
	static final int INVENTORYSIZE = 3;
	static final int NBCASE = 6;


	private BufferedImage m_image;
	private int InventoryX;
	private int InventoryY;
	private Viewport m_viewport;

	public InventoryMenu(Viewport v) throws IOException {
		m_viewport = v;

		BufferedImage[] images = View.loadSprite("resources/MiniWorldSprites/User_Interface/Highlighted-Boxes.png", 1,
				5);
		m_image = images[0];
		InventoryX = m_viewport.m_d.width / 2 - NBCASE/2 * (m_image.getWidth()* INVENTORYSIZE);
		InventoryY = m_viewport.m_d.height - m_image.getHeight() - 100;
	}

	public void paint(Graphics g) {
		int w = m_image.getWidth() * INVENTORYSIZE;

		for (int i = 0; i < NBCASE; i++) {
			g.drawImage(m_image, InventoryX + i * w, InventoryY, w, m_image.getHeight() * INVENTORYSIZE, null);
		}
	}

}
