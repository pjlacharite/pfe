package controller;

import java.util.ArrayList;
import java.util.List;

import model.Episode;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import utils.WebServiceConnector;
import android.os.AsyncTask;
import android.util.Log;

public class EpisodeController implements Controller{

    private Episode lastFetchedEpisode;
    public Episode fetchEpisode(String serieId, String seasonNumber, String episodeNumber){
        lastFetchedEpisode = null;
        new FetchEpisode().execute(serieId, seasonNumber, episodeNumber);
        while(lastFetchedEpisode == null){
            //Waiting for WS call to finish;
        }
        return lastFetchedEpisode;
    }

    private class FetchEpisode extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {
            List<String> paramNames = new ArrayList<String>();
            List<String> paramValues = new ArrayList<String>();
            paramNames.add("serieId");
            paramValues.add(params[0]);
            paramNames.add("seasonNumber");
            paramValues.add(params[1]);
            paramNames.add("episodeNumber");
            paramValues.add(params[2]);
            String wsParam = new WebServiceConnector().invoke("episodeservice", "text/xml", paramNames, paramValues);
            lastFetchedEpisode = readEpisode(wsParam);
            return null;
        }

        private Episode readEpisode(String wsParam){
            Log.d("WS", wsParam);
            Serializer serializer = new Persister();
            Episode episode= null;
            try {
                episode = serializer.read(Episode.class, wsParam);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return episode;
        }

    }
}
