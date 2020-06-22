package com.automation.restapi_gson_serilization_murodil;

import java.util.List;

public class AllSpartans {
    private List<Spartan>spartanList;

    public AllSpartans(List<Spartan>spartanList){
        this.spartanList=spartanList;

    }
    public void AllSpartans(){

    }

    public List<Spartan> getSpartanList() {
        return spartanList;
    }

    public void setSpartanList(List<Spartan> spartanList) {
        this.spartanList = spartanList;
    }

    @Override
    public String toString() {
        return "AllSpartans{" +
                "spartanList=" + spartanList +
                '}';
    }
}
