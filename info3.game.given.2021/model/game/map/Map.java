package game.map;

import game.automaton.Relative_Orientation;
import game.entity.Absolute_Orientation;
import game.entity.Entity;
import game.entity.Position;
import game.model.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {

	private int seed;
	private List<Biome> biomes;
	protected Model m_model;

	private Polygon borders; // Vertices in the order that create the polygon that makes the boundaries of
								// the map.

	public Map(Polygon borders) {
		this.biomes = new ArrayList<>();
		generateSeed();

		this.borders = borders;
	}

	/*
	 * Overwrites the current seed.
	 */
	public int generateSeed() {
		Random random = new Random();
		seed = random.nextInt(); // Generates a random integer seed
		return seed;
	}

	public void setBorders(Polygon borders) {
		this.borders = borders;

	}

	public Polygon getBorders() {
		return this.borders;
	}

	public void setSeed(int seed) {
		this.seed = seed;

	}

	public int getSeed() {
		return this.seed;
	}

	public List<Biome> getBiome() {
		return biomes;
	}

	public Biome getBiome(Position position) {
		Biome highestBiome = null;
		float maxZ = Float.NEGATIVE_INFINITY;

		for (Biome biome : biomes) {

			if (biome.getBorders().containsPosition(position) && biome.getPosition().getPositionZ() >= maxZ) {
				highestBiome = biome;
				maxZ = biome.getPosition().getPositionZ();
			}

		}
		return highestBiome;
	}

	public Plot getPlot(Position position) {
		Plot highestPlot = null;
		float maxZ = Float.NEGATIVE_INFINITY;

		Biome biome = getBiome(position);
		for (Plot plot : biome.getPlots()) {

			if (plot.getBorders().containsPosition(position) && plot.getPosition().getPositionZ() >= maxZ) {
				highestPlot = plot;
				maxZ = plot.getPosition().getPositionZ();
			}
		}

		return highestPlot;

	}

	public LandType getLandType(Position position) {
		return getPlot(position).getLandType();
	}

	public float getViscosity(Position position) {

		return getLandType(position).getViscosity();
	}

	public void generateMap(int seed) {

		List<Position> pointsInsideBorders = generatePointsInsidePolygon(seed);

		List<Position> allPoints = addBorderPointsToAllPoints(pointsInsideBorders);

		List<Position> seedPoints = selectSeedPoints(seed, allPoints);

		// Step 6: Créer les polygones à partir des seeds
		List<Polygon> polygons = CreateValidPolygons(seedPoints, allPoints);

		// Nettoyer les polygones pour maximiser leur surface
        for (Polygon polygon : polygons) {
            //polygon.cleanPolygon();
        	biomes.add(new Biome(polygon, new LandType("LAVA", (float) 0.1)));
        }
        
       
	}

	
    private float getMaxX() {
        float maxX = Float.NEGATIVE_INFINITY;
        for (Position vertex : borders.getVertices()) {
            if (vertex.getPositionX() > maxX) {
                maxX = vertex.getPositionX();
            }
        }
        return maxX;
    }

    // Method to get the maximum y value of the border points
    private float getMaxY() {
        float maxY = Float.NEGATIVE_INFINITY;
        for (Position vertex : borders.getVertices()) {
            if (vertex.getPositionY() > maxY) {
                maxY = vertex.getPositionY();
            }
        }
        return maxY;
    }
    
	private List<Position> generatePointsInsidePolygon(int seed) {
		Random random = new Random(seed);
		int numberOfPoints = random.nextInt(100) + 1;

		int count = 0;
		List<Position> positionsInsideBorders = new ArrayList<>();
		while (count < numberOfPoints) {
			float x = random.nextInt() % getMaxX();
			float y = random.nextInt() % getMaxY();

			Position position = new Position(x, y);
			if (borders.containsPosition(position)) {
				positionsInsideBorders.add(position);
				count++;
			}
		}

		return positionsInsideBorders;
	}

	private List<Position> addBorderPointsToAllPoints(List<Position> pointsInsideBorders) {
		List<Position> allPoints = new ArrayList<>(pointsInsideBorders);

		for (Position point : borders.getVertices()) {
			if (!allPoints.contains(point)) {
				allPoints.add(point);
			}
		}
		return allPoints;
	}

	private List<Position> selectSeedPoints(int seed, List<Position> allPoints) {

		Random random = new Random(seed);
		int numberOfSeeds = random.nextInt(allPoints.size());
		List<Position> seedPoints = new ArrayList<>();

		List<Position> to_remove = new ArrayList<>();
		
		// select seeds
		while (seedPoints.size() < numberOfSeeds) {
			int randomIndex = random.nextInt(allPoints.size());
			Position randomPoint = allPoints.get(randomIndex);

			if (!seedPoints.contains(randomPoint)) {
				seedPoints.add(randomPoint);
				to_remove.add(randomPoint);
			}
		}
		
		for (Position p:to_remove) {
			allPoints.remove(p);
		}

		return seedPoints;
	}

	private List<Polygon> CreateValidPolygons(List<Position> seedPoints, List<Position> allPoints) {
		List<Polygon> polygons = new ArrayList<>();

		// Créer un polygone initial pour chaque seed point
		for (Position seed : seedPoints) {
			Polygon poly = new Polygon();
			poly.addVertex(seed);
			polygons.add(poly);
		}

		while (!allPoints.isEmpty()) {
			// Assigner chaque point au polygone le plus proche

			for (Position point : allPoints) {
				Polygon closestPolygon = null;
				double minDistance = Double.MAX_VALUE;
				
				
				for (Polygon poly : polygons) {
					double distance = poly.getCenter().distance(point);
					if (distance < minDistance) {
						minDistance = distance;
						closestPolygon = poly;
					}
				}

				if (closestPolygon != null) {
					closestPolygon.addVertex(point);
				}

			}
			
			allPoints.clear();
			// check for invalid polygons
			// Vérifier les polygones avec des conditions spéciales
			List<Polygon> invalidPolygons = new ArrayList<>();

			for (Polygon poly : polygons) {
				if (poly.getVertices().size() <= 1 || poly.getArea() == 0) {
					allPoints.addAll(poly.getVertices());
					invalidPolygons.add(poly);
				}
			}
			for(Polygon poly : invalidPolygons) {
				polygons.remove(poly);
			}
		}
		
		return polygons;

	}

	public void tick(long elapsed) {
		for (Entity e : m_model.get_entities()) {
			e.tick(elapsed);
		}
		
	}
	
	/*
	 * get_entity return an entity who's around this entity at a distance distance
	 */

	public Entity get_entity(int distance, String type, float currentx, float currenty) {
		for (Entity entity : m_model.get_entities()) {
			if (entity.get_type().equals(type)) {
				float dx = entity.get_x() - currentx; 
				float dy = entity.get_y() - currenty;
				
				float dist = (float) Math.sqrt(dx * dx + dy * dy);
				if (dist <= distance)
					return entity;
			}
		}
		return null;
	}
		

	public boolean eval_abs(Absolute_Orientation dir, float positionX, float positionY, int porte) {
		float newX = positionX; 
		float newY = positionY;
		
		
		switch (dir.get_abs_Orientation()) {
		case "N" :
			newY += porte;
			break;
		case "S" : 
			newY -= porte;
			break;
		case "W" : 
			newX -= porte;
			break; 
		case "E" : 
			newX += porte;
			break;
		default : 
			break;
		}
		
		return isEntityAt(newX, newY);
	}


	public boolean eval_rel(Relative_Orientation dir, float positionX, float positionY, int porte) {
		float newX = positionX; 
		float newY = positionY;
		
		
		switch (dir.get_rel_Orientation()) {
		case 'F' :
			newY += porte;
			break;
		case 'B' : 
			newY -= porte;
			break;
		case 'L' : 
			newX -= porte;
			break; 
		case 'R' : 
			newX += porte;
			break;
		default :
			break;
		}
		
		return isEntityAt(newX, newY);
	}
	
	private boolean isEntityAt(float newX, float newY) {
	
		for (Entity e : m_model.get_entities()) {
			if (e.get_x() == newX && e.get_y() == newY) {
				return true;
			}
		}
		return false;
	}

	




}
