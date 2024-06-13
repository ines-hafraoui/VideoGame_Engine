package info3.game.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.entity.Entity;
import game.model.Model;
import info3.game.IFactory;
import info3.game.avatar.Avatar;

public class View extends Container {

	private Model m_model;
	private int m_width, m_height, m_border, m_x = 0, m_y = 0;
	private IFactory m_f;
	private Dimension m_dimension;
	private Viewport m_viewport;

	public View(Model model, IFactory f, Dimension d) {
		m_model = model;
		m_f = f;
		m_dimension = d;
		List<Entity> Es = m_model.get_grid().getEntities();
		Iterator<Entity> iter = Es.iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			m_f.newAvatar(e, this);
		}
		m_viewport = new Viewport();
	}

	public View(Model model, int x, int y, int width, int height, int border, IFactory f) {
		m_model = model;
		m_width = width;
		m_height = height;
		m_border = border;
		m_x = x;
		m_y = y;
		m_f = f;;
		List<Entity> Es = m_model.get_grid().getEntities();
		Iterator<Entity> iter = Es.iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			m_f.newAvatar(e, this);
		}
		m_viewport = new Viewport();
	}

	public void paint(Graphics g) {

		int box_width = (m_width + get_border()) / m_model.get_grid().getnboxline();
		int box_height = (m_height - get_border()) / m_model.get_grid().getnboxcol();
		for (int i = 0; i < m_model.get_grid().getnboxcol(); i++) {
			for (int j = 0; j < m_model.get_grid().getnboxline(); j++) {
				g.setColor(Color.GREEN);
				g.fillRect(get_x() + j * box_width + get_border(), get_y() + i * box_height + get_border(),
						box_width - (get_border() * 2), box_height - get_border());
			}
		}

		Iterator<Avatar> iterator = avatars.iterator();
		while (iterator.hasNext()) {
			Avatar avatar = iterator.next();
			avatar.paint(g, box_width, box_height);
		}
	}

	public int get_x() {
		return m_x;
	}

	public int get_y() {
		return m_y;
	}

	public int get_border() {
		return m_border;
	}

	public List<Avatar> getAvatars() {
		return avatars;
	}

	public void add_avatar(Avatar a) {
		getAvatars().add(a);
	}

}
