package parsers;

import java.util.ArrayList;
import java.util.List;

import model.ScheduleSlot;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ROVIScheduleParser {
    public List<ScheduleSlot> parse(String json){
        List<ScheduleSlot> scheduleSlots = new ArrayList<ScheduleSlot>();
        JSONObject jsonObject1;
        JSONObject jsonObject2;
        JSONArray jsonArray;
        try{
            jsonObject1 = (JSONObject) new JSONParser().parse(json);
            jsonObject2 = (JSONObject) jsonObject1.get("ProgramDetailsResult");
            jsonObject1 = (JSONObject) jsonObject2.get("Schedule");
            jsonArray = (JSONArray) jsonObject1.get("Airings");
            for (int i = 0; i < jsonArray.size(); i++){
                ScheduleSlot scheduleSlot = new ScheduleSlot();
                scheduleSlot.setAiringTime(((JSONObject)jsonArray.get(i)).get("AiringTime").toString());
                scheduleSlot.setBroadcasterId(((JSONObject)jsonArray.get(i)).get("ServiceId").toString());
                scheduleSlot.setSerieId(((JSONObject)jsonArray.get(i)).get("SeriesId").toString());
                scheduleSlot.setDuration(((JSONObject)jsonArray.get(i)).get("Duration").toString());
                scheduleSlot.setTitle(((JSONObject)jsonArray.get(i)).get("Title").toString());
                scheduleSlot.setEpisodeTitle(((JSONObject)jsonArray.get(i)).get("EpisodeTitle").toString());
                scheduleSlot.setSource(((JSONObject)jsonArray.get(i)).get("SourceDisplayName").toString());
                System.out.println(scheduleSlot.getAiringTime());
                System.out.println(scheduleSlot.getBroadcasterId());
                System.out.println(scheduleSlot.getDuration());
                System.out.println(scheduleSlot.getEpisodeTitle());
                System.out.println(scheduleSlot.getSerieId());
                System.out.println(scheduleSlot.getSource());
                System.out.println(scheduleSlot.getTitle());
                scheduleSlots.add(scheduleSlot);
            }
        } catch (org.json.simple.parser.ParseException e) {
            System.out.println(e.getLocalizedMessage());
        }
        return scheduleSlots;
    }
}
