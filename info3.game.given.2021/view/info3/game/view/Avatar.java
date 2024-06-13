package info3.game.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import game.entity.Entity;

public abstract class Avatar extends Component{

	Entity m_entity;
	View m_view;
	BufferedImage[] m_images;
	int m_imageIndex;
	
	public Avatar(Entity e, View v) {
		m_view = v;
		m_entity = e;
		m_view.add_avatar(this);
	}

	public abstract void paint(Graphics g, int box_width, int box_height);
	
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
	
	protected void drawHealthBar(Graphics g, int x, int y, int width, int height) {
        //int health= m_entity.health;
		int health = 50;
        int healthWidth = (int) ((width * health) / 100.0);
        
        // Fond de la barre de santé
        g.setColor(Color.GRAY);
        g.fillRect(x, y, width, height);

        // Barre de santé actuelle
        g.setColor(Color.BLUE);
        g.fillRect(x, y, healthWidth, height);

        // Bordure de la barre de santé
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }
}
