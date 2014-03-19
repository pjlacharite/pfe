package fetchers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import model.Broadcaster;
import parsers.ROVIBroadcasterParser;
import parsers.ROVIScheduleParser;
import parsers.ROVISerieParser;
import utils.PropertiesReader;

public class ROVIFetcher implements Fetcher {

    private static final String ROVI_API_LISTING_KEY = "ROVIListingKey";
    private static final String ROVI_API_SEARCH_KEY = "ROVISearchKey";
    private static final String ROVI_API_SECRET = "ROVISecret";
    private static final String SERIES_FILE = "series.properties";
    private static final String SERIES_KEY = "series";
    private static final String CONFIG_FILE = "configurations.properties";
    private List<String> serieNames = null;
    private String apiListingKey = null;
    private String apiSearchKey = null;
    private String apiSecret = null;

    public ROVIFetcher(){
        init();
    }

    public void init(){
        serieNames = new ArrayList<String>();
        Properties properties = new PropertiesReader().getProperties(SERIES_FILE);
        String[] seriesList = properties.getProperty(SERIES_KEY).split(";");
        for (String serieName: seriesList){
            serieNames.add(serieName);
        }
        properties = new PropertiesReader().getProperties(CONFIG_FILE);
        apiListingKey = properties.getProperty(ROVI_API_LISTING_KEY);
        apiSearchKey = properties.getProperty(ROVI_API_SEARCH_KEY);
        apiSecret = properties.getProperty(ROVI_API_SECRET);
    }

    @Override
    public void fetch() {
        List<Broadcaster> broadcasters = fetchBroadcasters();
        String serieId = null;
        for (String serieName: serieNames){
           serieId = fetchShowId(serieName);
       }
       fetchSchedule(broadcasters.get(0).getId(), serieId);
    }

    private String fetchShowId(String serieName){
        URL url;
        HttpURLConnection connection = null;
        String temp = null;
        try{
            url = new URL(getSourceUrl() + "search/v2.1/video/search?apikey=" + apiSearchKey + "&sig=" + getSig() + "&query=" + URLEncoder.encode(serieName, "UTF-8") + "&entitytype=tvseries&size=1");
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
            temp = new ROVISerieParser().parse(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return temp;
    }

    private List<Broadcaster> fetchBroadcasters(){
        URL url;
        HttpURLConnection connection = null;
        List<Broadcaster> broadcasters = new ArrayList<Broadcaster>();
        try {
            url = new URL(getSourceUrl() + "TVlistings/v9/listings/services/postalcode/H3T+1B2/info?locale=en-CA&countrycode=CA&format=json&apikey=" + apiListingKey);
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
            broadcasters = new ROVIBroadcasterParser().parse(response.toString());
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return broadcasters;
    }

    private void fetchSchedule(String broadcasterId, String serieId){
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(getSourceUrl() + "TVlistings/v9/listings/programdetails/" + broadcasterId + "/" + serieId + "/info?locale=en-CA&countrycode=CA&include=Program&duration=20160&apikey=" + apiListingKey);
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
            System.out.println(new ROVIScheduleParser().parse(response.toString()));
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
    }
    @Override
    public String getSourceUrl() {
        return "http://api.rovicorp.com/";
    }

    /**
     * 
     * @return
     * @throws NoSuchAlgorithmException
     */
    private String getSig() throws NoSuchAlgorithmException {
        String result = null;
        Date _date = new Date();
        long _secs = 0;
        _secs = _date.getTime() / 1000;
        String _sig = apiSearchKey + apiSecret + _secs;
        byte[] hash = MessageDigest.getInstance("MD5").digest(_sig.getBytes());
        BigInteger bi = new BigInteger(1, hash);
        result = bi.toString(16);
        if (result.length() % 2 != 0) {
            result = "0" + result;
        }
        return result;
    }
}
