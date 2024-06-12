package game.map;

import game.entity.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

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

            boolean onBoundary = ((yi <= y && y < yj) || (yj <= y && y < yi)) &&
                                 (x < (xj - xi) * (y - yi) / (yj - yi) + xi);
            if (onBoundary) return true;

            if ((yi > y) != (yj > y) && x < (xj - xi) * (y - yi) / (yj - yi) + xi) {
                inside = !inside;
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

    public void cleanPolygon() {
        this.vertices = convexHull(vertices);
    }

    private List<Position> convexHull(List<Position> points) {
        if (points.size() < 3) {
            return points;
        }

        // Find the point with the smallest y-coordinate (and x in case of tie)
        Position start = Collections.min(points, Comparator.comparing(Position::getPositionY)
            .thenComparing(Position::getPositionX));

        // Sort points by angle with the start point
        points.sort((a, b) -> {
            if (a.equals(start)) return -1;
            if (b.equals(start)) return 1;
            float angleA = angle(start, a);
            float angleB = angle(start, b);
            return Float.compare(angleA, angleB);
        });

        Stack<Position> hull = new Stack<>();
        hull.push(start);
        hull.push(points.get(1));

        for (int i = 2; i < points.size(); i++) {
            Position top = hull.pop();
            while (!hull.isEmpty() && !isCounterClockwise(hull.peek(), top, points.get(i))) {
                top = hull.pop();
            }
            hull.push(top);
            hull.push(points.get(i));
        }

        return new ArrayList<>(hull);
    }

    private float angle(Position a, Position b) {
        return (float) Math.atan2(b.getPositionY() - a.getPositionY(), b.getPositionX() - a.getPositionX());
    }

    private boolean isCounterClockwise(Position a, Position b, Position c) {
        return (b.getPositionX() - a.getPositionX()) * (c.getPositionY() - a.getPositionY()) 
                - (b.getPositionY() - a.getPositionY()) * (c.getPositionX() - a.getPositionX()) > 0;
    }

    public float getArea() {
        int n = vertices.size();
        if (n < 3) return 0;

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
}
