package game.map;

import game.entity.Position;
import java.util.List;

public class Polygon {

    private List<Position> vertices; // Vertices in the order you need to link them

    public Polygon(List<Position> vertices) {
        this.vertices = vertices;
    }

    // Check if a position is inside the polygon
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

            if (((yi > y) != (yj > y)) && 
                (x < (xj - xi) * (y - yi) / (yj - yi) + xi)) {
                inside = !inside;
            }
        }

        return inside;
    }

    // Get the vertices of the polygon
    public List<Position> getVertices() {
        return vertices;
    }

    // Add a vertex to the polygon
    public void addVertex(Position position) {
        vertices.add(position);
    }

    // Remove a vertex from the polygon
    public void removeVertex(Position position) {
        vertices.remove(position);
    }

    // Calculate the area of the polygon
    public double getArea() {
        double area = 0;
        int n = vertices.size();
        for (int i = 0; i < n; i++) {
            Position v1 = vertices.get(i);
            Position v2 = vertices.get((i + 1) % n);
            area += v1.getPositionX() * v2.getPositionY() - v2.getPositionX() * v1.getPositionY();
        }
        return Math.abs(area) / 2.0;
    }

    // Get the center of the polygon
    public Position getCenter() {
        double sumX = 0, sumY = 0;
        int n = vertices.size();
        for (Position vertex : vertices) {
            sumX += vertex.getPositionX();
            sumY += vertex.getPositionY();
        }
        return new Position((float) (sumX / n), (float) (sumY / n));
    }
}
