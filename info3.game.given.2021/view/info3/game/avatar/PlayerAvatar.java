package info3.game.avatar;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.entity.Player;

public class PlayerAvatar extends Avatar{

	public static final int HIT = 1;
	public static final int WALK = 2;
	
	public PlayerAvatar(Player e, Container parent) throws IOException {
		super(e,parent);
		m_imageIndex=0;
		loadSprite("resources/perso.png", 8, 12);
	}

	@Override
	public void paint(Graphics g) {
		int etat=2;
		BufferedImage img = m_images[m_imageIndex];
		g.drawImage(img, m_view.get_x() + m_entity.get_x(), m_view.get_y() + m_entity.get_y(), img.getWidth(), img.getHeight(), m_view);
		m_hb.drawHealthBar(g,m_view.get_x() + m_entity.get_x(),m_view.get_y() + m_entity.get_y() - 5%img.getHeight(),img.getWidth(),5%img.getHeight());
	}

}
