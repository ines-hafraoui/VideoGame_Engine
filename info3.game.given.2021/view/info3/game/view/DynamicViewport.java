package info3.game.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import game.entity.Entity;
import game.entity.Player;
import game.model.Model;
import info3.game.avatar.Avatar;

public class DynamicViewport extends AViewport {

	private static final long serialVersionUID = -5919561837744761793L;

	InventoryMenu[] m_inventory;
	List<Avatar> m_avatars;
	List<Avatar> m_players;

	int m_midwaypointx, m_midwaypointy;

	DynamicViewport(Model model, List<Avatar> avatars, View parent, Dimension d, int x, int y, List<Avatar> players,
			MapView m) {
		m_parent = parent;
		m_model = model;
		m_d = d;
		m_avatars = avatars;
		m_map = m;
		m_players = players;
		// Get the center between the to characters and then calculate the translation
		int plen = m_players.size();
		m_inventory = new InventoryMenu[plen];
		int xpts = 0;
		int ypts = 0;
		int i = 0;
		Iterator<Avatar> iter = m_players.iterator();
		while (iter.hasNext()) {
			Avatar a = iter.next();
			xpts += a.m_entity.get_x();
			ypts += a.m_entity.get_y();
			Entity[] inventory = a.m_entity.get_inventory();
			if (inventory != null)
				m_inventory[i] = new InventoryMenu(this, inventory);
			i++;
		}
		m_midwaypointx = xpts / plen;
		m_midwaypointy = ypts / plen;
		m_x = x;
		m_y = y;
		m_map = new MapView(0, 0, m_model, parent, this);

		Caculatetranslation(m_midwaypointx, m_midwaypointy);
		// Scaling the bounds' leeway to the zoom given to the map
		m_inWorldBounds = new Rectangle(10, 10, d.width + (20 * View.DISPLAYSCALE),
				d.height + (20 * View.DISPLAYSCALE));
	}

	@Override
	public void setDimension(Dimension d) {
		m_d = d;

		// Scaling the bounds' leeway to the zoom given to the map
		m_inWorldBounds = new Rectangle(-20, -20, m_d.width + (20 * View.DISPLAYSCALE),
				m_d.height + (20 * View.DISPLAYSCALE));
	}

	public boolean withinSameVP() {
		setmidwaypoint();
		for (Avatar p : m_players) {
			if (!p.within(m_inWorldBounds, -m_trx, -m_try) && !p.within(m_inWorldBounds, m_trx, m_try)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void paint(Graphics g) {
		Graphics mg = g.create(m_x, m_y, m_d.width, m_d.height);
		m_map.paint(mg, -m_trx, -m_try);

		Iterator<Avatar> iter = m_avatars.iterator();
		while (iter.hasNext()) {
			Avatar a = iter.next();
			if (a.within(m_inWorldBounds, -m_trx, -m_try)) {
				a.paint(mg, -m_trx, -m_try);
			}
		}

		m_inventory[0].paint(mg,
				m_d.width / 4 - InventoryMenu.NBCASE / 2
						* (m_inventory[0].get_image().getWidth() * InventoryMenu.INVENTORYSIZE),
				m_d.height - m_inventory[0].get_image().getHeight() - 100);
		m_inventory[1].paint(mg,
				m_d.width
						- (InventoryMenu.NBCASE * (m_inventory[1].get_image().getWidth() * InventoryMenu.INVENTORYSIZE))
						- m_d.width / 9,
				m_d.height - m_inventory[1].get_image().getHeight() - 100);
	}

	private void setmidwaypoint() {
		int xpts = 0;
		int ypts = 0;
		Iterator<Avatar> iter = m_players.iterator();
		while (iter.hasNext()) {
			Avatar a = iter.next();
			// Get the center between the to characters and then calculate the translation
			xpts += a.m_entity.get_x();
			ypts += a.m_entity.get_y();
		}
		if (xpts / m_players.size() != m_midwaypointx || ypts / m_players.size() != m_midwaypointy) {
			m_midwaypointx = xpts / m_players.size();
			m_midwaypointy = ypts / m_players.size();
			Caculatetranslation(m_midwaypointx, m_midwaypointy);
		}
	}

}
