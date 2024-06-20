package game.map;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.Stack;

import game.entity.Position;
import info3.game.view.View;

public class Polygon {

	private List<Position> vertices; // vertices in the order you need to link them

	public Polygon(List<Position> vertices) {
		this.vertices = vertices;
	}

	public Polygon() {
		this.vertices = new ArrayList<>();
	}

	public boolean containsPosition(Position position) {
	    int n = vertices.size();
	    boolean inside = false;

	    float x = position.getPositionX();
	    float y = position.getPositionY();

	    for (int i = 0, j = n - 1; i < n; j = i++) {
	        float xi = vertices.get(i).getPositionX();
	        float yi = vertices.get(i).getPositionY();
	        float xj = vertices.get(j).getPositionX();
	        float yj = vertices.get(j).getPositionY();

	        if (((yi <= y && y < yj) || (yj <= y && y < yi)) && 
	            (x == (xj - xi) * (y - yi) / (yj - yi) + xi)) {
	            return true;
	        }

	        if ((yi > y) != (yj > y)) {
	            float intersectX = (xj - xi) * (y - yi) / (yj - yi) + xi;
	            if (x < intersectX) {
	                inside = !inside;
	            }
	        }
	    }

	    return inside;
	}


	public List<Position> getVertices() {
		return vertices;
	}

	public void setVertices(List<Position> vertices) {
		this.vertices = vertices;
	}

	public void addVertex(Position position) {
		vertices.add(position);
	}

	public void removeVertex(Position position) {
		vertices.remove(position);
	}

	public float getArea() {
		int n = vertices.size();
		if (n < 3)
			return 0;

		float area = 0;
		for (int i = 0; i < n; i++) {
			Position current = vertices.get(i);
			Position next = vertices.get((i + 1) % n);
			area += current.getPositionX() * next.getPositionY();
			area -= current.getPositionY() * next.getPositionX();
		}
		return Math.abs(area) / 2.0f;
	}

	public Position getCenter() {
		float sumX = 0;
		float sumY = 0;
		int n = vertices.size();

		for (Position vertex : vertices) {
			sumX += vertex.getPositionX();
			sumY += vertex.getPositionY();
		}

		return new Position(sumX / n, sumY / n);
	}

	public Rectangle getBounds() {
    	if (vertices.isEmpty()) {
            return new Rectangle();
        }

        float minX = Float.MAX_VALUE;
        float minY = Float.MAX_VALUE;
        float maxX = Float.MIN_VALUE;
        float maxY = Float.MIN_VALUE;

        for (Position vertex : vertices) {
            float x = vertex.getPositionX();
            float y = vertex.getPositionY();

            if (x < minX) minX = x;
            if (y < minY) minY = y;
            if (x > maxX) maxX = x;
            if (y > maxY) maxY = y;
        }

        return new Rectangle(Math.round(minX), Math.round(minY), Math.round(maxX - minX), Math.round(maxY - minY));
	}

	public boolean intersects(Rectangle r) {
		for (int i = 0; i < vertices.size(); i++) {
            Position p1 = vertices.get(i);
            Position p2 = vertices.get((i + 1) % vertices.size());

            if (lineIntersectsRect(p1, p2, r)) {
                return true;
            }
        }

        if (containsPosition(new Position(r.x, r.y)) ||
            containsPosition(new Position(r.x + r.width, r.y)) ||
            containsPosition(new Position(r.x, r.y + r.height)) ||
            containsPosition(new Position(r.x + r.width, r.y + r.height))) {
            return true;
        }

        return false;
    }
	
	private boolean lineIntersectsRect(Position p1, Position p2, Rectangle r) {
        return r.intersectsLine(p1.getPositionX(), p1.getPositionY(), p2.getPositionX(), p2.getPositionY());
    }

	
	public List<Position> generatePointsInsidePolygon(int seed) {
		Random random = new Random(seed);
		int numberOfPoints = random.nextInt(1000)%100 + 1;

		int count = 0;
		List<Position> positionsInsideBorders = new ArrayList<>();
		while (count < numberOfPoints) {
			float x = random.nextInt() % getMaxX();
			float y = random.nextInt() % getMaxY();

			Position position = new Position(x, y);
			if (containsPosition(position)) {
				positionsInsideBorders.add(position);
				count++;
			}
		}

		return positionsInsideBorders;
	}
	
    public float getMaxX() {
        float maxX = Float.NEGATIVE_INFINITY;
        for (Position vertex : getVertices()) {
            if (vertex.getPositionX() > maxX) {
                maxX = vertex.getPositionX();
            }
        }
        return maxX;
    }

    // Method to get the maximum y value of the border points
    public float getMaxY() {
        float maxY = Float.NEGATIVE_INFINITY;
        for (Position vertex : getVertices()) {
            if (vertex.getPositionY() > maxY) {
                maxY = vertex.getPositionY();
            }
        }
        return maxY;
    }

	public Polygon scale(int scale) {
		List<Position> new_vertices = new ArrayList<Position>() ;
		for (int i = 0; i < vertices.size(); i++) {
			Position pos = vertices.get(i);
			int x = (int) (pos.getPositionX() * View.DISPLAYSCALE);
			int y = (int) (pos.getPositionY() * View.DISPLAYSCALE);
			new_vertices.add(new Position(x,y));
		}
		return new Polygon(new_vertices);
	}
	
    public boolean intersectsWith(Polygon other) {
        int size1 = this.vertices.size();
        int size2 = other.vertices.size();

        for (int i = 0; i < size1; i++) {
            Position p1 = this.vertices.get(i);
            Position p2 = this.vertices.get((i + 1) % size1);

            for (int j = 0; j < size2; j++) {
                Position p3 = other.vertices.get(j);
                Position p4 = other.vertices.get((j + 1) % size2);

                if (segmentsIntersect(p1, p2, p3, p4)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean segmentsIntersect(Position p1, Position p2, Position p3, Position p4) {
        float x1 = p1.getPositionX();
        float y1 = p1.getPositionY();
        float x2 = p2.getPositionX();
        float y2 = p2.getPositionY();
        float x3 = p3.getPositionX();
        float y3 = p3.getPositionY();
        float x4 = p4.getPositionX();
        float y4 = p4.getPositionY();

        float d1 = direction(x3, y3, x4, y4, x1, y1);
        float d2 = direction(x3, y3, x4, y4, x2, y2);
        float d3 = direction(x1, y1, x2, y2, x3, y3);
        float d4 = direction(x1, y1, x2, y2, x4, y4);

        if (d1 * d2 < 0 && d3 * d4 < 0) {
            return true;
        }

        if (d1 == 0 && onSegment(x3, y3, x4, y4, x1, y1)) return true;
        if (d2 == 0 && onSegment(x3, y3, x4, y4, x2, y2)) return true;
        if (d3 == 0 && onSegment(x1, y1, x2, y2, x3, y3)) return true;
        if (d4 == 0 && onSegment(x1, y1, x2, y2, x4, y4)) return true;

        return false;
    }

    private float direction(float xi, float yi, float xj, float yj, float xk, float yk) {
        return (xk - xi) * (yj - yi) - (xj - xi) * (yk - yi);
    }

    private boolean onSegment(float xi, float yi, float xj, float yj, float xk, float yk) {
        return Math.min(xi, xj) <= xk && xk <= Math.max(xi, xj) && Math.min(yi, yj) <= yk && yk <= Math.max(yi, yj);
    }
}
