package main;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import model.Schedule;
import model.ScheduleSlot;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import persistence.ScheduleSlotDAO;

@Path("/scheduleservice")
public class ScheduleService {
    // This method is called if XML is request
    private static final int MAX_SCHEDULE_SLOTS = 10;
    @GET
    @Produces(MediaType.TEXT_XML)
    public String getScheduleXml(@QueryParam("serieId") String serieId, @QueryParam("broadcasterId") String broadcasterId) {
        Serializer serializer = new Persister();
        StringWriter writer = new StringWriter();
        try {
          List<String> parameters = new ArrayList<String>();
          parameters.add(serieId);
          parameters.add(broadcasterId);
          ScheduleSlotDAO scheduleSlotDAO = new ScheduleSlotDAO();
          List<ScheduleSlot> scheduleSlots = scheduleSlotDAO.findAll(parameters);
          if (scheduleSlots.size() > 10){
              scheduleSlots = scheduleSlots.subList(0, MAX_SCHEDULE_SLOTS);
          }
          scheduleSlotDAO.releaseConnection();
          Schedule schedule = new Schedule();
          schedule.addScheduleSlots(scheduleSlots);
          serializer.write(schedule, writer);
          System.out.println(writer.getBuffer());
        } catch (Exception e) {
          e.printStackTrace();
        }
        return "<?xml version=\"1.0\"?>" + writer.getBuffer();
    }
}
