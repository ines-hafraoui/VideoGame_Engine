package info3.game.avatar;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.entity.Absolute_Orientation;
import game.entity.Entity;
import info3.game.view.View;

public class ArrowAvatar extends Avatar {

	public ArrowAvatar(Entity e, View v) throws IOException {
		super(e, v);
		m_imageIndex = 0;
		m_images = View.loadSprite("resources/MiniWorldSprites/Objects/ArrowLong.png", 2, 3);
	}

	@Override
	public void paint(Graphics g, int x, int y) {
		BufferedImage img = m_images[m_imageIndex];
		g.drawImage(img, x + (int) m_entity.get_x(),
				y + (int) m_entity.get_y(), img.getWidth() * View.DISPLAYSCALE,
				img.getHeight() * View.DISPLAYSCALE, null);
		String abs_or = m_entity.get_abs_or().get_abs_Orientation();
		switch (abs_or) {
		case Absolute_Orientation.NORTH:
		case Absolute_Orientation.NORTH_E:
		case Absolute_Orientation.NORTH_W:
			m_imageIndex = 1;
			break;
		case Absolute_Orientation.SOUTH:
		case Absolute_Orientation.SOUTH_E:
		case Absolute_Orientation.SOUTH_W:
			m_imageIndex = 0;
			break;
		case Absolute_Orientation.EAST:
			m_imageIndex = 2;
			break;
		default:
			m_imageIndex = 3;
		}
	}

}