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
    
    /**
     * Updates the series list and returns it
     * @return
     */
    public List<Serie> fetchAllSeries(){
        lastFetchedSerieList = null;
        new webServiceTask().execute();
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
    public Serie getSerie(String id){
        if (lastFetchedSerieList == null){
            lastFetchedSerieList = fetchAllSeries();
        }
        for(Serie serie : lastFetchedSerieList){
            if (serie.getId().equals(id)){
                return serie;
            }
        }
        return null;
    }

    private class webServiceTask extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {
            String wsParam = new WebServiceConnector().invoke("serieslistservice", "text/xml");
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
}
