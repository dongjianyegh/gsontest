package com.example.gsontest.eventtrack.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DetailInfo {
    @SerializedName("src")
    @Expose
    private String src;

    @SerializedName("lans")
    @Expose
    private ArrayList<String> lans;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public ArrayList<String> getLans() {
        return lans;
    }

    public void setLans(ArrayList<String> lans) {
        this.lans = lans;
    }
}
