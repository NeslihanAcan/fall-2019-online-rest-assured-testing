package com.automation.restapi_gson_serilization_murodil;


import com.google.gson.Gson;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class POJO_deserialization {

    @Test
    public void spartan_to_pojo_object_deserialization(){
        Response response=given().accept(ContentType.JSON).
                auth().basic("admin","admin").get("http://18.233.67.181:8000/api/spartans/613");

        //deserialize json to pojo java object
        //JSON response body>>>Custom Java class object
        Spartan spartan=response.body().as(Spartan.class);
        System.out.println(spartan.getName());
        System.out.println(spartan.getGender());
        System.out.println(spartan.getId());
        System.out.println(spartan.getPhone());
        System.out.println(spartan.toString());

        assertEquals("Ashraf",spartan.getName());
        assertEquals("Male",spartan.getGender());
        assertEquals(new Integer(613),spartan.getId());
        assertEquals(new Long(1112223334),spartan.getPhone());
    }
    @Test
    public void gSonExample(){
        Spartan spartan=new Spartan(8,"Ramle","female",2156983625L);
        Gson gson=new Gson();
        //serialize spartan object to JSON format using GSON
        String json=gson.toJson(spartan);
        System.out.println("json = " + json);
        
        String myJson="{\"id\":34,\"name\":\"Nesli\",\"gender\":\"female\",\"phone\":2156987625}";
        //deserialization. Convert Json to Java spartan object
        Spartan neslihan=gson.fromJson(myJson,Spartan.class);
        System.out.println("neslihan = " + neslihan);

        //fromJson (String json,Which.class) it will convert the json to object of class
        //toJson (Java Object) it will take the java object and create json and return it

    }
    @Test
    public void list_of_spartans_pojo_deserialization(){
        Response response=
                given().
                        auth().basic("admin","admin").
                     accept(ContentType.JSON).
                when().
                     get("http://18.233.67.181:8000/api/spartans");
        List<Spartan>allSpartans=response.body().as(new TypeRef<List<Spartan>>() {});

        System.out.println("allSpartans = " + allSpartans);
        System.out.println(allSpartans.get(0));

        Spartan first=allSpartans.get(0);
        System.out.println(first.toString());

        //For each loop will give all spartans separately

        for (Spartan sp:allSpartans){
            System.out.println(sp.toString());
        }
        System.out.println("============ALLSPARTAN CLASS===========");
        //USING ALLSPARTANS CLASS for deserialization
        //TODO: Fix the deserialization issue
        AllSpartans allSpartansObj=response.body().as(AllSpartans.class);
        System.out.println("allSpartansObj = " + allSpartansObj);
        System.out.println(allSpartansObj.getSpartanList().get(0).toString());

        
    }
}
