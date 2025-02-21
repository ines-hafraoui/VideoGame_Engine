package info3.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import game.entity.Entity;
import game.model.Model;
import info3.game.Game;
import info3.game.IFactory;
import info3.game.avatar.Avatar;

public class View extends Container {

	private static final long serialVersionUID = 5772029785230806250L;

	// How much of the world we will be showing in each Viewport
	public static int DISPLAYSCALE = 1;

	private Model m_model;
	private int m_x = 0, m_y = 0;
	int m_mwidth, m_mheight;
	private IFactory m_f;
	public Dimension m_d;
	private Viewport[] m_viewports;
	private DynamicViewport m_dviewport;
	private List<Avatar> m_avatars;
	private List<Avatar> m_players;
	MapView m_map;
	MiniMap m_minimap;
	private int gameover = -1;

	public boolean Changed;

	public View(Model model, IFactory f, Dimension d) {
		DISPLAYSCALE = Game.configParse.zoom;
		m_model = model;
		m_mwidth = model.get_width() * DISPLAYSCALE;
		m_mheight = model.get_height() * DISPLAYSCALE;
		m_f = f;
		m_d = d;
		m_avatars = new LinkedList<Avatar>();
		m_players = new LinkedList<Avatar>();
		m_minimap = new MiniMap(this, m_model);

		// Creates every avatar made at initialization
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

		Changed = false;

		setViewports();
	}

	public void paint(Graphics g) {
		Graphics mg = g.create(m_x, m_y, m_d.width, m_d.height);
		switch (m_viewports.length) {
		case 1:
			m_viewports[0].paint(mg);
			break;
		case 2:
			if (!m_dviewport.withinSameVP()) {
				m_viewports[0].paint(mg);
				m_viewports[1].paint(mg);
				mg.setColor(Color.black);
				mg.drawLine(m_d.width / 2, m_y, m_d.width / 2, m_d.height);
			} else {
				m_dviewport.paint(mg);
			}
			break;
		default:
			throw new IllegalArgumentException("You have more than 2 players");
		}
		// Asks the minimap to display itself within the overall view so that it is
		// shared by both players
		m_minimap.paint(mg);
		long timer = m_model.get_timer();
		if (gameover != -1) {
			GameOver(g);
		}
		Affichage_timer(mg, timer);
	}

	public void GameOver(int team) {
		gameover = team;
	}

	private void GameOver(Graphics g) {
		if (gameover == Entity.TEAM2) {
			g.setColor(Color.RED); // Set the color for the timer text
			g.setFont(new Font("Arial", Font.BOLD, 90 % m_d.width));
			FontMetrics fm = g.getFontMetrics();
			String message = "PLAYER 1 WIN !";
			g.drawString(message, (m_d.width - fm.stringWidth(message)) / 2, m_d.height / 2);

		} else if (gameover == Entity.TEAM1) {
			g.setColor(Color.CYAN); // Set the color for the timer text
			g.setFont(new Font("Arial", Font.BOLD, 90 % m_d.width));
			FontMetrics fm = g.getFontMetrics();
			String message = "PLAYER 2 WIN !";
			g.drawString(message, (m_d.width - fm.stringWidth(message)) / 2, m_d.height / 2);
		} else {
			g.setColor(Color.WHITE); // Set the color for the timer text
			g.setFont(new Font("Arial", Font.BOLD, 90 % m_d.width));
			FontMetrics fm = g.getFontMetrics();
			String message = "GAME OVER";
			g.drawString(message, (m_d.width - fm.stringWidth(message)) / 2, m_d.height / 2);
		}
	}

	public void Affichage_timer(Graphics mg, long timer) {
		mg.setColor(Color.WHITE); // Set the color for the timer text
		mg.setFont(new Font("Arial", Font.BOLD, 20 % m_d.width));
		String timeString = formatTime(timer); // Format timer into a readable format
		mg.drawString(timeString, 10 % m_d.width, 20 % m_d.height);
	}

	private String formatTime(long millis) {
		long seconds = millis / 1000;
		long minutes = seconds / 60;
		seconds = seconds % 60;
		return String.format("%02d:%02d", minutes, seconds);
	}

	public int get_x() {
		return m_x;
	}

	public int get_y() {
		return m_y;
	}

	/*
	 * THE FOLLOWING 4 METHODS ARE MEANT TO BE CALLED BY THE MODEL LISTENER ONCE
	 * SOMETHING HAPPENS IN THE WORLD
	 */
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

	public void setDimension(Dimension d) {
		m_d = d;
		switch (m_viewports.length) {
		case 1:
			m_viewports[0].setDimension(m_d);
			break;
		case 2:
			Dimension dv = new Dimension(d.width / 2, d.height);
			m_viewports[0].setX(0);
			m_viewports[0].setDimension(dv);
			m_viewports[1].setX(d.width / 2);
			m_viewports[1].setDimension(dv);
			break;
		default:
			throw new IllegalArgumentException("You have more than 2 players");
		}
		m_dviewport.setDimension(m_d);
		Changed = true;
	}

	public void setDimension(int w, int h) {
		m_d = new Dimension(w, h);
		switch (m_viewports.length) {
		case 1:
			m_viewports[0].setDimension(m_d);
			break;
		case 2:
			Dimension d = new Dimension(w / 2, h);
			m_viewports[0].setX(0);
			m_viewports[0].setDimension(d);
			m_viewports[1].setX(w / 2);
			m_viewports[1].setDimension(d);
			break;
		default:
			throw new IllegalArgumentException("You have more than 2 players");
		}
		m_dviewport.setDimension(m_d);
		Changed = true;
	}

	public static BufferedImage[] loadSprite(String filename, int nrows, int ncols) {
		File imageFile = new File(filename);
		if (imageFile.exists()) {
			BufferedImage image;
			try {
				image = ImageIO.read(imageFile);
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
		int nb_p = m_players.size();
		m_viewports = new Viewport[nb_p];
		int w = m_d.width / nb_p;
		int h = m_d.height;
		Dimension vp_d = new Dimension(w, h);
		int i = 0;
		Iterator<Avatar> iter = m_players.iterator();
		while (iter.hasNext()) {
			Avatar a = iter.next();
			m_viewports[i] = new Viewport(m_model, m_avatars, this, vp_d, i * (m_d.width / nb_p), 0, a, m_map);
			i++;
		}

		m_dviewport = new DynamicViewport(m_model, m_avatars, this, m_d, 0, 0, m_players, m_map);

		// How many viewports should be displayed based on how many players there are
		BorderLayout bl = new BorderLayout();
		bl.setVgap(10);
		setLayout(bl);
		switch (nb_p) {
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

	public int WorldToViewX(float x) {
		return (int) ((x * View.DISPLAYSCALE) * m_d.width / m_mwidth);
	}

	public int WorldToViewY(float y) {
		return (int) ((y * View.DISPLAYSCALE) * m_d.height / m_mheight);
	}

	public List<Avatar> getAvatars() {
		return m_avatars;
	}

	public List<Avatar> getPlayers() {
		return m_players;
	}

	public void newEntity(Entity e) {
		try {
			m_f.newAvatar(e, this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void removedEntity(Entity e) {
		List<Avatar> torm = ToremoveAvatar(e);
		for (Avatar a : torm) {
			m_avatars.remove(a);
		}
	}

	public List<Avatar> ToremoveAvatar(Entity e) {
		List<Avatar> todelete = new ArrayList<Avatar>();
		Iterator<Avatar> iter = m_avatars.iterator();
		while (iter.hasNext()) {
			Avatar a = iter.next();
			if (a.m_entity == e)
				todelete.add(a);
		}
		return todelete;
	}
}
