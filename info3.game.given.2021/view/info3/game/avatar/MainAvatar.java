package info3.game.avatar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
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
	JSONObject m_actions, m_orientations;
	int m_maxsprite;

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
		m_maxsprite = nr * nc - 1;
		m_animate = (Boolean) sprite_spec.get("animation");
		JSONObject details = (JSONObject) sprite_spec.get("details");
		if (m_animate && details != null) {
			m_actions = (JSONObject) details.get("action");
			m_orientations = (JSONObject) details.get("orientation");
		}
		if (player) {
			m_view.addPlayer(this);
		}
	}

	@Override
	public void paint(Graphics g, int x, int y) {
		BufferedImage img = m_images[m_imageIndex];
		int h = img.getHeight() * View.DISPLAYSCALE;
		int w = img.getWidth() * View.DISPLAYSCALE;
		g.drawImage(img, (x + (int) m_entity.get_x() * View.DISPLAYSCALE) - w,
				(y + (int) m_entity.get_y() * View.DISPLAYSCALE) - h, h, w, null);
		int hp = this.m_entity.get_HP();
		if (hp > 0) {
			m_hb.drawHealthBar(g, (x + (int) m_entity.get_x() * View.DISPLAYSCALE) - w,
					(y + (int) m_entity.get_y() * View.DISPLAYSCALE) - h - 5 % img.getHeight(), w, 5 % img.getHeight(),
					hp);
		}
		g.setColor(Color.red);
		g.drawRect((x + (int) m_entity.get_x() * View.DISPLAYSCALE) - w,
				(y + (int) m_entity.get_y() * View.DISPLAYSCALE) - h,
				(int) m_entity.getHitBox().getHbWidth() * View.DISPLAYSCALE,
				(int) m_entity.getHitBox().getHbHeight() * View.DISPLAYSCALE);

		if (m_animate) {
			configureAnimation();
		}

	}

	@Override
	public void paintmainplayer(Graphics g, int x, int y) {
		BufferedImage img = m_images[m_imageIndex];
		int h = img.getHeight() * View.DISPLAYSCALE;
		int w = img.getWidth() * View.DISPLAYSCALE;
		g.drawImage(img, x - w, y - h, w, h, null);
		int hp = this.m_entity.get_HP();
		if (hp > 0) {
			m_hb.drawHealthBar(g, x - w, y - h - 5 % img.getHeight(), w, 5 % img.getHeight(), hp);
		}
		g.setColor(Color.red);
		g.drawRect(x - w/2 - 20, y - h, (int) m_entity.getHitBox().getHbWidth() * View.DISPLAYSCALE,
				(int) m_entity.getHitBox().getHbHeight() * View.DISPLAYSCALE);
		if (m_animate) {
			configureAnimation();
		}

	}

	@Override
	protected void configureAnimation() {
		String abs_or = m_entity.get_abs_or().get_abs_Orientation();
		if (m_actions != null) {
			a_state = m_entity.get_state_action();
			switch (a_state) {
			case ActionType.IDLE:
				JSONArray indexidle = (JSONArray) ((JSONObject) m_actions.get("idle")).get("index");
				if (m_imageIndex < ((Number) indexidle.get(0)).intValue()) {
					m_imageIndex = ((Number) indexidle.get(0)).intValue();
				}
				if (m_imageIndex >= ((Number) indexidle.get(1)).intValue())
					m_imageIndex = ((Number) indexidle.get(0)).intValue();
				break;
			case ActionType.MOVE:
				if (abs_or.equals(Absolute_Orientation.SOUTH) || abs_or.equals(Absolute_Orientation.SOUTH_E)
						|| abs_or.equals(Absolute_Orientation.SOUTH_W)) {
					JSONArray index = (JSONArray) ((JSONObject)((JSONObject) m_actions.get("move"))
							.get("orientation")).get("south");
					if (m_imageIndex < ((Number) index.get(0)).intValue()) {
						m_imageIndex = ((Number) index.get(0)).intValue();
					}
					if (m_imageIndex >= ((Number) index.get(1)).intValue())
						m_imageIndex = ((Number) index.get(0)).intValue();
				} else if (abs_or.equals(Absolute_Orientation.NORTH) || abs_or.equals(Absolute_Orientation.NORTH_E)
						|| abs_or.equals(Absolute_Orientation.NORTH_W)) {
					JSONArray index = (JSONArray) ((JSONObject)((JSONObject) m_actions.get("move"))
							.get("orientation")).get("north");
					if (m_imageIndex < ((Number) index.get(0)).intValue()) {
						m_imageIndex = ((Number) index.get(0)).intValue();
					}
					if (m_imageIndex >= ((Number) index.get(1)).intValue())
						m_imageIndex = ((Number) index.get(0)).intValue();
				} else if (abs_or.equals(Absolute_Orientation.EAST)) {
					JSONArray index = (JSONArray) ((JSONObject)((JSONObject) m_actions.get("move"))
							.get("orientation")).get("east");
					if (m_imageIndex < ((Number) index.get(0)).intValue()) {
						m_imageIndex = ((Number) index.get(0)).intValue();
					}
					if (m_imageIndex >= ((Number) index.get(1)).intValue())
						m_imageIndex = ((Number) index.get(0)).intValue();
				} else {// WEST
					JSONArray index = (JSONArray) ((JSONObject)((JSONObject) m_actions.get("move"))
							.get("orientation")).get("west");
					if (m_imageIndex < ((Number) index.get(0)).intValue()) {
						m_imageIndex = ((Number) index.get(0)).intValue();
					}
					if (m_imageIndex >= ((Number) index.get(1)).intValue())
						m_imageIndex = ((Number) index.get(0)).intValue();
				}
				break;
			case ActionType.HIT:
				if (abs_or.equals(Absolute_Orientation.SOUTH) || abs_or.equals(Absolute_Orientation.SOUTH_E)
						|| abs_or.equals(Absolute_Orientation.SOUTH_W)) {
					JSONArray index = (JSONArray) ((JSONObject)((JSONObject) m_actions.get("hit"))
							.get("orientation")).get("south");
					if (m_imageIndex < ((Number) index.get(0)).intValue()) {
						m_imageIndex = ((Number) index.get(0)).intValue();
					}
					if (m_imageIndex >= ((Number) index.get(1)).intValue())
						m_imageIndex = ((Number) index.get(0)).intValue();
				} else if (abs_or.equals(Absolute_Orientation.NORTH) || abs_or.equals(Absolute_Orientation.NORTH_E)
						|| abs_or.equals(Absolute_Orientation.NORTH_W)) {
					JSONArray index = (JSONArray) ((JSONObject)((JSONObject) m_actions.get("hit"))
							.get("orientation")).get("north");
					if (m_imageIndex < ((Number) index.get(0)).intValue()) {
						m_imageIndex = ((Number) index.get(0)).intValue();
					}
					if (m_imageIndex >= ((Number) index.get(1)).intValue())
						m_imageIndex = ((Number) index.get(0)).intValue();
				} else if (abs_or.equals(Absolute_Orientation.EAST)) {
					JSONArray index = (JSONArray) ((JSONObject)((JSONObject) m_actions.get("move"))
							.get("orientation")).get("east");
					if (m_imageIndex < ((Number) index.get(0)).intValue()) {
						m_imageIndex = ((Number) index.get(0)).intValue();
					}
					if (m_imageIndex >= ((Number) index.get(1)).intValue())
						m_imageIndex = ((Number) index.get(0)).intValue();
				} else {// WEST
					JSONArray index = (JSONArray) ((JSONObject)((JSONObject) m_actions.get("move"))
							.get("orientation")).get("west");
					if (m_imageIndex < ((Number) index.get(0)).intValue()) {
						m_imageIndex = ((Number) index.get(0)).intValue();
					}
					if (m_imageIndex >= ((Number) index.get(1)).intValue())
						m_imageIndex = ((Number) index.get(0)).intValue();
				}
				break;
			default:
				if (m_imageIndex >= m_maxsprite)
					m_imageIndex = 0;
			}
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastUpdateTime > ANIMATION_INTERVAL) {
				m_imageIndex++;
				lastUpdateTime = currentTime; // Réinitialiser le dernier temps de mise à jour
			}
		} else if (m_orientations != null) {

		} else {
			long currentTime = System.currentTimeMillis();
			if (currentTime - lastUpdateTime > ANIMATION_INTERVAL && m_imageIndex <= m_maxsprite) {
				m_imageIndex++;
				lastUpdateTime = currentTime; // Réinitialiser le dernier temps de mise à jour
			}else if(currentTime - lastUpdateTime > ANIMATION_INTERVAL){
				m_imageIndex = 0;
				lastUpdateTime = currentTime;
			}
		}
	}

	@Override
	public Image[] get_images() {
		// TODO Auto-generated method stub
		return null;
	}

}
