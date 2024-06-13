package info3.game.avatar;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.entity.Entity;

public abstract class Avatar extends Component{

	Entity m_entity;
	Container m_parent;
	BufferedImage[] m_images;
	int m_imageIndex;
	protected HealthBar m_hb;
	
	public Avatar(Entity e, Container p) {
		m_parent = p;
		m_entity = e;
		m_hb = new HealthBar(this);
		m_parent.add(this,BorderLayout.CENTER);
	}

	public abstract void paint(Graphics g);
	
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
