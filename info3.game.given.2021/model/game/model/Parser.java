package game.model;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import game.entity.Absolute_Orientation;
import game.entity.Base;
import game.entity.Bot;
import game.entity.EntityType;
import game.entity.HitBox;
import game.entity.Item;
import game.entity.Player;
import game.entity.Position;


public class Parser {
	
	JSONObject jo;
	
	//public ArrayList<Entity> entities = new ArrayList<Entity>();
	public Map<String, Map<String, Object>> entities = new HashMap<>();
	public Map<String, Object> sprites = new HashMap<>();
	
	public int timer; 
	public int viscosity;
	public int nb_bot_init;
	public int nb_player;
	public boolean coop;
	
	public Parser(String Filename) throws FileNotFoundException, IOException, ParseException{
		
		Object obj = new JSONParser().parse(new FileReader(Filename));
		this.jo = (JSONObject) obj;
		
		coop = (Boolean) jo.get("cooperative");
		timer = ((Number) jo.get("timer")).intValue();
		viscosity = ((Number) jo.get("viscosity")).intValue();
		nb_bot_init =((Number) jo.get("nb_bot_init")).intValue();
		nb_player =((Number) jo.get("nb_player")).intValue();
		
		
		for (Object key : jo.keySet()) {
            String keyStr = (String) key;
            Object keyValue = jo.get(keyStr);

            if (keyValue instanceof JSONObject) {
                Map<String, Object> entityProperties = new HashMap<>();
                JSONObject entityJson = (JSONObject) keyValue;

                for (Object entityKey : entityJson.keySet()) {
                    String entityKeyStr = (String) entityKey;
                    Object entityKeyValue = entityJson.get(entityKeyStr);
                    
                    String name = (String)jo.get("name");
                    
                    if ("position".equals(entityKeyStr) && entityKeyValue instanceof JSONArray) {
                        JSONArray positionArray = (JSONArray) entityKeyValue;
                        Position pos = new Position(((Number) positionArray.get(0)).floatValue(), ((Number) positionArray.get(1)).floatValue());
                        entityProperties.put(entityKeyStr, pos);
                    } else if ("hitbox".equals(entityKeyStr) && entityKeyValue instanceof JSONArray) {
                        JSONArray hitBoxArray = (JSONArray) entityKeyValue;
                        HitBox pos = new HitBox(((Number) hitBoxArray.get(0)).floatValue(), ((Number) hitBoxArray.get(1)).floatValue());
                        entityProperties.put(entityKeyStr, pos);
                    } else if ("sprite".equals(entityKeyStr) && entityKeyValue instanceof JSONArray){
                    	sprites.put(convertString(name), entityKeyValue);
                    }else {
                    	entityProperties.put(entityKeyStr, entityKeyValue);
                    }
                }

                entities.put(keyStr, entityProperties);
            }
        }
		

	}

	private String convertString(String name) {
		
		switch (name) {
		case "Player1":
        case "Player2":
            return EntityType.PLAYER;
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
        	return "";
		}
	}
}


