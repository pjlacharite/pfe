package utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;


public class WebServiceConnector{

    private static final String WS_URL = "http://192.168.0.103/WS/rest/";

    /**
     * Invokes the hello test WS
     * @param URI
     * @param mediaType
     * @return
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public String hello() {
        HttpParams httpParameters = new BasicHttpParams();
        int timeoutConnection = 3000;
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        int timeoutSocket = 5000;
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
        HttpClient httpClient = new DefaultHttpClient(httpParameters);
        HttpGet httpGet = new HttpGet(WS_URL + "hello");
        httpGet.addHeader("accept", "application/xml");
        HttpResponse response = null;
        String xml = "";
        try {
            response = httpClient.execute(httpGet);
            httpClient.getConnectionManager().shutdown();
            xml = EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return xml;
    }
}
