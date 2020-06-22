package com.automation.restapi_gson_serilization_murodil;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import com.automation.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class JsonToCollections_day6 {

    @Test
    public void hrApiEmployee_jsondata_to_java_object(){

        Response response=given().accept(ContentType.JSON).
                          pathParam("employee_id",105).
                          when().get("http://18.233.67.181:1000/ords/hr/employees/{employee_id}");
        Map<String, Object>employeeMap= response.body().as(Map.class);
        System.out.println(employeeMap.toString());

        String  firstName=employeeMap.get("firstname").toString();
        System.out.println("firstName = " + firstName);
        assertEquals("Celestine",firstName);

        double salary= (Double) employeeMap.get("salary");
        System.out.println("salary = " + salary);
        assertEquals(4800.0,salary);

    }
    @Test
    public void convertAll_Spartans_to_List_of_Maps(){
     Response response=given().
                          accept(ContentType.JSON).
                       when().
                           auth().basic("admin","admin").get("http://54.152.21.73:8000/api/spartans");

        List<Map<String,Object>>jsonListOfMap=response.body().as(List.class);
        System.out.println(jsonListOfMap);

        //print all data of first spartan
        System.out.println(jsonListOfMap.get(0));

        Map<String,?>firstSpartan=jsonListOfMap.get(0);
        System.out.println("firstSpartan = " + firstSpartan);

        System.out.println(firstSpartan.get("name"));
        
        //print list of each map
        int counter=1;
        for(Map<String ,?> spartan:jsonListOfMap){
            System.out.println(counter+" - spartan = " + spartan);
            counter++;
        }
    }
    @Test
    public void regions_data_to_collections(){
        Response response=given().accept(ContentType.JSON).get("http://54.152.21.73:1000/ords/hr/regions/");

        Map<String ,?>dataMap=response.body().as(Map.class);
        System.out.println("dataMap = " + dataMap);
        System.out.println("===============================");
        System.out.println(dataMap.get("items"));

        List<Map<String,?>>regionsList= (List<Map<String, ?>>) dataMap.get("items");
        System.out.println(regionsList.get(0).get("region_name"));
        System.out.println(regionsList.get(1).get("region_name"));
        System.out.println(regionsList.get(2).get("region_name"));

    }






}
