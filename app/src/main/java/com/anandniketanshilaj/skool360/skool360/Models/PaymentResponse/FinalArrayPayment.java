package com.anandniketanshilaj.skool360.skool360.Models.PaymentResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admsandroid on 1/5/2018.
 */

public class FinalArrayPayment {
    @SerializedName("Term")
    @Expose
    private Integer term;
    @SerializedName("Data")
    @Expose
    private List<FeesDatum> data = new ArrayList<FeesDatum>();

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public List<FeesDatum> getData() {
        return data;
    }

    public void setData(List<FeesDatum> data) {
        this.data = data;
    }

}
