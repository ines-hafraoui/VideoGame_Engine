package game.map.Biomes;

import game.map.Biome;
import game.map.LandType;
import game.map.Polygon;
import game.map.LandTypes.Water;

public class Ocean extends Biome {
	public Ocean(Polygon borders, LandType lt) {
		super (borders, lt);
		
		addLandType(new Water());
	}
}
