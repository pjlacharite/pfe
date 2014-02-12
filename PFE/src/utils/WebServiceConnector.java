package utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import android.os.AsyncTask;


public class WebServiceConnector extends AsyncTask<String, Void, String> {

    private static final String WS_URL = "http://localhost:8080/WS/rest/";

    /**
     * Invokes the hello test WS
     * @param URI
     * @param mediaType
     * @return
     * @throws IOException 
     * @throws ClientProtocolException 
     */
    public String hello() {
        HttpClient httpClient = new DefaultHttpClient();
        HttpContext localContext = new BasicHttpContext();
        HttpGet httpGet = new HttpGet(WS_URL + "hello");
        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet, localContext);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response.getEntity().toString();
    }

    @Override
    protected String doInBackground(String... params) {
        return hello();
    }
    
    @Override
    protected void onPostExecute(String result){
    }
}
