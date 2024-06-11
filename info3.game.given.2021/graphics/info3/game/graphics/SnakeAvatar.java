package info3.game.graphics;

import java.awt.Color;
import java.awt.Graphics;

import game.entity.Block;
import game.entity.Entity;
import game.entity.Head;
import game.entity.Snake;

public class SnakeAvatar extends Avatar{

	public SnakeAvatar(Snake e, View v) {
		super(e, v);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g, int box_width, int box_height) {
		Entity[] eList = m_entity.get_entity();
		
		for (Entity e_e : eList) {
			if (e_e instanceof Head) {
				g.setColor(Color.BLUE);
				g.fillRect(m_view.get_x() + e_e.get_x() * box_width + m_view.get_border(),
						m_view.get_y() + e_e.get_y() * box_height + m_view.get_border(),
						box_width - (m_view.get_border() * 2), box_height - m_view.get_border());
			}
			if (e_e instanceof Block) {
				g.setColor(Color.YELLOW);
				g.fillRect(m_view.get_x() + e_e.get_x() * box_width + m_view.get_border(),
						m_view.get_y() + e_e.get_y() * box_height + m_view.get_border(),
						box_width - (m_view.get_border() * 2), box_height - m_view.get_border());
			}

		}
		
	}
	
}
