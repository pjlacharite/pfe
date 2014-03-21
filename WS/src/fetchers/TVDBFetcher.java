package fetchers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Episode;
import model.ModelManager;
import model.Season;
import model.Serie;
import parsers.TVDBEpisodeParser;
import parsers.TVDBSeasonParser;
import parsers.TVDBSerieParser;
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
    
    public TVDBFetcher(){
        init();
    }

    private void init(){
        serieNames = new ArrayList<String>();
        Properties properties = new PropertiesReader().getProperties(SERIES_FILE);
        String[] seriesList = properties.getProperty(SERIES_KEY).split(";");
        for (String serieName: seriesList){
            serieNames.add(serieName);
        }
        properties = new PropertiesReader().getProperties(CONFIG_FILE);
        apiKey = properties.getProperty(TVDB_API_KEY);
        language = properties.getProperty(LANGUAGE);
    }

    private Serie fetchSerie(String serieName){
        URL url;
        HttpURLConnection connection = null;
        Serie serie = null;
        try {
            //Create connection
            url = new URL(getSourceUrl() + "GetSeries.php?seriesname=" + URLEncoder.encode(serieName, "UTF-8"));
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
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
            serie = new TVDBSerieParser().parse(response.toString());

        } catch (Exception e) {
          e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect(); 
            }
        }
        return serie;
    }

    private List<Episode> fetchEpisodes(Serie serie){
        URL url;
        HttpURLConnection connection = null;
        List<Episode> episodes = new ArrayList<Episode>();
        try {
            //Create connection
            url = new URL(getSourceUrl() + apiKey + "/series/" + serie.getId() + "/all/" + language + ".xml");
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));
            String line;
            StringBuffer response = new StringBuffer(); 
            while((line = rd.readLine()) != null) {
                response.append(line);
                response.append('\r');
            }
            rd.close();
            episodes = new TVDBEpisodeParser().parse(response.toString());
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return episodes;
    }

    @Override
    public void fetch() {
        System.out.println("Fetching from TVDB");
        ModelManager modelManager = ModelManager.getInstance();
        List<Serie> series = new ArrayList<Serie>();
        for (String serieName : serieNames){
            Serie serie = fetchSerie(serieName);
            List<Episode> episodes = fetchEpisodes(serie);
            List<Season> seasons = new TVDBSeasonParser().parse(episodes);
            serie.setSeasonCount(seasons.size());
            series.add(serie);
            for (Season season: seasons){
                season.setSerieId(serie.getId());
                season.setId(season.getSerieId() + "-" + season.getSeasonNumber());
            }
            for (Episode episode: episodes){
                episode.setSerieId(serie.getId());
            }
            modelManager.addEpisodes(episodes);
            modelManager.addSeasons(seasons);
        }
        ModelManager.getInstance().addSeries(series);
    }

    @Override
    public String getSourceUrl() {
        return "http://thetvdb.com/api/";
    }

}
