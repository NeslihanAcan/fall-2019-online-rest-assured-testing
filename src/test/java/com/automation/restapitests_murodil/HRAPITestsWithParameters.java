package com.automation.restapitests_murodil;
import com.automation.utilities.ConfigurationReader;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
public class HRAPITestsWithParameters {

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI=ConfigurationReader.getProperty("ORDS.URI");
    }

    /**
     * Given accept type is Json
     * And parameters: q =region_id=2
     * When users sends a Get request to "/countries"
     * Then status code is 200
     * And content type is application/json
     * And payload should contain "United States of America"
     *
     */
    @Test
    public void getCountries_using_queryParameters(){

        Response response=
                given()
                   .accept(ContentType.JSON).
                and()
                   .queryParams("q","{\"region_id\":2}").
                when().
                    get("/countries").prettyPeek();

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("United States of America"));

    }
    @Test
    public void getEmployees_using_queryParameters(){

        Response response=
                given()
                        .accept(ContentType.JSON).
                        and()
                        .queryParams("q","{\"job_id\":\"IT_PROG\"}").
                        when().
                        get("/employees").prettyPeek();

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("IT_PROG"));

    }

}
