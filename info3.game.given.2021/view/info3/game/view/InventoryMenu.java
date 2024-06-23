package info3.game.view;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import game.entity.Item;
import game.entity.Player;
import info3.game.avatar.ItemAvatar;

public class InventoryMenu {

	// How much of the world we will be showing in each Viewport
	static final int INVENTORYSIZE = 3;
	static final int NBCASE = 6;

	private BufferedImage m_image;
	private int InventoryX;
	private int InventoryY;
	private AViewport m_viewport;
	private Player m_player;

	public InventoryMenu(AViewport v, Player player) throws IOException {
		m_viewport = v;

		BufferedImage[] images = View.loadSprite("resources/MiniWorldSprites/User_Interface/Highlighted-Boxes.png", 1,
				5);
		m_image = images[0];
		InventoryX = m_viewport.m_d.width / 2 - NBCASE / 2 * (m_image.getWidth() * INVENTORYSIZE);
		InventoryY = m_viewport.m_d.height - m_image.getHeight() - 100;
		m_player = player;
	}

	public void paint(Graphics g) throws IOException {
		if (m_viewport.m_parent.Changed) {
			setpositions();
		}
		int w = m_image.getWidth() * INVENTORYSIZE;
		Item[] L_item = m_player.get_inventory();
		for (int i = 0; i < NBCASE; i++) {
			g.drawImage(m_image, InventoryX + i * w, InventoryY, w, m_image.getHeight() * INVENTORYSIZE, null);
			if (L_item.length > i) {
				if (L_item[i] != null) {
					Item item = L_item[i];
					ItemAvatar item_avatar = new ItemAvatar();
					g.drawImage(item_avatar.get_images()[0], InventoryX + i * w, InventoryY, w,
							m_image.getHeight() * INVENTORYSIZE, null);
				}
			}

		}
	}

	private void setpositions() {
		InventoryX = m_viewport.m_d.width / 2 - NBCASE / 2 * (m_image.getWidth() * INVENTORYSIZE);
		InventoryY = m_viewport.m_d.height - m_image.getHeight() - 100;
	}

}
