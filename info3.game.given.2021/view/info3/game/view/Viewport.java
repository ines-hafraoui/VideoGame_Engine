package info3.game.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Iterator;
import java.util.List;

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
	MiniMap m_minimap;
	List<Avatar> m_avatars;
	Dimension m_d;
	private MapView m_map;
	int m_x, m_y;
	Avatar m_player;

	Viewport(Model model, List<Avatar> avatars, View parent, int x, int y, Avatar player) {
		m_parent = parent;
		m_model = model;
		m_avatars = avatars;
		m_map = new MapView(0, 0, m_model, this);
		m_minimap = new MiniMap();// EAST SOUTH
		m_inventory = new InventoryMenu(); // SOUTH
		m_x = x;
		m_y = y;
		m_player = player;
	}
	
	
	Viewport(Model model, List<Avatar> avatars, View parent, Dimension d, int x, int y, Avatar player) {
		m_parent = parent;
		m_model = model;
		m_d = d;
		m_avatars = avatars;
		m_map = new MapView(0, 0, m_model, this);
		m_minimap = new MiniMap();// EAST SOUTH
		m_inventory = new InventoryMenu(); // SOUTH
		m_x = x;
		m_y = y;
		m_player = player;
	}

	void addDisplayedAvatar(Avatar avatar) {
		// TODO Auto-generated method stub
	}

	void undisplayAvatar(Avatar avatar) {
		// TODO Auto-generated method stub
	}

	void setDimension(Dimension d) {
		m_d = d;
	}
	
	public void paint(Graphics g) {
		Graphics mg = g.create(m_x, m_y, m_d.width, m_d.height);
		m_map.paint(mg);
		
		Iterator<Avatar> iter = m_avatars.iterator();
		while (iter.hasNext()) {
			Avatar a = iter.next();
			a.paint(g, m_x, m_y);
		}
		
		m_player.paint(mg, m_d.width/2,  m_d.height/2);
	}
}
