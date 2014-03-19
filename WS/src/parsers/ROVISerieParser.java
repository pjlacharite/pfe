package parsers;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ROVISerieParser {
    public String parse(String json){
        JSONObject jsonObject1;
        JSONObject jsonObject2;
        JSONArray jsonArray = null;
        try {
            jsonObject1 = (JSONObject) new JSONParser().parse(json);
            jsonObject2 = (JSONObject) jsonObject1.get("searchResponse");
            jsonArray = (JSONArray) jsonObject2.get("results");
        } catch (ParseException e) {
            System.out.println(e.getLocalizedMessage());
        } 
        return String.valueOf((Long)((JSONObject)jsonArray.get(0)).get("id"));
    }
}
