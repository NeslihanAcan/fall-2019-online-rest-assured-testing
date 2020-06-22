package com.automation.restapi_gson_serilization_murodil;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class POSTActions {


    /**
     * Given Accept and Content type is Json
     * And request json  body is
     * {
     * "gender":"Female",
     * "name"  : "Neslihan",
     * "phone" : 4923084637
     * }
     * When user sends POST request to "/spartans"
     * Then status code 201
     * And content type should be application/json
     * And json/payload response should contain:
     * "A Spartan is Born" message
     * and same data what is posted
     */


    @Test
    public void post_new_spartan_test() {
        Response response =
                given().
                        auth().basic("admin", "admin").
                        and().
                        accept(ContentType.JSON).
                        and().
                        contentType(ContentType.JSON).
                        and().body("{\n" +
                        "\"gender\":\"Female\",\n" +
                        "\"name\" :\"Neslihan\",\n" +
                        "\"phone\":4923084637}").
                        when().post("http://18.233.67.181:8000/api/spartans/");
        //Response validations
        response.then().assertThat().statusCode(201).
                and().assertThat().contentType("application/json");
        //extract message using path method
        String message1 = response.path("success");
        //extract message using jsonpath
        JsonPath json = response.jsonPath();
        String message2 = json.getString("success");
        System.out.println("message1 = " + message1);
        System.out.println("message2 = " + message2);
        assertEquals("Neslihan", json.getString("data.name"));
        assertEquals("Female", json.getString("data.gender"));
        assertEquals(4923084637L, json.getLong("data.phone"));
    }

    @Test
    public void post_new_spartan_with_map_test() {

        //Create a map to be used as a body for post request
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("name", "Ahmet Halit");
        requestMap.put("gender", "Male");
        requestMap.put("phone", 2758364539L);

        Response response =
                given().
                        auth().basic("admin", "admin").
                        and().
                        accept(ContentType.JSON).
                        and().
                        contentType(ContentType.JSON).
                        and().body(requestMap).
                        when().post("http://18.233.67.181:8000/api/spartans/");
        //Response validations
        response.then().assertThat().statusCode(201).
                and().assertThat().contentType("application/json");
        //extract message using path method
        String message1 = response.path("success");
        //extract message using jsonpath
        JsonPath json = response.jsonPath();
        String message2 = json.getString("success");
        System.out.println("message1 = " + message1);
        System.out.println("message2 = " + message2);
        assertEquals("Ahmet Halit", json.getString("data.name"));
        assertEquals("Male", json.getString("data.gender"));
        assertEquals(2758364539L, json.getLong("data.phone"));

        int spartanID = json.getInt("data.id");
        System.out.println("spartanID = " + spartanID);
        given().auth().basic("admin", "admin").get("http://18.233.67.181:8000/api/spartans/" + spartanID).body().prettyPrint();
    }

    @Test
    public void post_new_spartan_with_pojo_object() {

        //Create a spartan object to be used as a body for post request

        Spartan spartan = new Spartan();
        spartan.setGender("Female");
        spartan.setName("Ramle Reyhan");
        spartan.setPhone(3874638390L);
        Response response =
                given().
                        auth().basic("admin", "admin").
                        and().
                        accept(ContentType.JSON).
                        and().
                        contentType(ContentType.JSON).
                        and().body(spartan).
                        when().post("http://18.233.67.181:8000/api/spartans/");
        //Response validations
        response.then().assertThat().statusCode(201).
                and().assertThat().contentType("application/json");
        //extract message using path method
        String message1 = response.path("success");
        //extract message using jsonpath
        JsonPath json = response.jsonPath();
        String message2 = json.getString("success");
        System.out.println("message1 = " + message1);
        System.out.println("message2 = " + message2);
        assertEquals("Ramle Reyhan", json.getString("data.name"));
        assertEquals("Female", json.getString("data.gender"));
        assertEquals(3874638390L, json.getLong("data.phone"));

        int spartanID = json.getInt("data.id");
        System.out.println("spartanID = " + spartanID);
        given().auth().basic("admin", "admin").get("http://18.233.67.181:8000/api/spartans/" + spartanID).body().prettyPeek();
    }

    public static String iniCape(String word) {
        return word.substring(0, 1).toUpperCase() + word.substring(1);

    }

    @Test
    public void post_new_spartan_with_pojo_object_from_UInames_api() {

        for (int i = 1; i <= 2; i++) {


            //Send request to "http://uinames.com/api/" and extract name and gender
            JsonPath uinamesJson = get("http://uinames.com/api/").body().jsonPath();

            //Create a spartan object to be used as a body for post request
            Spartan spartan = new Spartan();
            spartan.setName(uinamesJson.getString("name") + " " + uinamesJson.getString("surname"));
            spartan.setGender(iniCape(uinamesJson.getString("gender")));
            spartan.setPhone(3874638390L);

            Response response =
                    given().
                            auth().basic("admin", "admin").
                            and().
                            accept(ContentType.JSON).
                            and().
                            contentType(ContentType.JSON).
                            and().body(spartan).
                            when().post("http://18.233.67.181:8000/api/spartans/");
            //Response validations
            response.then().assertThat().statusCode(201).
                    and().assertThat().contentType("application/json");
            //extract message using path method
            String message1 = response.path("success");
            //extract message using jsonpath
            JsonPath json = response.jsonPath();
            String message2 = json.getString("success");
            System.out.println("message1 = " + message1);
            System.out.println("message2 = " + message2);
            assertEquals("Ramle Reyhan",json.getString("data.name"));
            assertEquals("Female",json.getString("data.gender"));
            assertEquals(3874638390L,json.getLong("data.phone"));

            int spartanID = json.getInt("data.id");
            System.out.println("spartanID = " + spartanID);
            given().auth().basic("admin", "admin").get("http://18.233.67.181:8000/api/spartans/" + spartanID).body().prettyPeek();
        }
    }
}