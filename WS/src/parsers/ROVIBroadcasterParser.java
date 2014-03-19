package parsers;

import java.util.ArrayList;
import java.util.List;

import model.Broadcaster;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ROVIBroadcasterParser {
    public List<Broadcaster> parse(String json){
        List<Broadcaster> broadcasters = new ArrayList<Broadcaster>();
        JSONObject jsonObject1;
        JSONObject jsonObject2;
        JSONArray jsonArray;
        try{
            jsonObject1 = (JSONObject) new JSONParser().parse(json);
            jsonObject2 = (JSONObject) jsonObject1.get("ServicesResult");
            jsonObject1 = (JSONObject) jsonObject2.get("Services");
            jsonArray = (JSONArray) jsonObject1.get("Service");
            for (int i = 0; i < jsonArray.size(); i++){
                Broadcaster broadcaster = new Broadcaster();
                broadcaster.setId(((JSONObject)jsonArray.get(i)).get("ServiceId").toString());
                broadcaster.setName(((JSONObject)jsonArray.get(i)).get("MSO").toString());
                broadcasters.add(broadcaster);
            }
        } catch (org.json.simple.parser.ParseException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return broadcasters;
    }
}
