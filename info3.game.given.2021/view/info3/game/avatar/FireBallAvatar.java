package info3.game.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.entity.Entity;
import info3.game.view.View;

public class FireBallAvatar extends Avatar {

	public FireBallAvatar(Entity e, View v) throws IOException {
		super(e, v);
		m_imageIndex = 0;
		m_images = View.loadSprite("resources/MiniWorldSprites/Objects/FireballProjectile.png", 1, 4);
	}

	@Override
	public void paint(Graphics g, int x, int y) {
		BufferedImage img = m_images[m_imageIndex];
		g.drawImage(img,  (x + (int) m_entity.get_x() * View.DISPLAYSCALE) - (img.getWidth() * View.DISPLAYSCALE),  (y + (int) m_entity.get_y() * View.DISPLAYSCALE) - (img.getWidth() * View.DISPLAYSCALE), img.getWidth() * View.DISPLAYSCALE,
				img.getHeight() * View.DISPLAYSCALE, null);
		if (m_imageIndex >= 3)
			m_imageIndex = 0;
		else
			m_imageIndex++;

	}

}
