package controller;

import java.util.ArrayList;
import java.util.List;

import utils.WebServiceConnector;
import mock.SeriesMock;
import model.Serie;
import android.os.AsyncTask;

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
            String wsParam = new WebServiceConnector().invoke("hello", "text/xml");
            lastFetchedSerieList = readSerieList(wsParam);
            return null;
        }

        private List<Serie> readSerieList(String wsParam){
            List<Serie> seriesList = new ArrayList<Serie>();
            seriesList.addAll(SeriesMock.mockSeries());
            seriesList.add(new Serie("4", wsParam, wsParam));
            return seriesList;
        }

    }
}
