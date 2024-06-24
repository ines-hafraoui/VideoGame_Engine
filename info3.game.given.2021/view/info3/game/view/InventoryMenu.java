package info3.game.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.entity.Entity;
import info3.game.avatar.Avatar;
import info3.game.avatar.ItemAvatar;

public class InventoryMenu {

	// How much of the world we will be showing in each Viewport
	static final int INVENTORYSIZE = 3;
	static final int NBCASE = 6;

	private BufferedImage m_image;
	private int InventoryX;
	private int InventoryY;
	private AViewport m_viewport;
	private Entity[] m_inventory;

	public InventoryMenu(AViewport v, Entity[] inventory) {
		m_viewport = v;

		BufferedImage[] images = View.loadSprite("resources/MiniWorldSprites/User_Interface/Highlighted-Boxes.png", 1,
				5);
		m_image = images[0];
		m_inventory = inventory;
	}

	public void paint(Graphics g, int x, int y) {
		int w = get_image().getWidth() * INVENTORYSIZE;
		for (int i = 0; i < NBCASE; i++) {
			g.drawImage(get_image(), x + i * w, y, w, get_image().getHeight() * INVENTORYSIZE, null);
			if (m_inventory.length > i) {
				if (m_inventory[i] != null) {
					Entity item = m_inventory[i];
					Avatar item_avatar = new ItemAvatar();
					g.drawImage(item_avatar.get_images()[0], x + i * w, y, w,
							get_image().getHeight() * INVENTORYSIZE, null);
				}
			}

		}
	}

	public BufferedImage get_image() {
		return m_image;
	}

}
