package info3.game.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import game.entity.Player;
import game.model.Model;
import info3.game.avatar.Avatar;

public class DynamicViewport extends AViewport {

	private static final long serialVersionUID = -5919561837744761793L;

	InventoryMenu m_inventory;
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
		int xpts = 0;
		int ypts = 0;
		Iterator<Avatar> iter = m_players.iterator();
		while (iter.hasNext()) {
			Avatar a = iter.next();
			// Get the center between the to characters and then calculate the translation
			// (this should be done in a method)
			xpts += a.m_entity.get_x();
			ypts += a.m_entity.get_y();
//			try {
//				// TO CORRECT AS IT IS NOT FLEXIBLE ENOUGH
//				if (a.m_entity instanceof Player) {
//					m_inventory = new InventoryMenu(this, (Player) a.m_entity);
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}
		m_midwaypointx = xpts / 2;
		m_midwaypointy = ypts / 2;
		m_x = x;
		m_y = y;
		m_map = new MapView(0, 0, m_model, parent, this);

		Caculatetranslation(m_midwaypointx, m_midwaypointy);
		// Scaling the bounds' leeway to the zoom given to the map
		m_inWorldBounds = new Rectangle(-20 * View.DISPLAYSCALE, -20 * View.DISPLAYSCALE,
				d.width + (20 * View.DISPLAYSCALE), d.height + (20 * View.DISPLAYSCALE));
	}

	public boolean withinSameVP() {
		for (Avatar p : m_players) {
			if (!p.within(m_inWorldBounds, m_trx, -m_try)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void paint(Graphics g) {
		int xpts = 0;
		int ypts = 0;
		Iterator<Avatar> iter = m_players.iterator();
		while (iter.hasNext()) {
			Avatar a = iter.next();
			// Get the center between the to characters and then calculate the translation
			// (this should be done in a method)
			xpts += a.m_entity.get_x();
			ypts += a.m_entity.get_y();
		}
		if (xpts / m_players.size() != m_midwaypointx || ypts / m_players.size() != m_midwaypointy) {
			m_midwaypointx = xpts / m_players.size();
			m_midwaypointy = ypts / m_players.size();
			Caculatetranslation(m_midwaypointx, m_midwaypointy);
		}

		Graphics mg = g.create(m_x, m_y, m_d.width, m_d.height);
		m_map.paint(mg, -m_trx, -m_try);

		iter = m_avatars.iterator();
		while (iter.hasNext()) {
			Avatar a = iter.next();
			if (a.within(m_inWorldBounds, -m_trx, -m_try)) {
				a.paint(mg, -m_trx, -m_try);
			}
		}

//		try {
//			m_inventory.paint(mg);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

}
