package controller;

import java.util.ArrayList;
import java.util.List;

import model.Broadcaster;
import model.BroadcasterList;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import utils.WebServiceConnector;
import android.os.AsyncTask;
import android.util.Log;

public class BroadcasterController implements Controller{

    private List<Broadcaster> broadcasters;

    public List<Broadcaster> fetchBroadcasters(){
        broadcasters = null;
        new FetchBroadcaster().execute();
        while (broadcasters == null){
          //Waiting for WS call to finish;
        }
        return broadcasters;
    }

    private class FetchBroadcaster extends AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String... params) {
            String wsParam = new WebServiceConnector().invoke("broadcasterservice", "text/xml", null, null);
            broadcasters = readBroadcasters(wsParam);
            return null;
        }

        private List<Broadcaster> readBroadcasters(String wsParam){
            Log.d("WS", wsParam);
            Serializer serializer = new Persister();
            List<Broadcaster> broadcasters = new ArrayList<Broadcaster>();
            try {
                BroadcasterList broadcasterList = serializer.read(BroadcasterList.class, wsParam);
                for (Broadcaster broadcaster : broadcasterList.getBroadcasters()){
                    broadcasters.add(broadcaster);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return broadcasters;
        }

    }
}
