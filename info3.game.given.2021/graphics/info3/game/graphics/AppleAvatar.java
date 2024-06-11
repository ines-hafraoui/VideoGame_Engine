package info3.game.graphics;

import java.awt.Color;
import java.awt.Graphics;

import game.entity.Apple;

public class AppleAvatar extends Avatar {

	public AppleAvatar(Apple e, View v) {
		super(e, v);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Graphics g, int box_width, int box_height) {
			g.setColor(Color.RED);
			g.fillRect(m_view.get_x() + m_entity.get_x() * box_width + m_view.get_border(),
					m_view.get_y() + m_entity.get_y() * box_height + m_view.get_border(),
					box_width - (m_view.get_border() * 2), box_height - m_view.get_border());
	}

}
