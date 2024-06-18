import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import game.entity.Absolute_Orientation;
import game.entity.Base;
import game.entity.Bot;
import game.entity.Item;
import game.entity.Player;
import game.entity.Position;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class Parser {
	
	JSONObject jo;

	List<String> bots_string = new ArrayList<String>();
	List<String> players_string = new ArrayList<String>();
	List<String> item_string = new ArrayList<String>();
	List<String> base_string = new ArrayList<String>();
	
	ArrayList<Player> players = new ArrayList<Player>();
	ArrayList<Bot> bots = new ArrayList<Bot>();
	ArrayList<Base> bases = new ArrayList<Base>();
	ArrayList<Item> items = new ArrayList<Item>();
	int timer; 
	int viscosity;
	
	public Parser(String Filename) throws FileNotFoundException, IOException, ParseException{
		
		Object obj = new JSONParser().parse(new FileReader(Filename));
		this.jo = (JSONObject) obj;
		
		// find all entities on the file
		Set<String> keys = jo.keySet();
		String[] keysArray = keys.toArray(new String[keys.size()]);

		for (int i = 0; i < keysArray.length; i++) {
			String key = keysArray[i];
			JSONObject keyname = (JSONObject) jo.get(key);
			
			if (((String)keyname.get("name")).equals("PLAYER 1") || ((String)keyname.get("name")).equals("PLAYER 2")) {
				players_string.add(key);
			}else if(((String)keyname.get("name")).equals("BASE1") || ((String)keyname.get("name")).equals("BASE2") || 
					((String)keyname.get("name")).equals("BASE")) {
				base_string.add(key);
			}else if(((String)keyname.get("name")).equals("BOT1") || ((String)keyname.get("name")).equals("BOT2") ||
					((String)keyname.get("name")).equals("PARASITE")) {
				bots_string.add(key);
			}else {
				item_string.add(key);
			}
		}
		
		timer = getTimer();
		viscosity = getViscosity();
		getPlayer();
		getItem();
		getBot();
		getBase();
	}
	
	

	private int getTimer() {
		Number timer = (Number) jo.get("timer");
		return timer.intValue();	
	}
	
	private int getViscosity() {
		Number vis = (Number) jo.get("timer");
		return vis.intValue();
	}

	
	// rajouter un constructeur dans entity qui n'a pas de Model en arguemnt et rajouter un champ viw et un champ pickable
	private void getPlayer() {
		
		for (int i =0; i<players_string.size(); i++) {
			String player = players_string.get(i);
			JSONObject playerDetails = (JSONObject) jo.get(player);
			Number view = (Number)playerDetails.get("view");
			String behaviors = (String) playerDetails.get("behaviour");
			String features = (String) playerDetails.get("features");
			String sprite = (String) playerDetails.get("sprite");
			Boolean pickable = (Boolean) playerDetails.get("pickable");
			String orientation = (String) playerDetails.get("direction");
			JSONArray position = (JSONArray)playerDetails.get("position");
			Number team = (Number) playerDetails.get("team");
			
			Position pos = new Position((float)position.get(0),(float)position.get(1));
			players.add(new Player(pos, new Absolute_Orientation(orientation), "J",team.intValue(),5, view.intValue(), pickable));
		}
	}
	
	private void getBot() {
		
		for (int i =0; i<bots_string.size(); i++) {
			String bot = bots_string.get(i);
			JSONObject botDetails = (JSONObject) jo.get(bot);
			Number view = (Number) botDetails.get("view");
			String behaviors = (String) botDetails.get("behaviour");
			String features = (String) botDetails.get("features");
			String sprite = (String) botDetails.get("sprite");
			Boolean pickable = (Boolean) botDetails.get("pickable");
			String orientation = (String) botDetails.get("direction");
			JSONArray position = (JSONArray)botDetails.get("position");
			Number team = (Number) botDetails.get("team");
			
			Position pos = new Position((float)position.get(0),(float)position.get(1));
			bots.add(new Bot(pos, new Absolute_Orientation(orientation), "BO",team.intValue(),0, view.intValue(), pickable));
		}
		
	}

	private void getItem() {
		
		for (int i =0; i<players_string.size(); i++) {
			String item = item_string.get(i);
			JSONObject itemDetails = (JSONObject) jo.get(item);
			Number view = (Number) itemDetails.get("view");
			String behaviors = (String) itemDetails.get("behaviour");
			String features = (String) itemDetails.get("features");
			String sprite = (String) itemDetails.get("sprite");
			Boolean pickable = (Boolean) itemDetails.get("pickable");
			String orientation = (String) itemDetails.get("direction");
			JSONArray position = (JSONArray)itemDetails.get("position");
			Number team = (Number) itemDetails.get("team");
			
			Position pos = new Position((float)position.get(0),(float)position.get(1));
			items.add(new Item(pos, new Absolute_Orientation(orientation), "I",team.intValue(),0, view.intValue(), pickable));
		}
		
	}
	
	
	private void getBase() {
		
		for (int i =0; i<players_string.size(); i++) {
			String base = base_string.get(i);
			JSONObject baseDetails = (JSONObject) jo.get(base);
			Number view = (Number) baseDetails.get("view");
			String behaviors = (String) baseDetails.get("behaviour");
			String features = (String) baseDetails.get("features");
			String sprite = (String) baseDetails.get("sprite");
			Boolean pickable = (Boolean) baseDetails.get("pickable");
			String orientation = (String) baseDetails.get("direction");
			JSONArray position = (JSONArray) baseDetails.get("position");
			Number team = (Number) baseDetails.get("team");
			
			Position pos = new Position((float)position.get(0),(float)position.get(1));
			bases.add(new Base(pos, new Absolute_Orientation(orientation), "BA",team.intValue(),0, view.intValue(), pickable));
		}
		
	}

}
