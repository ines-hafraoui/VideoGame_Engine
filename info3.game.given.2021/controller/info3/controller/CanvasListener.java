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
package info3.controller;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import info3.game.Game;
import info3.game.view.GameCanvasListener;

public class CanvasListener implements GameCanvasListener {
	Game m_game;

	public CanvasListener(Game game) {
		m_game = game;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse clicked: (" + e.getX() + "," + e.getY() + ")");
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("Mouse pressed: (" + e.getX() + "," + e.getY() + ")");
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("Mouse released: (" + e.getX() + "," + e.getY() + ")");
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("Mouse entered: (" + e.getX() + "," + e.getY() + ")");
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("Mouse exited: (" + e.getX() + "," + e.getY() + ")");
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("Mouse dragged: (" + e.getX() + "," + e.getY() + ")");
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("Mouse moved: (" + e.getX() + "," + e.getY() + ")");
		System.out.println("   modifiers=" + e.getModifiersEx());
		System.out.println("   buttons=" + e.getButton());
	}

	@Override
	public void keyTyped(KeyEvent e) {
		System.out.println("Key typed: " + e.getKeyChar() + " code=" + e.getKeyCode());
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Key pressed: " + e.getKeyChar() + " code=" + e.getKeyCode());
		switch(e.getKeyCode()) {
		case 81 :// gauche joueur1 'q'
			m_game.m_model.entities[0].get_automate().set_key(81);
		case 68 :// droite joueur1 'd'
			m_game.m_model.entities[0].get_automate().set_key(68);
		case 90 :// haut joueur1 'z'
			m_game.m_model.entities[0].get_automate().set_key(90);
		case 83 :// bas joueur1 's'
			m_game.m_model.entities[0].get_automate().set_key(83);
		case 32 :// Hit joueur1 ' '
			m_game.m_model.entities[0].get_automate().set_key(32);
		case 65 :// Select joueur1 'a'
			m_game.m_model.entities[0].get_automate().set_key(65);
		case 69 :// Accepter sélection joueur1 'e'
			m_game.m_model.entities[0].get_automate().set_key(69);
			
		case 37 :// gauche joueur2 'Flèche gauche'
			m_game.m_model.entities[1].get_automate().set_key(37);
		case 39 :// droite joueur2 'Flèche droite'
			m_game.m_model.entities[1].get_automate().set_key(39);
		case 38 :// haut joueur2 'Flèche haute'
			m_game.m_model.entities[1].get_automate().set_key(38);
		case 40 :// bas joueur2 'Flèche basse'
			m_game.m_model.entities[1].get_automate().set_key(40);
		case 517 :// Hit joueur2 '!'
			m_game.m_model.entities[1].get_automate().set_key(517);
		case 0 :// Select joueur2 'ù'
			m_game.m_model.entities[1].get_automate().set_key(0);
		case 10 :// Accepter sélection joueur2 'Entrée'
			m_game.m_model.entities[1].get_automate().set_key(10);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("Key released: " + e.getKeyChar() + " code=" + e.getKeyCode());
	}

	@Override
	public void tick(long elapsed) {
		m_game.tick(elapsed);
	}

	@Override
	public void paint(Graphics g) {
		m_game.paint(g);
	}

	@Override
	public void windowOpened() {
		m_game.loadMusic();
//    m_game.m_canvas.setTimer(6000);
	}

	@Override
	public void exit() {
	}

//  boolean m_expired;
	@Override
	public void endOfPlay(String name) {
//    if (!m_expired) // only reload if it was a forced reload by timer
		m_game.loadMusic();
//    m_expired = false;
	}

	@Override
	public void expired() {
		// will force a change of music, after 6s of play
//    System.out.println("Forcing an ealy change of music");
//    m_expired = true;
//    m_game.loadMusic();    
	}

}
