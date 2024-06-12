package game.map;

import game.entity.Position;
import java.util.List;

public class Polygon {

	
    private List<Position> vertices; //vertices in the order you need to link them 

    public Polygon(List<Position> vertices) {
        this.vertices = vertices;
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
}
