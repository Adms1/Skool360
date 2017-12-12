package com.anandniketanshilaj.skool360.skool360.Models.HomeWorkResponse;

import com.anandniketanshilaj.skool360.skool360.Models.PTMTeacherResponse.FinalArray;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admsandroid on 12/12/2017.
 */

public class GetHomeWorkModel {
    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("FinalArray")
    @Expose
    private List<FinalArrayHomeWork> finalArray = new ArrayList<FinalArrayHomeWork>();

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<FinalArrayHomeWork> getFinalArray() {
        return finalArray;
    }

    public void setFinalArray(List<FinalArrayHomeWork> finalArray) {
        this.finalArray = finalArray;
    }
}
