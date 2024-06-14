package info3.game.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import game.entity.Entity;
import game.model.Model;
import info3.game.IFactory;
import info3.game.avatar.Avatar;

public class View extends Container {
	
	//How much of the world we will be showing in each Viewport
	public static final int MAPDISPLAYSCALE = 5; 
	public static final int DISPLAYSCALE = 3; //This is for the size of avatars
	
	
	private Model m_model;
	private int m_x = 0, m_y = 0;
	private IFactory m_f;
	public Dimension m_dimension;
	private Viewport[] m_viewports;
	private List<Avatar> m_avatars;

	public View(Model model, IFactory f, Dimension d) {
		m_model = model;
		m_f = f;
		m_dimension = d;
		m_avatars = new LinkedList<Avatar>();
		List<Entity> entities = m_model.get_entities();
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
//		Dimension vp_d= new Dimension(d.width/2,d.height);
		m_viewports = new Viewport[2];
		m_viewports[0] = new Viewport(m_model, m_avatars, this, d,0,0);
//		m_viewports[1] = new Viewport(m_model, m_avatars, this, vp_d,(m_dimension.width/2),0);
		setLayout(new BorderLayout());
		add(m_viewports[0], BorderLayout.EAST);
//		add(m_viewports[1], BorderLayout.WEST);
	}

	public View(Model model, int x, int y, Dimension d, IFactory f) {
		m_model = model;
		m_x = x;
		m_y = y;
		m_f = f;
		m_model = model;
		m_dimension = d;
		m_avatars = new LinkedList<Avatar>();
		List<Entity> entities = m_model.get_entities();
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
		Dimension vp_d= new Dimension((d.width/8),d.height);
		m_viewports = new Viewport[2];
		m_viewports[0] = new Viewport(m_model, m_avatars, this, vp_d,0,0);
		m_viewports[1] = new Viewport(m_model, m_avatars, this, vp_d, 0,0);
		setLayout(new BorderLayout());
		add(m_viewports[0], BorderLayout.EAST);
		add(m_viewports[1], BorderLayout.WEST);
	}

	public void paint(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		Graphics mg = g.create(0, 0, m_dimension.width, m_dimension.height);
		m_viewports[0].paint(mg);
//		g.drawLine(m_dimension.width/2, m_y, m_dimension.width/2, m_dimension.height);
//		m_viewports[1].paint(mg);
	}

	public int get_x() {
		return m_x;
	}

	public int get_y() {
		return m_y;
	}

	public void newEntity(Entity e) throws IOException {
		//ajouter un avatar à la liste et notifier le/s viewport/s
		Avatar a = m_f.newAvatar(e, this);
//		m_viewports[0].AddDisplayedAvatar(a);
	}

	public void removeEntity(Entity e) {
		//parcourir la liste d'avatar puis l'enlever de celle-ci et notifier le/s viewport/s
	}

	public void addAvatar(Avatar a) {
		m_avatars.add(a);
	}
	
	public void removeAvatar(Avatar a) {
		m_avatars.remove(a);
	}

}
