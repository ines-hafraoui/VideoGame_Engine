package info3.game.avatar;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.entity.Absolute_Orientation;
import game.entity.Entity;
import info3.game.view.View;

public class BotAvatar extends Avatar {

	private long lastUpdateTime; // Temps depuis la dernière mise à jour de l'animation
	private static final long ANIMATION_INTERVAL = 400; // 500 ms entre les mises à jour

	public BotAvatar(Entity e, View v) throws IOException {
		super(e, v);
		m_imageIndex = 0;
		if (e.get_team() == 1) {
			m_images = View.loadSprite("resources/MiniWorldSprites/Characters/Monsters/Demons/RedDemon.png", 8, 6);
		} else
			m_images = View.loadSprite("resources/MiniWorldSprites/Characters/Monsters/Demons/PurpleDemon.png", 8, 6);
	}

	@Override
	public void paint(Graphics g, int x, int y) {
		if (m_entity.get_state_action() != null) {
			a_state = StateToString(m_entity.get_state_action());
		}
		BufferedImage img = m_images[m_imageIndex];
		g.drawImage(img, (x + (int) m_entity.get_x() * View.DISPLAYSCALE) - (img.getWidth() * View.DISPLAYSCALE),
				(y + (int) m_entity.get_y() * View.DISPLAYSCALE) - (img.getHeight() * View.DISPLAYSCALE),
				img.getWidth() * View.DISPLAYSCALE, img.getHeight() * View.DISPLAYSCALE, null);
		m_hb.drawHealthBar(g, x + (int) m_entity.get_x() - (img.getWidth() * View.DISPLAYSCALE),
				y + (int) m_entity.get_y() - (img.getHeight() * View.DISPLAYSCALE) - 5 % img.getHeight(),
				(img.getWidth() * View.DISPLAYSCALE), 5 % img.getHeight(),this.m_entity.get_HP());
		configureAnimation();
	}

	@Override
	protected void configureAnimation() {
		String abs_or = m_entity.get_abs_or().get_abs_Orientation();
		switch (a_state) {
		case IDLE:
			if (m_imageIndex < 28) {
				m_imageIndex = 28;
			}
			if (m_imageIndex >= 29)
				m_imageIndex = 28;
			break;
		case WALK:
			if (abs_or.equals(Absolute_Orientation.SOUTH) || abs_or.equals(Absolute_Orientation.SOUTH_E)
					|| abs_or.equals(Absolute_Orientation.SOUTH_W)) {
				if (m_imageIndex < 0) {
					m_imageIndex = 0;
				}
				if (m_imageIndex >= 4)
					m_imageIndex = 0;
			} else if (abs_or.equals(Absolute_Orientation.NORTH) || abs_or.equals(Absolute_Orientation.NORTH_E)
					|| abs_or.equals(Absolute_Orientation.NORTH_W)) {
				if (m_imageIndex < 6) {
					m_imageIndex = 6;
				}
				if (m_imageIndex >= 10)
					m_imageIndex = 6;
			} else if (abs_or.equals(Absolute_Orientation.EAST)) {
				if (m_imageIndex < 12) {
					m_imageIndex = 12;
				}
				if (m_imageIndex >= 16)
					m_imageIndex = 12;
			} else {// WEST
				if (m_imageIndex < 18) {
					m_imageIndex = 18;
				}
				if (m_imageIndex >= 22)
					m_imageIndex = 18;
			}
			break;
		case HIT:
			if (abs_or.equals(Absolute_Orientation.SOUTH) || abs_or.equals(Absolute_Orientation.SOUTH_E)
					|| abs_or.equals(Absolute_Orientation.SOUTH_W)) {
				if (m_imageIndex < 24) {
					m_imageIndex = 24;
				}
				if (m_imageIndex >= 29)
					m_imageIndex = 24;
			} else if (abs_or.equals(Absolute_Orientation.NORTH) || abs_or.equals(Absolute_Orientation.NORTH_E)
					|| abs_or.equals(Absolute_Orientation.NORTH_W)) {
				if (m_imageIndex < 30) {
					m_imageIndex = 30;
				}
				if (m_imageIndex >= 35)
					m_imageIndex = 30;
			} else if (abs_or.equals(Absolute_Orientation.EAST)) {
				if (m_imageIndex < 36) {
					m_imageIndex = 36;
				}
				if (m_imageIndex >= 41)
					m_imageIndex = 36;
			} else {// WEST
				if (m_imageIndex < 42) {
					m_imageIndex = 42;
				}
				if (m_imageIndex >= 47)
					m_imageIndex = 42;
			}
			break;
		}
		long currentTime = System.currentTimeMillis();
		if (currentTime - lastUpdateTime > ANIMATION_INTERVAL) {
			m_imageIndex++;
			lastUpdateTime = currentTime; // Réinitialiser le dernier temps de mise à jour
		}
	}

	@Override
	public Image[] get_images() {
		// TODO Auto-generated method stub
		return null;
	}

}
