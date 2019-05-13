package com.example.android.detailedweatherreport;

import com.google.gson.annotations.SerializedName;

public class List_item {

    //Category of data
    private String category;

    //Value of data
    private String value;

    public List_item(String cat,String val){
            category  = cat;
            value = val;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public String getValue() {
        return value;
    }
}
