package info3.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import game.entity.Entity;
import game.model.Model;
import info3.game.IFactory;
import info3.game.avatar.Avatar;

public class Viewport extends Container {

	Model m_model;
	Container m_parent;
	IFactory m_f;
	InventoryMenu m_inventory;
	MiniMap m_map;

	Viewport(Model model, List<Entity> entities, IFactory f, Container parent) {
		super();
		m_parent = parent;
		m_model = model;
		m_parent.add(this, BorderLayout.EAST);
		BorderLayout bl = new BorderLayout();
		setLayout(bl);
		m_f = f;
		Iterator<Entity> iter = entities.iterator();
		while (iter.hasNext()) {
			Entity e = iter.next();
			try {
				m_f.newAvatar(e, this);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		m_map = new MiniMap();// EAST SOUTH
		m_inventory = new InventoryMenu(); // SOUTH
	}

	void UpdateDisplayedAvatar(List<Entity> entities) throws Exception {
		throw new Exception("NYI");
	}

}
