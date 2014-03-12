package fetchers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Serie;
import parsers.TVDBSerieHeaderParser;
import utils.PropertiesReader;

public class TVDBFetcher implements Fetcher{

    private static final String TVDB_API_KEY = "TVDBKey";
    private static final String CONFIG_FILE = "configurations.properties";
    private static final String SERIES_FILE = "series.properties";
    private static final String SERIES_KEY = "series";
    private static final String LANGUAGE = "language";
    private List<String> serieNames = null;
    private String apiKey = null;
    private String language = null;
    private Serie currentSerie = null;
    public TVDBFetcher(){
        init();
    }

    private void init(){
        serieNames = new ArrayList<String>();
        Properties properties = new PropertiesReader().getProperties(SERIES_FILE);
        String[] seriesList = properties.getProperty(SERIES_KEY).split(";");
        for (String serieName: seriesList){
            serieNames.add(serieName);
            System.out.println(serieName);
        }
        properties = new PropertiesReader().getProperties(CONFIG_FILE);
        apiKey = properties.getProperty(TVDB_API_KEY);
        language = properties.getProperty(LANGUAGE);
        System.out.println(apiKey);
        System.out.println(language);
    }

    private void fetchGetSerie(String serieName){
        URL url;
        HttpURLConnection connection = null;  
        try {
            //Create connection
            url = new URL(getSourceUrl() + "GetSeries.php?seriesname=" + URLEncoder.encode(serieName, "UTF-8"));
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", 
               "text/xml");
            connection.setRequestProperty("Content-Language", "en-US");
            connection.setUseCaches (false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            //Send request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
            wr.flush ();
            wr.close ();
            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer(); 
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            System.out.println(response.toString());
            currentSerie = (Serie)new TVDBSerieHeaderParser().parse(response.toString());
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect(); 
            }
        }
    }

    private void fetchSerie(){
        URL url;
        HttpURLConnection connection = null;  
        try {
            //Create connection
            url = new URL(getSourceUrl() + apiKey + "/series/" + currentSerie.getId() + "/all/" + language + ".xml");
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            //connection.setRequestProperty("Content-Type", 
            //        "text/xml");
            //connection.setRequestProperty("Content-Language", "en-US");
            //connection.setUseCaches (false);
            //connection.setDoInput(true);
            //connection.setDoOutput(true);
            //Send request
            //DataOutputStream wr = new DataOutputStream(connection.getOutputStream ());
            //wr.flush ();
            //wr.close ();
            //Get Response
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer(); 
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            System.out.println(response.toString());
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
    }

    @Override
    public void fetch() {
        for (String serieName : serieNames){
            fetchGetSerie(serieName);
            fetchSerie();
        }
    }


    @Override
    public String getSourceUrl() {
        return "http://thetvdb.com/api/";
    }

}
