package controller;

import java.util.ArrayList;
import java.util.List;

import model.Schedule;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import utils.WebServiceConnector;
import android.os.AsyncTask;
import android.util.Log;

public class ScheduleController implements Controller{
    private Schedule schedule;
    
    public Schedule fetchSchedule(String serieId, String broadcasterId){
        schedule = null;
        new FetchSchedule().execute(serieId, broadcasterId);
        while (schedule == null){
          //Waiting for WS call to finish;
        }
        return schedule;
    }

    private class FetchSchedule extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {
            List<String> parameters = new ArrayList<String>();
            List<String> parameterNames = new ArrayList<String>();
            parameterNames.add("serieId");
            parameterNames.add("broadcasterId");
            parameters.add(params[0]);
            parameters.add(params[1]);
            String wsParam = new WebServiceConnector().invoke("scheduleservice", "text/xml", parameterNames, parameters);
            schedule = readSchedule(wsParam);
            return null;
        }

        private Schedule readSchedule(String wsParam){
            Log.d("WS", wsParam);
            Serializer serializer = new Persister();
            Schedule schedule = null;
            try {
                schedule = serializer.read(Schedule.class, wsParam);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return schedule;
        }

    }
}
