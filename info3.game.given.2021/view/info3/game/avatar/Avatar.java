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

	public Avatar(Entity e, View v) {
		m_view = v;
		m_entity = e;
		m_hb = new HealthBar(this);
		m_view.addAvatar(this);
		m_valid = true;
		a_state = IDLE;
	}

	public abstract void paint(Graphics g, int x, int y);

	public static BufferedImage[] loadSprite(String filename, int nrows, int ncols) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image = ImageIO.read(imageFile);
			int width = image.getWidth(null) / ncols;
			int height = image.getHeight(null) / nrows;

			BufferedImage[] images = new BufferedImage[nrows * ncols];
			for (int i = 0; i < nrows; i++) {
				for (int j = 0; j < ncols; j++) {
					int x = j * width;
					int y = i * height;
					images[(i * ncols) + j] = image.getSubimage(x, y, width, height);
				}
			}
			return images;
		}
		return null;
	}

}
