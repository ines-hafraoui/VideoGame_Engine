package game.model;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import game.entity.Absolute_Orientation;
import game.entity.Base;
import game.entity.Bot;
import game.entity.Entity;
import game.entity.Item;
import game.entity.Player;
import game.entity.Position;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Parser {
	
	JSONObject jo;
	
	//public ArrayList<Entity> entities = new ArrayList<Entity>();
	public Map<String, Map<String, Object>> entities = new HashMap<>();
	
	public int timer; 
	public int viscosity;
	public int nb_bot_init;
	public boolean coop;
	
	public Parser(String Filename) throws FileNotFoundException, IOException, ParseException{
		
		Object obj = new JSONParser().parse(new FileReader(Filename));
		this.jo = (JSONObject) obj;
		
		coop = (Boolean) jo.get("cooperative");
		timer = ((Number) jo.get("timer")).intValue();
		viscosity = ((Number) jo.get("viscosity")).intValue();
		nb_bot_init =((Number) jo.get("nb_bot_init")).intValue();
		
		
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
                        Position pos = new Position(((Number) positionArray.get(0)).floatValue(), ((Number) positionArray.get(1)).floatValue());
                        entityProperties.put(entityKeyStr, pos);
                    } else {
                        entityProperties.put(entityKeyStr, entityKeyValue);
                    }
                }

                entities.put(keyStr, entityProperties);
            }
        }
		
		// find all entities on the file
//		Set<String> keys = jo.keySet();
//		String[] keysArray = keys.toArray(new String[keys.size()]);
//
//		for (int i = 0; i < keysArray.length; i++) {
//			String key = keysArray[i];
//			
//			JSONObject keyDetails = (JSONObject) jo.get(key);
//			String name = (String) keyDetails.get("name");
//			Number view = (Number)keyDetails.get("view");
//			String behaviors = (String) keyDetails.get("behaviour");
//			String features = (String) keyDetails.get("features");
//			String sprite = (String) keyDetails.get("sprite");
//			Boolean pickable = (Boolean) keyDetails.get("pickable");
//			String orientation = (String) keyDetails.get("direction");
//			JSONArray position = (JSONArray) keyDetails.get("position");
//			Number team = (Number) keyDetails.get("team");
//			
//			Position pos = new Position((float)position.get(0),(float)position.get(1));
//			
//			switch(name) {
//			case "BOT1":
//			case "BOT2":
//				for(int j=0; j<nb_bot_init;j++) {
//					entities.add(new Bot(pos, new Absolute_Orientation(orientation),team.intValue(),nb_bot_init, view.intValue(),pickable));
//				}
//				break;
//			case "BASE1":
//			case "BASE2":
//				entities.add(new Base(pos, new Absolute_Orientation(orientation),team.intValue(),nb_bot_init, view.intValue(),pickable));
//				break;
//			case "PLAYER1":
//			case "PLAYER2":
//				entities.add(new Player(pos, new Absolute_Orientation(orientation),team.intValue(),nb_bot_init, view.intValue(),pickable));
//				break;
//			default : 
//				entities.add(new Item(pos, new Absolute_Orientation(orientation),team.intValue(),nb_bot_init, view.intValue(),pickable));
//				break;
//			}	
//		}	
	}
}


