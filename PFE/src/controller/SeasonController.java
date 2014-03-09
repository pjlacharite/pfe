package controller;

import java.util.ArrayList;
import java.util.List;

import model.Season;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import utils.WebServiceConnector;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Controller to retrieve information related to series
 * @author pjlacharite
 *
 */
public class SeasonController implements Controller{

    private Season lastFetchedSeason;
    /**
     * Fetches all seasons for a serieId
     * @param serieId
     * @return
     */
    public Season fetchSeason(String serieId, String seasonNumber){
        lastFetchedSeason = null;
        new FetchSeason().execute(serieId, seasonNumber);
        while(lastFetchedSeason == null){
            //Waiting for WS call to finish;
        }
        return lastFetchedSeason;
    }

    private class FetchSeason extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {
            List<String> paramNames = new ArrayList<String>();
            List<String> paramValues = new ArrayList<String>();
            paramNames.add("serieId");
            paramValues.add(params[0]);
            paramNames.add("seasonNumber");
            paramValues.add(params[1]);
            String wsParam = new WebServiceConnector().invoke("seasonservice", "text/xml", paramNames, paramValues);
            lastFetchedSeason = readSeason(wsParam);
            return null;
        }

        private Season readSeason(String wsParam){
            Log.d("WS", wsParam);
            Serializer serializer = new Persister();
            Season season = null;
            try {
                season = serializer.read(Season.class, wsParam);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return season;
        }

    }
}
