package info3.game.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import game.entity.Apple;
import game.entity.Block;
import game.entity.Entity;
import game.entity.Head;

public abstract class Avatar extends Component{

	Entity m_entity;
	View m_view;

	public Avatar(Entity e, View v) {
		m_view = v;
		m_entity = e;
		m_view.add_avatar(this);
	}

	public abstract void paint(Graphics g, int box_width, int box_height);
}
