
package com.example.myapplication.Model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("Android")
    @Expose
    private List<Android> android = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Android> getAndroid() {
        return android;
    }

    public void setAndroid(List<Android> android) {
        this.android = android;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", android=" + android +
                '}';
    }
}
