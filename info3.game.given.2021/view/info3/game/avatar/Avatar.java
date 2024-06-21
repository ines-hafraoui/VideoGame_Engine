package info3.game.avatar;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.entity.Entity;
import info3.game.view.View;

public abstract class Avatar {

	public static final int HIT = 1;
	public static final int WALK = 2;
	public static final int IDLE = 3;

	protected int a_state;

	public Entity m_entity;
	protected View m_view;
	public BufferedImage[] m_images;
	protected int m_imageIndex;
	protected HealthBar m_hb;
	boolean m_valid;

	public Avatar(Entity e, View v) {
		m_view = v;
		m_entity = e;
		m_hb = new HealthBar(this);
		m_view.addAvatar(this);
		m_valid = true;
		a_state = HIT;
	}
	
	public Avatar() {
	}

	public abstract void paint(Graphics g, int x, int y);

	public int StateToString(String s) {
		if (s.equals("HIT")) {
			return HIT;
		} else if (s.equals("MOVE")) {
			return WALK;
		} else {
			return IDLE;
		}
	}

	public void paintmainplayer(Graphics g, int x, int y) {
		BufferedImage img = m_images[m_imageIndex];
		g.drawImage(img, x - (img.getWidth() * View.DISPLAYSCALE), y - (img.getHeight() * View.DISPLAYSCALE),
				img.getWidth() * View.DISPLAYSCALE, img.getHeight() * View.DISPLAYSCALE, null);
		m_hb.drawHealthBar(g, x + (int) m_entity.get_x() - (img.getWidth() * View.DISPLAYSCALE),
				y + (int) m_entity.get_y() - (img.getHeight() * View.DISPLAYSCALE) - 5 % img.getHeight(),
				(img.getWidth() * View.DISPLAYSCALE), 5 % img.getHeight(),this.m_entity.get_HP());
		configureAnimation();
	}

	protected abstract void configureAnimation();

}
