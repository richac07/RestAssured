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

public class RestClient {

    //1. GET Method
    public void get(String url){
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url); //http request
            CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpGet); // hit the Get URL

            // 1. STATUS CODE
            int statusCode = closeableHttpResponse.getCode();
            System.out.println("Status Code : "+ statusCode);

            // 2. JSON STRING
            //String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
            String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), StandardCharsets.UTF_8);
            JSONObject responseJson = new JSONObject(responseString);
            System.out.println("Response JSON from API -----> " + responseJson);


            // 3. HEADER
            Header[] headerArray = closeableHttpResponse.getHeaders();
            HashMap<String, String> allHeaders = new HashMap<String, String>();

            for(Header header: headerArray){
                allHeaders.put(header.getName(),header.getValue());
            }
            System.out.println("Headers Array --> " + allHeaders);


        }
        catch (IOException e){
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
