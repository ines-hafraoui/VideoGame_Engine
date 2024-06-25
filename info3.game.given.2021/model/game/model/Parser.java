package game.model;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import game.entity.EntityType;
import game.entity.HitBox;
import game.entity.Position;
import info3.game.view.View;

public class Parser {

	JSONObject jo;

	// public ArrayList<Entity> entities = new ArrayList<Entity>();
	public Map<String, Map<String, Object>> entities = new HashMap<>();
	public String aut_projectile[];
	public String aut_bot[];

	public int timer;
	public int viscosity;
	public int nb_bot_init;
	public int nb_player;
	public int nb_item;
	public boolean coop;
	public BufferedImage[] bg_sprite;
	public boolean bg_rand;
	public int zoom;

	public Parser(String Filename) throws FileNotFoundException, IOException, ParseException {

		Object obj = new JSONParser().parse(new FileReader(Filename));
		this.jo = (JSONObject) obj;

		coop = (Boolean) jo.get("cooperative");
		timer = ((Number) jo.get("timer")).intValue();
		viscosity = ((Number) jo.get("viscosity")).intValue();
		nb_bot_init =((Number) jo.get("nb_bot_init")).intValue();
		nb_player =((Number) jo.get("nb_player")).intValue();
		nb_item =((Number) jo.get("nb_item")).intValue();
		
		
		JSONArray array = (JSONArray)jo.get("automates");
		if (array != null) {
			 int aut_size = (array).size();
				aut_bot = new String[aut_size];
				
				for (int i = 0; i<aut_size; i++) {
					aut_bot[i] = (String)array.get(i);
				}
		}
		for (Object key : jo.keySet()) {
			String keyStr = (String) key;
			Object keyValue = jo.get(keyStr);

			if (keyValue instanceof JSONObject) {
				Map<String, Object> entityProperties = new HashMap<>();
				JSONObject entityJson = (JSONObject) keyValue;

				for (Object entityKey : entityJson.keySet()) {
					String entityKeyStr = (String) entityKey;
					Object entityKeyValue = entityJson.get(entityKeyStr);

					if ("position".equals(entityKeyStr) && entityKeyValue instanceof JSONArray) {
						JSONArray positionArray = (JSONArray) entityKeyValue;
						Position pos = new Position(((Number) positionArray.get(0)).floatValue(),
								((Number) positionArray.get(1)).floatValue());
						entityProperties.put(entityKeyStr, pos);
					} else if ("hitbox".equals(entityKeyStr) && entityKeyValue instanceof JSONArray) {
						JSONArray hitBoxArray = (JSONArray) entityKeyValue;
						HitBox hb = new HitBox(((Number) hitBoxArray.get(0)).floatValue(),
								((Number) hitBoxArray.get(1)).floatValue());
						entityProperties.put(entityKeyStr, hb);
					} else {
						entityProperties.put(entityKeyStr, entityKeyValue);
					}
				}

				entities.put(keyStr, entityProperties);
			}
		}

//		The Configuration options for the view
		zoom =((Number) jo.get("zoom")).intValue();
		JSONArray bg_array = (JSONArray) jo.get("background");
		if( bg_array.size()>4) {
			throw new IllegalArgumentException("Too many argument to set the background");
		}
		int nr = ((Number) bg_array.get(1)).intValue();
		int nc = ((Number) bg_array.get(2)).intValue();
		bg_sprite = View.loadSprite((String) bg_array.get(0), nr, nc);
		bg_rand = (Boolean) bg_array.get(3);
	}

	// find the sprite corresponding to the name of the entity
	public JSONObject getSprite(String name) {
		Map<String, Object> properties = entities.get(name);
		if (properties != null) {
			return (JSONObject) properties.get("sprite");
		}
		return null;
	}

	private String convertString(String name) {

		switch (name) {
		case "Player1":
        case "Player2":
            return EntityType.PLAYER;
        case "Arrow": 
        case "FireBall":
        	return EntityType.PROJECTILE;
        case "Bot1":
        case "Bot2":
        case "Parasite":
        case "Dasher":
        case "Arsher":
            return EntityType.TEAMMATE;
        case "Base1":
        case "Base2":
        case "Base":
            return  EntityType.BASE;
        case "Power":
        case "Capacity":
        case "Plant" : 
            return EntityType.ITEM;
        default : 
        	return null;
		}
	}
}
