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
public class HRApiPathTests_Day5 {

    @BeforeAll
    public static void setup(){
        baseURI=ConfigurationReader.getProperty("ORDS.URI");
    }
    /**
     * Given accept type  is JSON
     * path param - US
     * When users sends request to /countries
     * Then status code is 200
     * And country_id is US
     * And country_name is United States of America
     *
     */

    @Test
   public void verifyCountryDetailsUsing_Path_method(){
      Response response=
                 given()
                      .accept(ContentType.JSON).
                 and().
                      pathParam("country_id","US").
                 when().
                       get("/countries/{country_id}").prettyPeek();

     assertEquals(200,response.statusCode());
     assertEquals("US",response.path("country_id"));
     assertEquals("United States of America",response.path("country_name"));

    }
    /*
    Given accept type is Json
    Query param value  - q={"department_id":"80"}
    When users sends request to /employees
    Then status code is 200
    And all job_id's start with 'SA'
    And all department_id' are 80
    Count is 25
     */
    @Test
    public void verify_multiple_employee_details_using_path(){

        Response response=

                given().accept(ContentType.JSON).
                and().queryParam("q","{\"department_id\":\"80\"}").
                when().get("/employees").prettyPeek();
        //Validations
        assertEquals(200,response.statusCode());

        //extract values from json and store into list,vars
        List<String>jobIDs=response.path("items.job_id");
        List<Integer>deptIDs=response.path("items.department_id");
       int count=response.path("count");

       //do assertions
        for(String jobID:jobIDs){
            assertTrue(jobID.startsWith("SA"));//one way

            assertEquals("SA",jobID.substring(0,2));//second way

            //And all department_id' are 80
            deptIDs.forEach(id->assertEquals(new Integer(80),id));

            assertEquals(25,count);
        }
    }

}
