package com.qa.tests;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;
import org.apache.hc.client5.http.ClientProtocolException;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class GetAPITest extends TestBase {

    TestBase testbase;
    String serviceUrl;
    String apiUrl;
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setUp() throws ClientProtocolException, IOException {
        testbase = new TestBase();
        serviceUrl = prop.getProperty("URL");
        apiUrl = prop.getProperty("serviceURL");

        url = serviceUrl + apiUrl;

    }


    @Test
    public void getAPITest() throws ClientProtocolException, IOException, ParseException {
        restClient = new RestClient();
        closeableHttpResponse =restClient.get(url);


        // 1. STATUS CODE
        int statusCode = closeableHttpResponse.getCode();
        System.out.println("Status Code : "+ statusCode);
        Assert.assertEquals(statusCode,RESPONSE_STATUS_CODE_200 , "Status code is not 200");

            // 2. JSON STRING
        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), StandardCharsets.UTF_8);
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("Response JSON from API -----> " + responseJson);

        //Single Attribute of RestAPI
        //_per_page
        String perPageValue = TestUtil.getValueByJPath(responseJson, "/per_page");
        System.out.println("Value of per_page --> " +perPageValue);
        Assert.assertEquals(Integer.parseInt(perPageValue),6);

        //_total
        String totalValue = TestUtil.getValueByJPath(responseJson, "/total");
        System.out.println("Value of total --> " +totalValue);
        Assert.assertEquals(Integer.parseInt(totalValue),12);

        

            // 3. HEADER
        Header[] headerArray = closeableHttpResponse.getHeaders();
        HashMap<String, String> allHeaders = new HashMap<String, String>();

        for(Header header: headerArray){
            allHeaders.put(header.getName(),header.getValue());
        }
        System.out.println("Headers Array --> " + allHeaders);
    }
}
