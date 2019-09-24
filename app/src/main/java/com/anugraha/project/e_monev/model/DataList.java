package com.anugraha.project.e_monev.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataList {

    @SerializedName("data")
    @Expose
    private ArrayList<Data> data = null;

    public ArrayList<Data> getdata() {
        return data;
    }

    public void setdata(ArrayList<Data> data) {
        this.data = data;
    }

}