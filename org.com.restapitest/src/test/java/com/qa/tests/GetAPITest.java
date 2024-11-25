package com.qa.tests;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import org.apache.hc.client5.http.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class GetAPITest extends TestBase {

    TestBase testbase;
    String serviceUrl;
    String apiUrl;
    String url;
    RestClient restClient;

    @BeforeMethod
    public void setUp() throws ClientProtocolException, IOException {
        testbase = new TestBase();
        serviceUrl = prop.getProperty("URL");
        apiUrl = prop.getProperty("serviceURL");

        url = serviceUrl + apiUrl;

    }


    @Test
    public void getAPITest() throws ClientProtocolException, IOException {
        restClient = new RestClient();
        restClient.get(url);

    }
}
