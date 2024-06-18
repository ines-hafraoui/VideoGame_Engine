package info3.game.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.entity.Absolute_Orientation;
import game.entity.Entity;
import info3.game.view.View;

public class PlayerAvatar extends Avatar {

	public PlayerAvatar(Entity e, View v) throws IOException {
		super();
		m_view = v;
		m_entity = e;
		m_view.addPlayer(this);
		m_imageIndex = 0;
		m_images = View.loadSprite("resources/MiniWorldSprites/Characters/Soldiers/Mounted/RedKnight.png", 12, 4);
	}

	@Override
	public void paint(Graphics g, int x, int y) {
		BufferedImage img = m_images[m_imageIndex];
		g.drawImage(img, x - (img.getWidth() * View.DISPLAYSCALE), y - (img.getHeight() * View.DISPLAYSCALE),
				img.getWidth() * View.DISPLAYSCALE, img.getHeight() * View.DISPLAYSCALE, null);
		m_hb.drawHealthBar(g, x + (int) m_entity.get_x() - (img.getWidth() * View.DISPLAYSCALE),
				y + (int) m_entity.get_y() - (img.getHeight() * View.DISPLAYSCALE) - 5 % img.getHeight(),
				(img.getWidth() * View.DISPLAYSCALE), 5 % img.getHeight());

		String abs_or = m_entity.get_abs_or().get_abs_Orientation();
		switch (a_state) {
		case IDLE:
			if (m_imageIndex >= 29)
				m_imageIndex = 28;
			else
				m_imageIndex++;
			break;
		case WALK:
			if (abs_or.equals(Absolute_Orientation.SOUTH) || abs_or == Absolute_Orientation.SOUTH_E
					|| abs_or == Absolute_Orientation.SOUTH_W) {
				if (m_imageIndex >= 4)
					m_imageIndex = 0;
				else
					m_imageIndex++;
			} else if (m_entity.get_abs_or().get_abs_Orientation() == Absolute_Orientation.NORTH
					|| abs_or == Absolute_Orientation.NORTH_E || abs_or == Absolute_Orientation.NORTH_W) {
				if (m_imageIndex >= 10)
					m_imageIndex = 6;
				else
					m_imageIndex++;
			} else if (m_entity.get_abs_or().get_abs_Orientation() == Absolute_Orientation.EAST) {
				if (m_imageIndex >= 16)
					m_imageIndex = 12;
				else
					m_imageIndex++;
			} else {// WEST
				if (m_imageIndex >= 22)
					m_imageIndex = 18;
				else
					m_imageIndex++;
			}
			break;
		case HIT:
			if (abs_or.equals(Absolute_Orientation.SOUTH) || abs_or == Absolute_Orientation.SOUTH_E
					|| abs_or == Absolute_Orientation.SOUTH_W) {
				if (m_imageIndex >= 29)
					m_imageIndex = 24;
				else
					m_imageIndex++;
			} else if (m_entity.get_abs_or().get_abs_Orientation() == Absolute_Orientation.NORTH
					|| abs_or == Absolute_Orientation.NORTH_E || abs_or == Absolute_Orientation.NORTH_W) {
				if (m_imageIndex >= 35)
					m_imageIndex = 30;
				else
					m_imageIndex++;
			} else if (m_entity.get_abs_or().get_abs_Orientation() == Absolute_Orientation.EAST) {
				if (m_imageIndex >= 41)
					m_imageIndex = 36;
				else
					m_imageIndex++;
			} else {// WEST
				if (m_imageIndex >= 47)
					m_imageIndex = 42;
				else
					m_imageIndex++;
			}
			break;
		}
		m_imageIndex++;
	}
}
