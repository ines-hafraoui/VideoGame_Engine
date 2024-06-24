package info3.game.avatar;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import game.entity.Absolute_Orientation;
import game.entity.ActionType;
import game.entity.Entity;
import info3.game.view.View;

public class PlayerAvatar extends Avatar {

	private long lastUpdateTime; // Temps depuis la dernière mise à jour de l'animation
	private static final long ANIMATION_INTERVAL = 500; // 500 ms entre les mises à jour

	public PlayerAvatar(Entity e, View v) throws IOException {
		super(e, v);
		m_view.addPlayer(this);
		m_imageIndex = 0;
		if (e.get_team() == 1) {
			m_images = View.loadSprite("resources/MiniWorldSprites/Characters/Soldiers/Mounted/RedKnight.png", 12, 4);
		} else {
			m_images = View.loadSprite("resources/MiniWorldSprites/Characters/Soldiers/Mounted/CyanKnight.png", 12, 4);
		}
	}

	@Override
	public void paint(Graphics g, int x, int y) {
		if (m_entity.get_state_action() != null) {
			a_state = m_entity.get_state_action();
			//a_state = StateToString(m_entity.get_state_action());
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
		System.out.println("ICI " + a_state);

		//a_state = StateToString(m_entity.get_state_action());
		
		
		String abs_or = m_entity.get_abs_or().get_abs_Orientation();
		a_state = m_entity.get_state_action();
		switch (a_state) {

		case ActionType.IDLE:
//			m_entity.get_automate().blocked=false;
			System.out.print("In IDLE\n");

			if (m_imageIndex < 4) {
				m_imageIndex = 4;
			}
			if (m_imageIndex >= 5)
				m_imageIndex = 4;
			break;

		case ActionType.MOVE:
//			m_entity.get_automate().blocked=false;

			if (abs_or.equals(Absolute_Orientation.SOUTH) || abs_or.equals(Absolute_Orientation.SOUTH_E)
					|| abs_or.equals(Absolute_Orientation.SOUTH_W)) {
				if (m_imageIndex < 0) {
					m_imageIndex = 0;
				}
				if (m_imageIndex >= 3)
					m_imageIndex = 0;
				else
					m_imageIndex++;
			} else if (abs_or.equals(Absolute_Orientation.NORTH) || abs_or.equals(Absolute_Orientation.NORTH_E)
					|| abs_or.equals(Absolute_Orientation.NORTH_W)) {
				if (m_imageIndex < 36) {
					m_imageIndex = 36;
				}
				if (m_imageIndex >= 39)
					m_imageIndex = 36;
			} else if (abs_or.equals(Absolute_Orientation.EAST)) {
				if (m_imageIndex < 12) {
					m_imageIndex = 12;
				}
				if (m_imageIndex >= 15)
					m_imageIndex = 12;
			} else {// WEST
				if (m_imageIndex < 24) {
					m_imageIndex = 24;
				}
				if (m_imageIndex >= 25)
					m_imageIndex = 24;
			}
			break;

		case ActionType.HIT:
			if (abs_or.equals(Absolute_Orientation.SOUTH) || abs_or.equals(Absolute_Orientation.SOUTH_E)
					|| abs_or.equals(Absolute_Orientation.SOUTH_W)) {
				if (m_imageIndex < 8) {
					m_imageIndex = 8;
				}
				if (m_imageIndex >= 11)
					m_imageIndex = 8;
			} else if (abs_or.equals(Absolute_Orientation.NORTH) || abs_or.equals(Absolute_Orientation.NORTH_E)
					|| abs_or.equals(Absolute_Orientation.NORTH_W)) {
				if (m_imageIndex < 20) {
					m_imageIndex = 20;
				}
				if (m_imageIndex >= 35)
					m_imageIndex = 30;
			} else if (abs_or.equals(Absolute_Orientation.EAST)) {
				if (m_imageIndex < 20) {
					m_imageIndex = 20;
				}
				if (m_imageIndex >= 23)
					m_imageIndex = 20;
			} else {// WEST
				if (m_imageIndex < 44) {
					m_imageIndex = 44;
				}
				if (m_imageIndex >= 47)
					m_imageIndex = 44;
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
