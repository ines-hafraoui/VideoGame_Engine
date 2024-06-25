package info3.game.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Iterator;
import java.util.List;

import game.entity.Entity;
import game.model.Model;
import info3.game.avatar.Avatar;

/*
 * A Viewport is centered around one or two entities of type Player
 * So the view shows what is happening around those players
 */
public class Viewport extends AViewport {

	private static final long serialVersionUID = 4264890697854297025L;

	InventoryMenu m_inventory;
	List<Avatar> m_avatars;
	private MapView m_map;
	Avatar m_player;
	int m_oldpositionx, m_oldpositiony;

	Viewport(Model model, List<Avatar> avatars, View parent, Dimension d, int x, int y, Avatar player, MapView m) {
		m_parent = parent;
		m_model = model;
		m_avatars = avatars;
		m_map = m;
		setDimension(d);
		Entity[] inventory = player.m_entity.get_inventory();
		if (inventory != null)
			m_inventory = new InventoryMenu(this, inventory);
		m_x = x;
		m_y = y;
		m_player = player;
		m_oldpositionx = (int) m_player.m_entity.get_x();
		m_oldpositiony = (int) m_player.m_entity.get_y();
		Caculatetranslation(m_oldpositionx, m_oldpositiony);
		m_map = new MapView(0, 0, m_model, parent, this);
	}

	public void paint(Graphics g) {
		int x = (int) m_player.m_entity.get_x();
		int y = (int) m_player.m_entity.get_y();
		if ((x != m_oldpositionx || y != m_oldpositiony) || m_parent.Changed) {
			Caculatetranslation(x, y);
		}
		Graphics mg = g.create(m_x, m_y, m_d.width, m_d.height);
		m_map.paint(mg, -m_trx, -m_try);
		Iterator<Avatar> iter = m_avatars.iterator();
		while (iter.hasNext()) {
			Avatar a = iter.next();
			if (!a.m_entity.equals(m_player.m_entity) && a.within(m_inWorldBounds, -m_trx, -m_try)) {
				a.paint(mg, -m_trx, -m_try);
			}
		}
		m_player.paintmainplayer(mg, m_d.width / 2, m_d.height / 2);
		m_inventory.paint(mg,
				m_d.width / 2
						- InventoryMenu.NBCASE / 2 * (m_inventory.get_image().getWidth() * InventoryMenu.INVENTORYSIZE),
				m_d.height - m_inventory.get_image().getHeight() - 100);
	}

	public void setX(int x) {
		m_x = x;
	}
}
