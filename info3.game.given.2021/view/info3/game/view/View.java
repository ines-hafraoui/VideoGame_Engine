package info3.game.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.entity.Apple;
import game.entity.Entity;
import game.entity.Head;
import game.entity.Snake;
import game.model.Model;

public class View extends Container {

	private Model m_model;
	private List<Avatar> avatars;
	private int m_width, m_height, m_border, m_x = 0, m_y = 0;
	private MapView m_map;

	public View(Model model, int width, int height, int border) {
		m_model = model;
		m_width = width;
		m_height = height;
		m_border = border;
		avatars = new ArrayList();
		m_map=new MapView(m_x,m_y,m_model);
		List<Entity> Es = m_model.get_grid().getEntities();
		Iterator<Entity> iter = Es.iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			if (e instanceof Apple) {
				new AppleAvatar((Apple) e, this);
			}
			if (e instanceof Snake) {
				Avatar a = new SnakeAvatar((Snake)e, this);
			}
		}
	}

	public View(Model model, int x, int y, int width, int height, int border) {
		m_model = model;
		m_width = width;
		m_height = height;
		m_border = border;
		m_x = x;
		m_y = y;
		avatars = new ArrayList();
		List<Entity> Es = m_model.get_grid().getEntities();
		Iterator<Entity> iter = Es.iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			if (e instanceof Apple) {
				Avatar a = new AppleAvatar((Apple) e, this);
			}
			if (e instanceof Snake) {
				Avatar a = new SnakeAvatar((Snake)e, this);
			}

		}
	}

	public void paint(Graphics g) {
		
		g.setColor(Color.DARK_GRAY);
		m_map.paint(g);

		int box_width = (m_width + get_border()) / m_model.get_grid().getnboxline();
		int box_height = (m_height - get_border()) / m_model.get_grid().getnboxcol();
		
//		for (int i = 0; i < m_model.get_grid().getnboxcol(); i++) {
//			for (int j = 0; j < m_model.get_grid().getnboxline(); j++) {
//				g.setColor(Color.GREEN);
//				g.fillRect(get_x() + j * box_width + get_border(), get_y() + i * box_height + get_border(),
//						box_width - (get_border() * 2), box_height - get_border());
//			}
//		}

		Iterator<Avatar> iterator = avatars.iterator();
		while (iterator.hasNext()) {
			Avatar avatar = iterator.next();
			avatar.paint(g, box_width, box_height);
		}

//		for (Entity e : m_model.get_grid().getEntities()) {
//			
//			Entity[] eList = e.get_entity();
//			
//			for (Entity e_e : eList) {
//				
//				g.setColor(Color.DARK_GRAY);
//				g.fillRect(get_x() + e_e.get_x() * box_width + get_border(),get_y() + e_e.get_y() * box_height + get_border(), box_width - (get_border() * 2),
//						box_height - get_border());
//
//			}
//		}
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
