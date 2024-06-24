package info3.game.avatar;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.entity.ActionType;
import game.entity.Entity;
import info3.game.view.View;

public abstract class Avatar {


	protected String a_state;

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
		a_state = e.get_state_action();
	}

	public Avatar() {
	}

	public abstract void paint(Graphics g, int x, int y);


	public void paintmainplayer(Graphics g, int x, int y) {
		BufferedImage img = m_images[m_imageIndex];
		g.drawImage(img, x - (img.getWidth() * View.DISPLAYSCALE), y - (img.getHeight() * View.DISPLAYSCALE),
				img.getWidth() * View.DISPLAYSCALE, img.getHeight() * View.DISPLAYSCALE, null);
		m_hb.drawHealthBar(g, x - (img.getWidth() * View.DISPLAYSCALE),
				y - (img.getHeight() * View.DISPLAYSCALE) - 5 % img.getHeight(), (img.getWidth() * View.DISPLAYSCALE),
				5 % img.getHeight(), this.m_entity.get_HP());
		configureAnimation();
	}

	public boolean within(Rectangle m_Bounds, int tx, int ty) {
		return m_Bounds.contains(m_entity.get_x()*View.DISPLAYSCALE + tx, m_entity.get_y()*View.DISPLAYSCALE + ty);
	}

	protected abstract void configureAnimation();

	public abstract Image[] get_images();
}
