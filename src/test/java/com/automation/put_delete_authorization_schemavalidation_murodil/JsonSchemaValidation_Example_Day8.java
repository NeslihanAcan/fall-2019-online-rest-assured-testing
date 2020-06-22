package com.automation.put_delete_authorization_schemavalidation_murodil;
import org.junit.jupiter.api.Test;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
public class JsonSchemaValidation_Example_Day8 {

    @Test
    public void SchemaValidationOfSingleSpartanAPI(){
        given().auth().basic("admin","admin").accept(ContentType.JSON).
                pathParam("id",3).
                when().get("http://18.233.67.181:8000/api/spartans/{id}").
                then().assertThat().body(matchesJsonSchemaInClasspath("MurodilDay8SingleSpartanSchema.json"));

   }
}
