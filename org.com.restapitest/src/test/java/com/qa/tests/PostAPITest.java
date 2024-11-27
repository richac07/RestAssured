package com.qa.tests;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.xml.stream.events.DTD;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class PostAPITest extends TestBase {
    TestBase testBase;
    String serviceUrl;
    String apiUrl;
    String url;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setUp() {
        testBase = new TestBase();
        serviceUrl = prop.getProperty("URL");
        apiUrl = prop.getProperty("serviceURL");

        //Post Call - Attribute 1
        url = serviceUrl + apiUrl;
    }

    @Test
    public void postAPITest() throws JsonGenerationException, JsonMappingException, IOException, ParseException {
        //Create an Object of the restClient . It is a class created by us with httpClient
        restClient = new RestClient();

        //Post Call - Attribute 2
        HashMap<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");

        //jackson API - this is for Automation of RestAPI
        ObjectMapper objectMapper = new ObjectMapper();

        //Creating an object using the class created by us
        Users users = new Users("John", "Leader");

        //Object to Json File -
        // Adding the data from the object to a file For our reference.
        objectMapper.writeValue(new File("/Users/I331396/Documents/RestAPI/org.com.restapitest/src/main/java/com/qa/data/Users.json"), users);

        //object to JSON in string - Marshalling.- Adding the object data as String for the post call
        //Post Call - Attribute 3
        String userJsonString = objectMapper.writeValueAsString(users);
        System.out.println(userJsonString);

        //Running the post call using the restClient and storing the response
        closeableHttpResponse = restClient.post(url, userJsonString, headerMap);

        //Fetching from response - Status Code
        int statusCode = closeableHttpResponse.getCode();
        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201, "Sorry Nothing POSTED");

        //Fetching from response - JSON String

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), StandardCharsets.UTF_8);

        JSONObject responseJSON = new JSONObject(responseString);
        System.out.println("JSON RESPONSE is -->" + responseJSON);

        //JSON to java object - UnMarshalling
        Users obj = objectMapper.readValue(responseString, Users.class); //expected User value
        System.out.println(obj);

        Assert.assertTrue(users.getName().equals(obj.getName()));
        Assert.assertTrue(users.getJob().equals(obj.getJob()));
        System.out.println("ID for the new User is --> " + obj.getId());
        System.out.println("ID for the new User created at --> " + obj.getCreatedAt());


    }

}
