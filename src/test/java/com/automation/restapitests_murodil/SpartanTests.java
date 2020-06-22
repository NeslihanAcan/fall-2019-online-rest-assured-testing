package com.automation.restapitests_murodil;

import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.beans.Transient;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import static io.restassured.RestAssured.given;

public class SpartanTests {

    String spartanAllUrl= ConfigurationReader.getProperty("SPARTAN.URI");
    @Test
    public void viewAllSpartanTest(){
//      Response response=  RestAssured.get(spartanAllUrl);
//        System.out.println(response.statusCode());
//        System.out.println(response.body().prettyPeek());

        given().
                auth().basic("admin", "admin").
                baseUri(spartanAllUrl).
                when().
                get("/spartans").prettyPeek().
                then().statusCode(200);
    }
    @Test
    public void viewAllSpartansTest2(){

        Response response= given().accept(ContentType.JSON).
                              auth().basic("admin", "admin").
                               baseUri(spartanAllUrl).
                               when().
                               get("/spartans").prettyPeek();
            response.then().assertThat().statusCode(200);
               response.then().assertThat().contentType("application/json;charset=UTF-8");
    }
    @Test
    public void viewAllSpartansTest3(){
           given().accept(ContentType.JSON).
                   auth().basic("admin", "admin").
                   baseUri(spartanAllUrl).
           when().
                 get(spartanAllUrl).prettyPeek().
           then().
                statusCode(200).
           and().
               contentType("application/json;charset=UTF-8");
    }
    /*
    When users send a get request to /spartans/3
    then status code should be 200
    And content type should be "application/json;charset=UTF-8"
    And json body should contain "Lorenza"
     */
    @Test
    public void viewSpartanTest4(){
      Response response=  given().
              auth().basic("admin","admin").baseUri(spartanAllUrl).
        when().
              get("/spartans/10").prettyPeek();
       response.then().assertThat().statusCode(200).contentType("application/json;charset=UTF-8");
       assertTrue(response.body().asString().contains("Lorenza"));

    }
}
