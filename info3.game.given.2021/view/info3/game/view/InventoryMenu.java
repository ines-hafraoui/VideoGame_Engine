package info3.game.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class InventoryMenu {

	private BufferedImage m_image;
	private int InventoryX;
	private int InventoryY;
	private Viewport m_viewport;

	public InventoryMenu(Viewport v) throws IOException {
		m_viewport = v;

		BufferedImage[] images = View.loadSprite("resources/MiniWorldSprites/User_Interface/Highlighted-Boxes.png", 1,
				5);
		m_image = images[0];
		InventoryX = m_viewport.m_d.width / 2;
		InventoryY = m_viewport.m_d.height - m_image.getHeight() - 100;
	}

	public void paint(Graphics g) {
		int w = m_image.getWidth() * View.DISPLAYSCALE;
		
		for (int i = 0; i < 5; i++) {
			g.drawImage(m_image, InventoryX + i*w, InventoryY, w,
					m_image.getHeight() * View.DISPLAYSCALE, null);
		}
	}

}
