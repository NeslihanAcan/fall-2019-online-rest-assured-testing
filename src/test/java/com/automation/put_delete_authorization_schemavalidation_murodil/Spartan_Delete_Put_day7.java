package com.automation.put_delete_authorization_schemavalidation_murodil;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class Spartan_Delete_Put_day7 {

    @BeforeAll
    public static void setup(){
        baseURI=ConfigurationReader.getProperty("SPARTAN.URI");

    }
    @Test
    public void UpdateExistingSpartan_PUT_request_test(){
        Map<String,Object>requestMap=new HashMap<>();

        requestMap.put("name","Neslihan");
        requestMap.put("gender","Female");
        requestMap.put("phone",3876543259L);

        given().auth().basic("admin","admin").contentType(ContentType.JSON).
                and().body(requestMap).
                and().pathParam("id",3).
                and().put("/spartans/{id}").
                then().assertThat().statusCode(204);
    }
    @Test
    public void deleteExistingSpartan_Delete_request_test(){
        Random random=new Random();
        int idToDelete=random.nextInt(351);
        given().auth().basic("admin","admin").
                and().pathParam("id",idToDelete).
                and().delete("/spartans/{id}").
                then().assertThat().statusCode(204);
    }

}
