package info3.game.avatar;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import org.json.simple.JSONObject;

import game.entity.Entity;
import info3.game.view.View;

public class TestAvatar extends Avatar {

	private static final int BASESIZE = 4;

	boolean m_animate;

	public TestAvatar(Entity e, View v, JSONObject sprite_spec) {
		super(e, v);
		m_imageIndex = ((Number) sprite_spec.get("index")).intValue();
		int nr = ((Number) sprite_spec.get("nb_rows")).intValue();
		int nc = ((Number) sprite_spec.get("nb_cols")).intValue();
		m_images = View.loadSprite((String) sprite_spec.get("filepath"), nr, nc);
		m_animate = (Boolean) sprite_spec.get("animation");
	}

	@Override
	public void paint(Graphics g, int x, int y) {
		if (m_entity.get_state_action() != null) {
			a_state = StateToString(m_entity.get_state_action());
		}
		BufferedImage img = m_images[m_imageIndex];
		g.drawImage(img, (x + (int) m_entity.get_x() * View.DISPLAYSCALE) - (img.getWidth() * View.DISPLAYSCALE),
				(y + (int) m_entity.get_y() * View.DISPLAYSCALE) - (img.getHeight() * View.DISPLAYSCALE),
				img.getWidth() * View.DISPLAYSCALE*BASESIZE, img.getHeight() * View.DISPLAYSCALE*BASESIZE, null);
		m_hb.drawHealthBar(g, x + (int) m_entity.get_x() - (img.getWidth() * View.DISPLAYSCALE),
				y + (int) m_entity.get_y() - (img.getHeight() * View.DISPLAYSCALE) - 5 % img.getHeight(),
				(img.getWidth() * View.DISPLAYSCALE), 5 % img.getHeight(),this.m_entity.get_HP());
		if(m_animate) {
			configureAnimation();
		}
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
