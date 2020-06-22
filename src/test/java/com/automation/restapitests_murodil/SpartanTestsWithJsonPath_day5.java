package com.automation.restapitests_murodil;

import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithJsonPath_day5 {



    @BeforeAll
    public static void setup() {
        baseURI = ConfigurationReader.getProperty("SPARTAN.URI");
    }
    /**
     * Given accept type is json
     * And path param spartan id is 11
     * When user sends a get request to /spartans/{id}
     * Then status code is 200
     * And content type is json
     * And         "id": 593,
     *             "name": "Jon Snow",
     *             "gender": "Male",
     *             "phone": 1112223334
     */
    @Test
    public void verifySpartanInfoUsingJsonPath(){
        Response response=

                given().auth().basic("admin","admin"). accept(ContentType.JSON).
                 and().pathParam("id",593).
                 when().get("/spartans/{id}");

        assertEquals(200,response.statusCode());
        assertEquals("application/json;charset=UTF-8",response.contentType());
        
        //Store response json body/payload into JSONPATH object

        //convert json body to JsonPath object
        JsonPath json=response.jsonPath();//one way
       // JsonPath json=response.body().jsonPath();//another way
        JsonPath json2=new JsonPath(response.body().asString());//another way

        /**
         * "id": 593,
         *  "name": "Jon Snow",
         *   "gender": "Male",
         *  "phone": 1112223334
         *  verification for this body
         */

        //read the values from json using jsonpath
        int id=json.getInt("id");
        String name=json.getString("name");
        String gender=json.getString("gender");
        Long phone=json.getLong("phone");

        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("gender = " + gender);
        System.out.println("phone = " + phone);

        assertEquals(593,id);
        assertEquals("Jon Snow",name);
        assertEquals("Male",gender);
        assertEquals(1112223334L,phone);



    }

    @Test
    public void GetAStudent_cbraining_api_jsonpath(){
       JsonPath jsonData=get("http://api.cybertektraining.com/student/11721").jsonPath();

       String firstName=jsonData.getString("students.firstName");
       String lastName=jsonData.getString("students.lastName");
       String phone=jsonData.getString("students.contact.phone");
       String companyName=jsonData.getString("students.company.companyName");
       String city=jsonData.getString("student.company.address.city");

        System.out.println("firstName = " + firstName);
        System.out.println("lastName = " + lastName);
        System.out.println("phone = " + phone);
        System.out.println("companyName = " + companyName);
        System.out.println("city = " + city);

    }
    @Test
    public void hr_api_countries_jsonpath_list(){
        JsonPath json=get("http://54.152.21.73:1000/ords/hr/countries").jsonPath();

        List<String >countryNames=json.getList("items.country_id");
        System.out.println("countryNames = " + countryNames);

        List<String >countryIDs=json.getList("items.country_id");
        System.out.println("countryIDs = " + countryIDs);

        //Get country names of all countries in region id 2
        List<String>countriesInRegion2=json.getList("items.findAll {it.region_id==2}.country_name");
        System.out.println("countriesInRegion2 = " + countriesInRegion2);


    }
}
