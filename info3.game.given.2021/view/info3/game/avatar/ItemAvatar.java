package info3.game.avatar;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.entity.Entity;
import info3.game.view.View;

public class ItemAvatar extends Avatar {

	public ItemAvatar(Entity e, View v) throws IOException {
		super(e, v);
		m_imageIndex = 0;
		m_images = View.loadSprite("resources/MiniWorldSprites/Miscellaneous/Chests.png", 1, 2);
	}

	public ItemAvatar(){
		super();
		m_imageIndex = 0;
		m_images = View.loadSprite("resources/MiniWorldSprites/Miscellaneous/Chests.png", 1, 2);
	}

	@Override
	public void paint(Graphics g, int x, int y) {
		BufferedImage img = m_images[m_imageIndex];
		g.drawImage(img, (x + (int) m_entity.get_x() * View.DISPLAYSCALE) - (img.getWidth() * View.DISPLAYSCALE),
				(y + (int) m_entity.get_y() * View.DISPLAYSCALE) - (img.getWidth() * View.DISPLAYSCALE),
				img.getWidth() * View.DISPLAYSCALE, img.getHeight() * View.DISPLAYSCALE, null);
	}

	public BufferedImage[] get_images() {
		return m_images;
	}

	@Override
	protected void configureAnimation() {
		// TODO Auto-generated method stub

	}

	@Override
	public void paintmainplayer(Graphics g, int x, int y) {
		// TODO Auto-generated method stub
		
	}
}
