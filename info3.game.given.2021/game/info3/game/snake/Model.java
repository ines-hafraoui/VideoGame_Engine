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
package info3.game.snake;

import java.awt.Graphics;
import java.io.IOException;


/**
 * A simple class that holds the images of a sprite for an animated cowbow.
 *
 */
public class Model {

  long m_imageElapsed;
  int m_x=10, m_y=10;
  int m_width;
  Grid m_grid;
  
  Model(Grid grid) throws IOException {
	    m_grid = grid;
  }
  
  public void tick(long elapsed) {
    m_imageElapsed += elapsed;
    if (m_imageElapsed > 200) {
      m_imageElapsed = 0;
    }
    
    m_grid.tick(elapsed);
  }
  
  public void paint(Graphics g, int width, int height) {
    m_width = width;
    m_grid.setBorder(2);
	m_grid.paint(g);
  }


}
