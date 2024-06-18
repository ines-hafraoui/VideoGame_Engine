package info3.game.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Polygon;
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
	Rectangle m_inWorldBounds;
	Avatar m_player;

	int m_trx, m_try;

	Viewport(Model model, List<Avatar> avatars, View parent, Dimension d, int x, int y, Avatar player, MapView m) {
		m_parent = parent;
		m_model = model;
		m_d = d;
		m_avatars = avatars;
		m_map = m;
		m_minimap = new MiniMap();// EAST SOUTH
		m_inventory = new InventoryMenu(); // SOUTH
		m_x = x;
		m_y = y;
		m_player = player;

//		m_inWorldBounds = new Rectangle(m_trx,m_try,d.width,d.height);
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

	void translate() {
		// Creates bounds of how much of the world can be desplayed
		m_trx = ((int) m_player.m_entity.get_x()) - (m_d.width / 2);
		m_try = ((int) m_player.m_entity.get_y()) - (m_d.height / 2);
	}

	public void paint(Graphics g) {
		translate();

		Graphics mg = g.create(m_x, m_y, m_d.width, m_d.height);
//		int x = ((int) m_player.m_entity.get_x()) + m_player.m_images[0].getWidth()-100;
//	    int y = ((int) m_player.m_entity.get_y())  + m_player.m_images[0].getHeight() -200;
////		mg.translate(x,y);
//		 // Create a polygon
//        Polygon polygon = new Polygon();
//       
//        int width = (int) m_player.m_entity.get_x()+100;
//        int height =  (int) m_player.m_entity.get_y()+200;
//        polygon.addPoint(x, y);
//        polygon.addPoint(width, y);
//        polygon.addPoint(width, height);
//        polygon.addPoint(x, height);

//		mg.setClip(polygon);
		m_map.paint(mg, m_x, m_y);

		Iterator<Avatar> iter = m_avatars.iterator();
		while (iter.hasNext()) {
			Avatar a = iter.next();
			a.paint(mg, m_trx, m_try);
		}

		m_player.paint(mg, m_d.width / 2, m_d.height / 2);
	}
}
