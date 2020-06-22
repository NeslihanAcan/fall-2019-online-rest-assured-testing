package com.automation.restapitests_murodil;
import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithPath_Day4 {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI=ConfigurationReader.getProperty("SPARTAN.URI");
    }
    /*
    Given accept type is json
    And path param id is 556
    when user sends a get request to "/spartans/{id}"
    Then status code is 200
    And content-type is "application/json;char"
    And response payload values match the following :
       " id": 556,
        "name": "Canan",
        "gender": "Female",
        "phone": 8753478209
     */
    @Test
    public void spartanGetWithID_Json_Test_Using_Path(){
        Response response=
                   given().
                        auth().basic("admin","admin").
                           accept(ContentType.JSON).
                   and().
                        pathParam("id",556).
                   when().
                        get("/spartans/{id}");
//        System.out.println(response.body().path("id").toString());
//        System.out.println(response.body().path("name").toString());
//        System.out.println(response.body().path("gender").toString());
//        System.out.println(response.body().path("phone").toString());

        int id=response.body().path("id");
        String firstName=response.body().path("name");
        String spartanGender=response.body().path("gender");
        long phoneNumber=response.body().path("phone");

        System.out.println("id "+id);
        System.out.println("firstName " +firstName);
        System.out.println("spartanGender "+spartanGender);
        System.out.println("phoneNumber "+phoneNumber);
        assertEquals(200,response.statusCode());
        assertEquals(556,id);
        assertEquals("Canan",firstName);
        assertEquals("Female",spartanGender);
        assertEquals(8753478209L,phoneNumber);
    }
    @Test
    public void getAllSpartans_using_path_with_list(){

        Response response=given().auth().basic("admin","admin").get("/spartans/");

        int firstID=response.path("id[0]");
        System.out.println("firstID "+firstID);

        String first1stName=response.path("name[0]");
        System.out.println("first1stName "+first1stName);

        String lastFirstName=response.path("name[-1]");
        System.out.println("lastFirstName "+lastFirstName);

        List<String >names=response.path("name");
        System.out.println("Number of names ="+names.size());
        System.out.println(names);

        List<Object>phoneNumbers=response.path("phone");
        for (Object ph:phoneNumbers) {
            System.out.println("Phone Numbers " +ph);
        }

    }
    @Test
    public void getCountries_And_Extract_nested_key_values_using_path(){
        baseURI=ConfigurationReader.getProperty("ORDS.URI");
        Response response=given().
                              queryParam("q","{\"region_id\":2}").
                          when().
                              get("/countries");
        String firstCountryID=response.path("items.country_id[0]");
        String firstCountryName=response.path("items.country_name[0]");

        System.out.println("firstCountryID "+firstCountryID);
        System.out.println("firstCountryName "+firstCountryName);

        //Get all country names and print

        List<String >countryNames=response.path("items.country_name");
        System.out.println("Country Names= "+countryNames);

        //Assert that all regions id's equal to 2
        List<Integer>regionIDs=response.path("items.region_id");
        for(int regionID:regionIDs){
            assertEquals(2,regionID);
        }
        }


}

