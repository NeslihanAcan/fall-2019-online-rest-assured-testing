package com.automation.restapitests_murodil;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class HamcrestMatchers_apiTest_day5 {

    /**
     * given accept type is json
     * And path param  id is 593
     * when user sends a get request  to/spartans/{id}
     * Then status code is 200
     * And content type is Json
     * And json data has following
     *         "id": 593,
     *         "name": "Jon Snow",
     *         "gender": "Male",
     *         "phone": 1112223334
     */
    @Test
    public void getSpartanAnd_assertUsing_hamcrest(){
               // Request
                given().accept(ContentType.JSON).
                auth().basic("admin","admin").
                and().pathParam("id",593).
                when().get("http://54.152.21.73:8000/api/spartans/{id}").
                //Response
                then().assertThat().statusCode(200).
                and().assertThat().contentType("application/json;charset=UTF-8").
                and().assertThat().body("id",equalTo(593),
                     "name",equalTo("Jon Snow"),
                                             "gender",equalTo("Male"),
                                            "phone",equalTo(1112223334));
    }
}
