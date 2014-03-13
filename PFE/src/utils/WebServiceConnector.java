package utils;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.util.Log;


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
    public String invoke(String service, String mimeType, List<String> parametersName, List<String> parametersValue) {
        HttpParams httpParameters = new BasicHttpParams();
        int timeoutConnection = 5000;
        HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
        HttpClient httpClient = new DefaultHttpClient(httpParameters);
        String serviceCall = WS_URL + service;
        if (parametersValue != null){
            for (int i = 0; i < parametersName.size(); i++){
                if (i == 0){
                    serviceCall += "?";
                }else{
                    serviceCall += "&";
                }
                serviceCall += parametersName.get(i) + "=" + parametersValue.get(i);
            }
        }
        Log.d("WS", serviceCall);
        HttpGet httpGet = new HttpGet(serviceCall);

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
