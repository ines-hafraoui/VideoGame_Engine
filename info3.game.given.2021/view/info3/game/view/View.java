package info3.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import game.entity.Entity;
import game.model.Model;
import info3.game.IFactory;
import info3.game.avatar.Avatar;

public class View extends Container {

	private static final long serialVersionUID = 5772029785230806250L;

	// How much of the world we will be showing in each Viewport
	public static final int MAPDISPLAYSCALE = 5;
	public static final int DISPLAYSCALE = 2; // This is for the size of avatars

	private Model m_model;
	private int m_x = 0, m_y = 0, m_mwidth, m_mheight;
	private IFactory m_f;
	public Dimension m_d;
	private Viewport[] m_viewports;
	private List<Avatar> m_avatars;
	private List<Avatar> m_players;
	protected BufferedImage[] m_bgimages;

	public View(Model model, IFactory f, Dimension d) {
		m_model = model;
		m_mwidth = model.get_width() * MAPDISPLAYSCALE;
		m_mheight = model.get_height() * MAPDISPLAYSCALE;
		m_f = f;
		m_d = d;
		m_avatars = new LinkedList<Avatar>();
		m_players = new LinkedList<Avatar>();

		List<Entity> entities = m_model.get_entities();
		Iterator<Entity> iter = entities.iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			try {
				m_f.newAvatar(e, this);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		setViewports();

		try {
			m_bgimages = loadSprite("resources/MiniWorldSprites/Ground/TexturedGrass.png", 2, 3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public View(Model model, int x, int y, Dimension d, IFactory f) {
		m_model = model;
		m_mwidth = model.get_width() * MAPDISPLAYSCALE;
		m_mheight = model.get_height() * MAPDISPLAYSCALE;
		m_x = x;
		m_y = y;
		m_f = f;
		m_model = model;
		m_d = d;

		m_avatars = new LinkedList<Avatar>();
		List<Entity> entities = m_model.get_entities();
		Iterator<Entity> iter = entities.iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			try {
				m_f.newAvatar(e, this);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		setViewports();

		try {
			m_bgimages = loadSprite("resources/MiniWorldSprites/Ground/TexturedGrass.png", 2, 3);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {
		Graphics mg = g.create(0, 0, m_d.width, m_d.height);

		for (int i = 0; i < m_mheight; i += (m_bgimages[0].getHeight() * DISPLAYSCALE)) {
			for (int j = 0; j < m_mwidth; j += (m_bgimages[0].getWidth() * DISPLAYSCALE)) {
				BufferedImage img = m_bgimages[getRandomNumber(0, m_bgimages.length)];
				g.drawImage(img, j, i, img.getWidth() * DISPLAYSCALE, img.getHeight() * DISPLAYSCALE, null);
			}
		}
		switch (m_viewports.length) {
		case 1:
			m_viewports[0].paint(mg);
			break;
		case 2:
			m_viewports[0].paint(mg);
			g.drawLine(m_d.width / 2, m_y, m_d.width / 2, m_d.height);
			m_viewports[1].paint(mg);
			break;
		default:
			throw new IllegalArgumentException("You have more than 2 players");
		}
	}

	public int get_x() {
		return m_x;
	}

	public int get_y() {
		return m_y;
	}

	public void newEntity(Entity e) throws IOException {
		// ajouter un avatar à la liste et notifier le/s viewport/s
		Avatar a = m_f.newAvatar(e, this);
	}

	public void removeEntity(Entity e) {
		// parcourir la liste d'avatar puis l'enlever de celle-ci et notifier le/s
		// viewport/s
	}

	public void addAvatar(Avatar a) {
		m_avatars.add(a);
	}

	public void addPlayer(Avatar a) {
		m_players.add(a);
	}

	public void removePlayer(Avatar a) {
		m_players.remove(a);
	}

	public void removeAvatar(Avatar a) {
		m_avatars.remove(a);
	}

	void setDimension(Dimension d) {
		m_d = d;
	}

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

	public static BufferedImage loadImage(String filename) throws IOException {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage img = ImageIO.read(imageFile);
			return img;
		}
		return null;
	}

	public void setViewports() {
		Entity[] players = m_model.get_players();
		int plen = players.length;
		m_viewports = new Viewport[plen];
		int w = m_d.width / plen;
		int h = m_d.height;
		Dimension vp_d = new Dimension(w, h);
		for (int i = 0; i < players.length; i++) {
			try {
				Avatar a = m_f.newAvatar(players[i], this);
				m_viewports[i] = new Viewport(m_model, m_avatars, this, vp_d, i * (m_d.width / plen), 0, a);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

		// How many viewports should be displayed based on how many players there are
		BorderLayout bl = new BorderLayout();
		bl.setVgap(10);
		setLayout(bl);
		switch (plen) {
		case 1:
			add(m_viewports[0], BorderLayout.CENTER);
			break;
		case 2:
			add(m_viewports[0], BorderLayout.EAST);

			add(m_viewports[1], BorderLayout.WEST);
			break;
		default:
			throw new IllegalArgumentException("You have more than 2 players");
		}
	}

	public int getRandomNumber(int min, int max) {
		return (int) ((Math.random() * (max - min)) + min);
	}

}
