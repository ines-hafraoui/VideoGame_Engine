package info3.game.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import game.entity.Player;
import game.model.Model;
import info3.game.IFactory;
import info3.game.avatar.Avatar;

/*
 * A Viewport is centered around one or two entities of type Player
 * So the view shows what is happening around those players
 */
public class Viewport extends Component {

	private static final long serialVersionUID = 4264890697854297025L;

	Model m_model;
	View m_parent;
	IFactory m_f;
	InventoryMenu m_inventory;
	List<Avatar> m_avatars;
	Dimension m_d;
	private MapView m_map;
	int m_x, m_y;
	Rectangle m_inWorldBounds;
	Avatar m_player;

	int m_trx, m_try;
	int m_oldpositionx, m_oldpositiony;

	Viewport(Model model, List<Avatar> avatars, View parent, Dimension d, int x, int y, Avatar player, MapView m) {
		m_parent = parent;
		m_model = model;
		m_d = d;
		m_avatars = avatars;
		m_map = m;

		try {
			if (player.m_entity instanceof Player) {
				m_inventory = new InventoryMenu(this, (Player) player.m_entity);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		m_x = x;
		m_y = y;
		m_player = player;
		m_oldpositionx = (int) m_player.m_entity.get_x();
		m_oldpositiony = (int) m_player.m_entity.get_y();
		Caculatetranslation(m_oldpositionx, m_oldpositiony);
		m_map = new MapView(0, 0, m_model, parent, this);

		// Scaling the bounds' leeway to the zoom given to the map
		m_inWorldBounds = new Rectangle(-20 * View.DISPLAYSCALE, -20 * View.DISPLAYSCALE,
				d.width + (20 * View.DISPLAYSCALE), d.height + (20 * View.DISPLAYSCALE));
	}

	void setDimension(Dimension d) {
		m_d = d;
	}

	void Caculatetranslation(int x, int y) {
		// Creates bounds of how much of the world can be desplayed
		m_trx = x * View.DISPLAYSCALE - (m_d.width / 2);
		m_try = y * View.DISPLAYSCALE - (m_d.height / 2);
	}

	public void paint(Graphics g) {
		int x = (int) m_player.m_entity.get_x();
		int y = (int) m_player.m_entity.get_y();

		if (x != m_oldpositionx || y != m_oldpositiony) {
			Caculatetranslation(x, y);
		}

		Graphics mg = g.create(m_x, m_y, m_d.width, m_d.height);
		m_map.paint(mg, -m_trx, -m_try);

		Iterator<Avatar> iter = m_avatars.iterator();
		while (iter.hasNext()) {
			Avatar a = iter.next();
			if (!a.equals(m_player) && a.within(m_inWorldBounds, -m_trx, -m_try)) {
				a.paint(mg, -m_trx, -m_try);
			}
		}

		m_player.paintmainplayer(mg, m_d.width / 2, m_d.height / 2);
		try {
			m_inventory.paint(mg);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public boolean withinbounds(int tilex, int tiley) {
		if (m_inWorldBounds.contains(tilex, tiley)) {
			return true;
		}
		return false;
	}
}
