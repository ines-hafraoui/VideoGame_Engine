package info3.game.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.model.Model;

public abstract class AViewport extends Component {

	protected Dimension m_d;
	protected Rectangle m_inWorldBounds;
	protected int m_x, m_y;
	Model m_model;
	View m_parent;
	protected MapView m_map;
	protected int m_trx;
	protected int m_try;

	public abstract void paint(Graphics g);


	public boolean withinbounds(int tilex, int tiley) {
		if (m_inWorldBounds.contains(tilex, tiley)) {
			return true;
		}
		return false;
	}
	

	public void setDimension(Dimension d) {
		m_d = d;
		// Scaling the bounds' leeway to the zoom given to the map
		m_inWorldBounds = new Rectangle(-20 * View.DISPLAYSCALE, -20 * View.DISPLAYSCALE,
				m_d.width + (20 * View.DISPLAYSCALE), m_d.height + (20 * View.DISPLAYSCALE));
	}

	void Caculatetranslation(int x, int y) {
		// Creates bounds of how much of the world can be desplayed
		m_trx = x * View.DISPLAYSCALE - (m_d.width / 2);
		m_try = y * View.DISPLAYSCALE - (m_d.height / 2);
	}
}
