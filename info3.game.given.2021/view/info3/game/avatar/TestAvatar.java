package info3.game.avatar;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.entity.Entity;
import info3.game.view.View;

public class TestAvatar extends Avatar {

	private static final int BASESIZE = 4;

	public TestAvatar(Entity e, View v, String filename){
		super(e, v);
		m_imageIndex = 2;
		if (e.get_team() == 1) {
			m_images = View.loadSprite(filename, 2, 3);
		} else {
			m_images = View.loadSprite(filename, 2, 3);
		}
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
		configureAnimation();
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
