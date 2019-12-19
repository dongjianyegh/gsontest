package com.example.gsontest.pingback.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Ec38Item {

    @SerializedName("cc")
    @Expose
    public int cc;

    @SerializedName("sc")
    @Expose
    public int sc;

    @SerializedName("sl")
    @Expose
    public ArrayList<Integer> sl;
}
