package com.qa.client;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    // GET Method without header
    public CloseableHttpResponse get(String url) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url); //http request
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet); // hit the Get URL
        return closeableHttpResponse;
    }

        // GET Method with header

        public CloseableHttpResponse get(String url, HashMap<String, String> headerMap) throws IOException{
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url); //http request

            //add Header in the http Request
            for(Map.Entry<String, String> entry : headerMap.entrySet()){
                httpGet.addHeader(entry.getKey(),entry.getValue());
            }
            CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet); // hit the Get URL
            return closeableHttpResponse ;

    }
}
