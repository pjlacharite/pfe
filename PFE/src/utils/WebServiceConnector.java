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

    private static final String WS_URL = "http://10.0.2.2:8080/WS/rest/";

    /**
     * Invokes the hello test WS
     * @param URI
     * @param mediaType
     * @return
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public String invoke(String service, String mimeType) {
        HttpParams httpParameters = new BasicHttpParams();
        int timeoutConnection = 3000;
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        int timeoutSocket = 5000;
        HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
        HttpClient httpClient = new DefaultHttpClient(httpParameters);
        HttpGet httpGet = new HttpGet(WS_URL + service);
        httpGet.addHeader("accept", mimeType);
        HttpResponse response = null;
        String mime = "";
        try {
            response = httpClient.execute(httpGet);
            httpClient.getConnectionManager().shutdown();
            mime = EntityUtils.toString(response.getEntity());
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mime;
    }
}
