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
	
	protected Entity m_entity;
	protected View m_view;
	protected BufferedImage[] m_images;
	protected int m_imageIndex;
	protected HealthBar m_hb;
	boolean m_valid;

	public Avatar() {
		m_hb = new HealthBar(this);
		m_valid = true;
		a_state = IDLE;
	}
	
	public Avatar(Entity e, View v) {
		m_view = v;
		m_entity = e;
		m_hb = new HealthBar(this);
		m_view.addAvatar(this);
		m_valid = true;
		a_state = HIT;
	}

	public abstract void paint(Graphics g, int x, int y);

}
