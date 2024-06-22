/*
 * Copyright (C) 2020  Pr. Olivier Gruber
 * Educational software for a basic game development
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *  Created on: March, 2020
 *      Author: Pr. Olivier Gruber
 */
package info3.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;

import game.entity.Entity;
import game.model.Model;
import game.model.Model.ModelListener;
import game.model.Parser;
import info3.controller.CanvasListener;
import info3.game.sound.RandomFileInputStream;
import info3.game.view.GameCanvas;
import info3.game.view.View;

public class Game {

	static Game game;

	public static void main(String args[]) throws Exception {
		try {
			System.out.println("Game starting...");
			game = new Game();
			System.out.println("Game started.");
		} catch (Throwable th) {
			th.printStackTrace(System.err);
		}
	}

	JFrame m_frame;
	JLabel m_text;
	GameCanvas m_canvas;
	CanvasListener m_listener;
	View m_view;
	public Model m_model;
	Sound m_music;
	public Map<String, Object> sprites = new HashMap<>();
	Dimension c;

	Game() throws Exception {
		// creating a model, that would be a model
		// in an Model-View-Controller pattern (MVC)
		Dimension d = new Dimension(1800, 1000);
		IFactory factory = new Game1Factory();
		// Parse the config file
		String parsePath = new File("model/configjeu1.json").getAbsolutePath();
		Parser configParse = new Parser(parsePath);

		m_model = new Model(d.width, d.height, configParse, factory);

		m_model.setListener(new SyncViewModel());

		// creating a listener for all the events
		// from the game canvas, that would be
		// the controller in the MVC pattern
		m_listener = new CanvasListener(this);
		// creating the game canvas to render the game,
		// that would be a part of the view in the MVC pattern
		m_canvas = new GameCanvas(m_listener);

		sprites = configParse.sprites;
		Dimension viewd = new Dimension(1000, 700);
		m_view = new View(m_model, factory, viewd);

		System.out.println("  - creating frame...");
		m_frame = m_canvas.createFrame(viewd);
		System.out.println("  - setting up the frame...");
		c = new Dimension(m_canvas.getWidth(), m_canvas.getHeight());

		setupFrame();
	}

	/*
	 * Then it lays out the frame, with a border layout, adding a label to the north
	 * and the game canvas to the center.
	 */
	private void setupFrame() {

		m_frame.setTitle("Game");
		m_frame.setLayout(new BorderLayout());

		m_frame.add(m_canvas, BorderLayout.CENTER);

		// center the window on the screen
		m_frame.setLocationRelativeTo(null);

		// make the vindow visible
		m_frame.setVisible(true);
	}

	/*
	 * ================================================================ All the
	 * methods below are invoked from the GameCanvas listener, once the window is
	 * visible on the screen.
	 * ==============================================================
	 */

	/*
	 * Called from the GameCanvas listener when the frame
	 */
	String m_musicName;

	public void loadMusic() {
		m_musicName = m_musicNames[m_musicIndex];
		String filename = "resources/" + m_musicName + ".ogg";
		m_musicIndex = (m_musicIndex + 1) % m_musicNames.length;
		try {
			RandomAccessFile file = new RandomAccessFile(filename, "r");
			RandomFileInputStream fis = new RandomFileInputStream(file);
			m_canvas.playMusic(fis, 0, 1.0F);
		} catch (Throwable th) {
			th.printStackTrace(System.err);
			System.exit(-1);
		}
	}

	private int m_musicIndex = 0;
	private String[] m_musicNames = new String[] { "Runaway-Food-Truck" };

	private long m_textElapsed;

	/*
	 * This method is invoked almost periodically, given the number of milli-seconds
	 * that elapsed since the last time this method was invoked.
	 */
	public void tick(long elapsed) {
		m_model.tick(elapsed);

		// Update every second
		// the text on top of the frame: tick and fps
		m_textElapsed += elapsed;
		if (m_textElapsed > 1000) {
			m_textElapsed = 0;
			float period = m_canvas.getTickPeriod();
			int fps = m_canvas.getFPS();
			System.out.println("Elapsed=" + period + " FPS=" + fps);
		}
	}

	/*
	 * This request is to paint the Game Canvas, using the given graphics. This is
	 * called from the GameCanvasListener, called from the GameCanvas.
	 */
	public void paint(Graphics g) {
		
		
		// getickt the size of the canvas
		int width = m_canvas.getWidth();
		int height = m_canvas.getHeight();
		if(c.width != width && c.height !=height) {
			m_view.setDimension(width,height);  
			c.width = width;
			c.height = height;
		}
		// erase background
		g.setColor(Color.gray);
		g.fillRect(0, 0, width, height);

		// paint
		m_view.paint(g);
	}

	class SyncViewModel implements ModelListener {

		@Override
		public void addedEntity(Entity e) {
			m_view.newEntity(e);
		}

		@Override
		public void removedEntity(Entity e) {
			m_view.removedEntity(e);
		}
	}

}
