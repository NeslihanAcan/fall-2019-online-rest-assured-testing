package com.automation.restapitests_murodil;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsParameters {

    @BeforeAll
    public static void setup(){
        baseURI= ConfigurationReader.getProperty("SPARTAN.URI");

    }
    @Test
    public void helloTest(){

        //request
      Response response= get("/hello").prettyPeek();

        //response
      //verify status code
        System.out.println("Status Code: "+response.statusCode());
      assertEquals(200,response.statusCode());
      //verify headers
      //verify content type
        System.out.println("Content Type: "+response.contentType());

        assertEquals("text/plain;charset=UTF-8",response.contentType());
        System.out.println(response.getHeader("Date"));
        assertTrue(response.headers().hasHeaderWithName("Date"));
        assertEquals("17",response.getHeader("Content-Length"));
        assertEquals("Hello from Sparta",response.body().asString());

    }

    /*
   Given Accept type is json
   And id parameter value is 5
   when user sends Get request to /api/spartans/{id}
   then response status code should be 200
   And response content-type: "application/json;charset=UTF-8"
   And "Blythe" should be in response payload
     */

    @Test
    public void getSpartanById_Positive_Path_param_test(){

        //request
        Response response=
         given().
             auth().basic("admin", "admin").
             accept(ContentType.JSON).
         and().
            pathParam("id",1).
         when().
            get("/spartans/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json;charset=UTF-8",response.contentType());
        assertTrue(response.body().asString().contains("Aidar"));


    }
    @Test
    public void getSpartanById_Negative_path_param_test(){

        Response response= given().
                                  auth().basic("admin", "admin").
                                  accept(ContentType.JSON).
                            and().
                                  pathParam("id",500).
                            when().get("/spartans/{id}");
        assertEquals(404,response.statusCode());
        assertEquals("application/json;charset=UTF-8",response.contentType());
        assertTrue(response.body().asString().contains("Spartan Not Found"));

    }

    /**
     *
     * Given accept type is Json
     * And guery parameters values are : Gender \Female, name contains \e
     *When user sends get request to api/spartan/search
     * Then response status code should be 200
     * And Response content-type:application/json;charset=UTF-8
     * And "Female" should be in response payload
     * And "Penny" should be in response payload
     *
     */
    @Test
    public void positiveTestWithQueryParams_search(){

        String gender="Female";

      Response response=   given().
                                 auth().basic("admin", "admin").
                                accept(ContentType.JSON).
                            and().
                                queryParams("gender","Female").
                                queryParam("nameContains","e").
                            when().
                                get("/spartans/search");
                                response.then().assertThat().statusCode(200).
                            and().
                                 assertThat().contentType("application/json;charset=UTF-8");

        Response response2= given().
                                 auth().basic("admin", "admin").
                                 accept(ContentType.JSON).
                             and().
                                  queryParams("gender","Female","nameContains","e").
                             when().get("/spartans/search").prettyPeek();


        Response response3 = given().
                                  auth().basic("admin", "admin").
                                  accept(ContentType.JSON).
                           when().
                                get("/spartans/search?gender=Female&nameContains=e");

                          response3.then().assertThat().statusCode(200).assertThat().contentType("application/json;charset=UTF-8");
                          assertTrue(response3.body().asString().contains("Female"));
                          assertTrue(response3.body().asString().contains("Penny"));

    }
    /**
     *
     * Given accept type is xml
     * And query parameters values are : Gender \Female, name contains \e
     *When user sends get request to api/spartans/search
     * Then response status code should be 406
     * And "Could not find acceptable representation" shouls be in reponse payload
     *
     */
    @Test
    public void invalid_header_test_With_parameters(){
       Response response=given().
                            auth().basic("admin", "admin").
                            accept(ContentType.XML).
                             queryParams("gender","Female","nameContains","e").
                        when().
                            get("/spartans/search");


        Response response2=
             given().
                auth().basic("admin", "admin").
                accept(ContentType.XML).
                queryParams("gender","Female").
                queryParam("nameContains","e").
             when()
                .get("/spartans/search");

        Map<String ,Object>paramsMap=new HashMap<>();

        paramsMap.put("gender","Female");
        paramsMap.put("nameContains","e");

        Response response3=
                given().
                        auth().basic("admin", "admin").
                        accept(ContentType.XML).
                        queryParams(paramsMap).
                 when().
                        get("/spartans/search").prettyPeek();
        assertEquals(406,response3.statusCode());
        assertTrue(response3.body().asString().contains("Female"));
    }
}
