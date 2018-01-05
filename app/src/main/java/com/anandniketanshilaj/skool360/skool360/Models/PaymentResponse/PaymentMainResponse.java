package com.anandniketanshilaj.skool360.skool360.Models.PaymentResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admsandroid on 1/5/2018.
 */

public class PaymentMainResponse {
    @SerializedName("Success")
    @Expose
    private String success;
    @SerializedName("TermTotal")
    @Expose
    private String termTotal;
    @SerializedName("TermPaid")
    @Expose
    private String termPaid;
    @SerializedName("TermDuePay")
    @Expose
    private String termDuePay;
    @SerializedName("TermDiscount")
    @Expose
    private String termDiscount;
    @SerializedName("TermLateFee")
    @Expose
    private String termLateFee;
    @SerializedName("FinalArray")
    @Expose
    private List<FinalArrayPayment> finalArray = new ArrayList<FinalArrayPayment>();

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getTermTotal() {
        return termTotal;
    }

    public void setTermTotal(String termTotal) {
        this.termTotal = termTotal;
    }

    public String getTermPaid() {
        return termPaid;
    }

    public void setTermPaid(String termPaid) {
        this.termPaid = termPaid;
    }

    public String getTermDuePay() {
        return termDuePay;
    }

    public void setTermDuePay(String termDuePay) {
        this.termDuePay = termDuePay;
    }

    public String getTermDiscount() {
        return termDiscount;
    }

    public void setTermDiscount(String termDiscount) {
        this.termDiscount = termDiscount;
    }

    public String getTermLateFee() {
        return termLateFee;
    }

    public void setTermLateFee(String termLateFee) {
        this.termLateFee = termLateFee;
    }

    public List<FinalArrayPayment> getFinalArray() {
        return finalArray;
    }

    public void setFinalArray(List<FinalArrayPayment> finalArray) {
        this.finalArray = finalArray;
    }

}
