package com.anandniketanshilaj.skool360.skool360.Models.HomeWorkResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admsandroid on 12/12/2017.
 */

public class FinalArrayHomeWork {
    @SerializedName("HomeWorkDate")
    @Expose
    private String homeWorkDate;
    @SerializedName("Data")
    @Expose
    private ArrayList<HomeWorkInfo> data = new ArrayList<HomeWorkInfo>();

    public String getHomeWorkDate() {
        return homeWorkDate;
    }

    public void setHomeWorkDate(String homeWorkDate) {
        this.homeWorkDate = homeWorkDate;
    }

    public ArrayList<HomeWorkInfo> getData() {
        return data;
    }

    public void setData(ArrayList<HomeWorkInfo> data) {
        this.data = data;
    }

}
