package com.automation.restapi_gson_serilization_murodil;

import com.google.gson.annotations.SerializedName;

/**
 *           "id": 613,
 *           "name": "Ashraf",
 *           "gender": "Male",
 *           "phone": 1112223334
 */


public class Spartan {
    @SerializedName("id")
    private Integer id;
    private String name;
    private  String gender;
    private Long phone;

    public Spartan() {
    }

    public Spartan(Integer id, String name, String gender, Long phone) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }



    @Override
    public String toString() {
        return "Spartan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }
}
