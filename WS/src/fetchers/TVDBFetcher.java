package fetchers;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class TVDBFetcher implements Fetcher{

    public TVDBFetcher(){
    }


    @Override
    public void fetch() {
        URL url;
        HttpURLConnection connection = null;  
        try {
          //Create connection
          url = new URL(getSourceUrl() + "GetSeries.php?seriesname=How%20I%20Met%20Your%20Mother");
          connection = (HttpURLConnection)url.openConnection();
          connection.setRequestMethod("GET");
          connection.setRequestProperty("Content-Type", 
               "text/xml");
          connection.setRequestProperty("Content-Language", "en-US");
          connection.setUseCaches (false);
          connection.setDoInput(true);
          connection.setDoOutput(true);
          //Send request
          DataOutputStream wr = new DataOutputStream (
                      connection.getOutputStream ());
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
        return "http://thetvdb.com/api/";
    }

}
