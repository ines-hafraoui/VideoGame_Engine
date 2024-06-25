package info3.game.avatar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import org.json.simple.JSONObject;

import game.entity.Absolute_Orientation;
import game.entity.ActionType;
import game.entity.Entity;
import game.entity.Position;
import info3.game.view.View;

public class MainAvatar extends Avatar {
	private long lastUpdateTime; // Temps depuis la dernière mise à jour de l'animation
	private static final long ANIMATION_INTERVAL = 500; // 500 ms entre les mises à jour

	boolean m_animate;

	public MainAvatar(Entity e, View v, JSONObject sprite_spec, boolean player) throws IOException {
		super(e, v);
		if (sprite_spec == null) {
			throw new IOException("Huuum there are no sprites");
		}
		m_imageIndex = ((Number) sprite_spec.get("index")).intValue();
		int nr = ((Number) sprite_spec.get("nb_rows")).intValue();
		int nc = ((Number) sprite_spec.get("nb_cols")).intValue();
		m_images = View.loadSprite((String) sprite_spec.get("filepath"), nr, nc);
		if (m_images == null) {
			System.out.println("the file path : " + (String) sprite_spec.get("filepath"));
			throw new IOException("Huuum sprite has not been loaded");
		}
		m_animate = (Boolean) sprite_spec.get("animation");
		if (player) {
			m_view.addPlayer(this);
		}
	}

	@Override
	public void paint(Graphics g, int x, int y) {
		if (m_entity.get_state_action() != null) {
			a_state = m_entity.get_state_action();
		}
		BufferedImage img = m_images[m_imageIndex];
//		int h = (int) m_entity.getHitBox().getHbHeight() * View.DISPLAYSCALE;
//		int w = (int) m_entity.getHitBox().getHbWidth() * View.DISPLAYSCALE;
		int h = img.getHeight() * View.DISPLAYSCALE;
		int w = img.getWidth() * View.DISPLAYSCALE;
		g.drawImage(img, (x + (int) m_entity.get_x() * View.DISPLAYSCALE) - w,
				(y + (int) m_entity.get_y() * View.DISPLAYSCALE) - h, w, h, null);
		int hp = this.m_entity.get_HP();
		if (hp > 0) {
			m_hb.drawHealthBar(g, (x + (int) m_entity.get_x() * View.DISPLAYSCALE) - w,
					(y + (int) m_entity.get_y() * View.DISPLAYSCALE) - h - 5 % img.getHeight(), w, 5 % img.getHeight(),
					hp);
		}
		if(m_entity.get_selected()) {
			g.setColor(Color.GREEN);
			g.drawRect((x + (int) m_entity.get_x() * View.DISPLAYSCALE) - w, (y + (int) m_entity.get_y() * View.DISPLAYSCALE) - h, w, h);
		}
		if (m_animate) {
			configureAnimation();
		}

	}

	@Override
	public void paintmainplayer(Graphics g, int x, int y) {
		if (m_entity.get_state_action() != null) {
			a_state = m_entity.get_state_action();
		}
		BufferedImage img = m_images[m_imageIndex];
		int h = img.getHeight() * View.DISPLAYSCALE;
		int w = img.getWidth() * View.DISPLAYSCALE;
		g.drawImage(img, x - w, y - h, w, h, null);
		int hp = this.m_entity.get_HP();
		if (hp > 0) {
			m_hb.drawHealthBar(g, x - w, y - h - 5 % img.getHeight(), w, 5 % img.getHeight(), hp);
		}
//		g.setColor(Color.red);
//		g.drawRect(x - w/2 - 20, y - h, (int) m_entity.getHitBox().getHbWidth() * View.DISPLAYSCALE,
//				(int) m_entity.getHitBox().getHbHeight() * View.DISPLAYSCALE);

		if (m_animate) {
			configureAnimation();
		}

	}

	@Override
	protected void configureAnimation() {
		a_state = m_entity.get_state_action();
		String abs_or = m_entity.get_abs_or().get_abs_Orientation();
		switch (a_state) {
		case ActionType.IDLE:
			if (m_imageIndex < 4) {
				m_imageIndex = 4;
			}
			if (m_imageIndex >= 5)
				m_imageIndex = 4;
			break;
		case ActionType.MOVE:
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
		default:
			if (m_imageIndex >= 47)
				m_imageIndex = 4;
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
