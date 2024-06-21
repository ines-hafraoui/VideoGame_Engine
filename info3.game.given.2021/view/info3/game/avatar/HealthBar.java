package info3.game.avatar;

import java.awt.Color;
import java.awt.Graphics;

public class HealthBar {

	Avatar m_avatar;

	public HealthBar(Avatar avatar) {
		m_avatar = avatar;
	}

	protected void drawHealthBar(Graphics g, int x, int y, int width, int height,int r_health) {
		// int health= m_entity.health;
		int health = r_health;
		int healthWidth = (int) ((width * health) / 100.0);

		// Fond de la barre de santé
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);

		// Barre de santé actuelle
		g.setColor(Color.GREEN);
		g.fillRect(x, y, healthWidth, height);

		// Bordure de la barre de santé
		g.setColor(Color.BLACK);
		g.drawRect(x, y, width, height);
	}

}
