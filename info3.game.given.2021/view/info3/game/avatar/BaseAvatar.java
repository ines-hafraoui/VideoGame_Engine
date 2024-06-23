package info3.game.avatar;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.entity.Entity;
import info3.game.view.View;

public class BaseAvatar extends Avatar {

	static final int BASESIZE = 3;

	public BaseAvatar(Entity e, View v) throws IOException {
		super(e, v);
		m_imageIndex = 0;
		if (e.get_team() == 1) {
			m_images = View.loadSprite("resources/MiniWorldSprites/Buildings/Red/RedKeep.png", 2, 3);
		} else {
			m_images = View.loadSprite("resources/MiniWorldSprites/Buildings/Cyan/CyanKeep.png", 2, 3);
		}
	}

	@Override
	public void paint(Graphics g, int x, int y) {
		BufferedImage img = m_images[m_imageIndex];
		g.drawImage(img, (x + (int) m_entity.get_x() * View.DISPLAYSCALE) - (img.getWidth() * View.DISPLAYSCALE),
				(y + (int) m_entity.get_y() * View.DISPLAYSCALE) - (img.getHeight() * View.DISPLAYSCALE),
				img.getWidth() * View.DISPLAYSCALE*BASESIZE, img.getHeight() * View.DISPLAYSCALE*BASESIZE, null);
	}

	@Override
	protected void configureAnimation() {
		// TODO Auto-generated method stub

	}

	@Override
	public Image[] get_images() {
		// TODO Auto-generated method stub
		return null;
	}
}