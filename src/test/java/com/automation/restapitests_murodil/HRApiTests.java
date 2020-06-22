package com.automation.restapitests_murodil;

import org.junit.jupiter.api.Test;
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
public class HRApiTests {

    String baseURI="http://54.152.21.73:1000/ords/hr/regions";

    @Test
    public void getAllRegions(){
        Response response= get(baseURI);
        System.out.println("Status Code:" +response.statusCode());
        System.out.println("Content Type: "+response.contentType());
        response.body().prettyPrint();
        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Europe"));
    }}
