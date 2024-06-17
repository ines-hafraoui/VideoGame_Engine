package info3.game.avatar;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.entity.Entity;
import info3.game.view.View;

public class PlayerAvatar extends Avatar {

	public PlayerAvatar(Entity e, View v) throws IOException {
		super();
		m_view = v;
		m_entity = e;
		m_view.addPlayer(this);
		m_imageIndex = 0;
		m_images = View.loadSprite("resources/MiniWorldSprites/Characters/Soldiers/Mounted/RedKnight.png", 12, 4);
	}

	
	@Override
	public void paint(Graphics g, int x, int y) {
		BufferedImage img = m_images[m_imageIndex];
//		m_hb.drawHealthBar(g, (int) (x + m_entity.get_x() + 25), (int) (y + m_entity.get_y() - 5 % img.getHeight()),
//				(img.getWidth() * View.DISPLAYSCALE / 2), 5 % img.getHeight());
		g.drawImage(img, x, y, img.getWidth() * View.DISPLAYSCALE, img.getHeight() * View.DISPLAYSCALE, null);

//		System.out.println("pos = " + m_entity.get_x() +" " + m_entity.get_y());
		
// Animating the character based on which state it is in		
//		switch(a_state) {
//		case IDLE:
//			if(m_imageIndex) {
//				
//			}
//			break;
//		}
		if (m_imageIndex > 46)
			m_imageIndex = 0;
		else
			m_imageIndex++;
	}

}
