package com.automation.pojos;


import com.google.gson.annotations.SerializedName;
import org.apache.http.impl.client.StandardHttpRequestRetryHandler;

import java.util.Objects;

/**
 *This class represents Spartan POJO
 * Example of JSON response :
 * {
 *     "id":393,               --> private int id;
 *     "name":"Michael Scott", --> private String name;
 *     "gender":"Male",        --> private String gender;
 *     "phone":6969696969     --> @SerializedName("phone") private long phoneNumber;
 * }
 */

public class Spartan {
    private int id;
    private String name;
    private String gender;
    @SerializedName("phone")
    private long phoneNumber;


    public Spartan(String name,String gender,long phoneNumber){
        this.name=name;
        this.gender=gender;
        this.phoneNumber=phoneNumber;
    }
    public Spartan(int id,String name,String gender,long phoneNumber){
        this.id=id;
        this.name=name;
        this.gender=gender;
        this.phoneNumber=phoneNumber;

    }
    public Spartan(){

    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }



    @Override
    public String toString() {
        return "Spartan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber=" + phoneNumber +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spartan spartan = (Spartan) o;
        return id == spartan.id &&
                phoneNumber == spartan.phoneNumber &&
                Objects.equals(name, spartan.name) &&
                Objects.equals(gender, spartan.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, phoneNumber);
    }
}
