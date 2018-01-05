package com.anandniketanshilaj.skool360.skool360.Models.PaymentResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admsandroid on 1/5/2018.
 */

public class GetPaymentLedgerResponse {
    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("FinalArray")
    @Expose
    private List<FinalArrayPaymentLedger> finalArray = new ArrayList<FinalArrayPaymentLedger>();

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public List<FinalArrayPaymentLedger> getFinalArray() {
        return finalArray;
    }

    public void setFinalArray(List<FinalArrayPaymentLedger> finalArray) {
        this.finalArray = finalArray;
    }

}
