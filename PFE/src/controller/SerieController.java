package controller;

import java.util.ArrayList;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import model.Serie;
import model.SerieList;
import utils.WebServiceConnector;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Controller to retrieve information related to series
 * @author pjlacharite
 *
 */
public class SerieController implements Controller {

    private List<Serie> lastFetchedSerieList;
    private Serie lastFetchedSerie;

    /**
     * Updates the series list and returns it
     * @return
     */
    public List<Serie> fetchSeriesName(){
        lastFetchedSerieList = null;
        new FetchSeriesName().execute();
        while(lastFetchedSerieList == null){
            //Waiting for WS call to finish
        }
        return this.lastFetchedSerieList;
    }

    /**
     * Getter for a serie using its name
     * @param name
     * @return
     */
    public Serie fetchSerie(String id){
        lastFetchedSerie = null;
        Log.d("WTF", id);
        new FetchSerie().execute(id);
        while(lastFetchedSerie == null){
          //Waiting for WS call to finish
        }
        return lastFetchedSerie;
    }

    private class FetchSeriesName extends AsyncTask<String, Void, Void>{

        
        @Override
        protected Void doInBackground(String... params) {
            String wsParam = new WebServiceConnector().invoke("serieslistservice", "text/xml", null, null);
            lastFetchedSerieList = readSerieList(wsParam);
            return null;
        }

        private List<Serie> readSerieList(String wsParam){
            Log.d("WS", wsParam);
            List<Serie> series = new ArrayList<Serie>();
            Serializer serializer = new Persister();
            SerieList serieList = null;
            try {
                serieList = serializer.read(SerieList.class, wsParam);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (serieList != null){
                int i = 0;
                for (String serieName : serieList.getSeriesName()){
                    series.add(new Serie(String.valueOf(i), serieName));
                    i++;
                }
                //seriesList.addAll(carrier.getSeries());
            }
            return series;
        }
    }

    private class FetchSerie extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {
            List<String> paramNames = new ArrayList<String>();
            List<String> paramValues = new ArrayList<String>();
            paramNames.add("serieId");
            paramValues.add(params[0]);
            String wsParam = new WebServiceConnector().invoke("serieservice", "text/xml", paramNames, paramValues);
            lastFetchedSerie = readSerie(wsParam);
            return null;
        }

        private Serie readSerie(String wsParam){
            Log.d("WS", wsParam);
            Serializer serializer = new Persister();
            Serie serie = null;
            try {
                 serie = serializer.read(Serie.class, wsParam);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return serie;
        }

    }
}
