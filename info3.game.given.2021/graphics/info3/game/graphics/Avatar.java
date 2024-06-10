package info3.game.graphics;

import java.awt.Color;
import java.awt.Graphics;

import game.entity.*;

public class Avatar {

Entity m_entity;
View m_view;

	public Avatar(Entity e, View v) {
		m_view=v;
		m_entity=e;
		m_view.add_avatar(this);
	}
	
	public void paint(Graphics g, int box_width, int box_height) {
		if(m_entity instanceof Head) {
			g.setColor(Color.BLUE);
			g.fillRect(m_view.get_x() + m_entity.get_x() * box_width + m_view.get_border(),m_view.get_y() + m_entity.get_y() * box_height + m_view.get_border(), box_width - (m_view.get_border() * 2),
					box_height - m_view.get_border());
		}
		if(m_entity instanceof Block) {
			g.setColor(Color.YELLOW);
			g.fillRect(m_view.get_x() + m_entity.get_x() * box_width + m_view.get_border(),m_view.get_y() + m_entity.get_y() * box_height + m_view.get_border(), box_width - (m_view.get_border() * 2),
					box_height - m_view.get_border());
		}
		if(m_entity instanceof Apple) {
			g.setColor(Color.RED);
			g.fillRect(m_view.get_x() + m_entity.get_x() * box_width + m_view.get_border(),m_view.get_y() + m_entity.get_y() * box_height + m_view.get_border(), box_width - (m_view.get_border() * 2),
					box_height - m_view.get_border());
		}
	}
}
