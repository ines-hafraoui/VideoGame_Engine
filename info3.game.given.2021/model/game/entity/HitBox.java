package game.entity;

import java.util.ArrayList;
import java.util.List;

import game.map.Polygon;

public class HitBox {

	private Entity e;
	private float width;
	private float height;
	private Polygon polygon;

	public HitBox(float wid, float hei) {
		width = wid;
		height = hei;
	}

	public float getHbWidth() {
		return width;
	}

	public float getHbHeight() {
		return height;
	}

	public Polygon get_polygon() {
		updatePolygon();

		return polygon;
		
	}

	public void setHbWidth(float w) {
		this.width = w;
		updatePolygon();

	}

	public void setHbHeight(float h) {
		this.height = h;
		updatePolygon();
	}

	public void setEntity(Entity e) {
		this.e = e;
		updatePolygon();
	}

	public void setPolygone() {
		updatePolygon();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		HitBox hb = (HitBox) obj;
		return Float.compare(hb.width, width) == 0 && Float.compare(hb.height, height) == 0;
	}
	
	private void updatePolygon() {
		polygon = create_polygon();
	}

	private Polygon create_polygon() {
		float x_e = e.get_x();
		float y_e = e.get_y();
		Position a = new Position(x_e - (width / 2), y_e - (height / 2));
		Position b = new Position(x_e + (width / 2), y_e - (height / 2));
		Position c = new Position(x_e + (width / 2), y_e + (height / 2));
		Position d = new Position(x_e - (width / 2), y_e + (height / 2));
		List<Position> points = new ArrayList();
		points.add(a);
		points.add(b);
		points.add(c);
		points.add(d);
		return new Polygon(points);
	}

}
