package com.automation.restapitests_murodil;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class JsonToJavaDataStructures_day_5 {

    @Test
    public void convertSpartanData_to_Map(){
        Response response=given().accept(ContentType.JSON).
                auth().basic("admin","admin").
                when().get("http://54.152.21.73:8000/api/spartans/593");

        Map<String,Object>spartanMap=response.body().as(Map.class);
        System.out.println("spartanMap.toString() = " + spartanMap.toString());

    }

}
