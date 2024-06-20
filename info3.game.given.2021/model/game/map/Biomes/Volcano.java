package game.map.Biomes;

import game.map.Biome;
import game.map.LandType;
import game.map.Polygon;
import game.map.LandTypes.Lava;

public class Volcano extends Biome{

	public Volcano(Polygon borders, LandType lt) {
		super (borders, lt);
		
		addLandType(new Lava());
	}
	
}
