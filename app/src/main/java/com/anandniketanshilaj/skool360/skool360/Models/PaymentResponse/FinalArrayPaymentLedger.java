package com.anandniketanshilaj.skool360.skool360.Models.PaymentResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admsandroid on 1/5/2018.
 */

public class FinalArrayPaymentLedger {
    @SerializedName("PayDate")
    @Expose
    private String payDate;
    @SerializedName("Paid")
    @Expose
    private String paid;
    @SerializedName("Data")
    @Expose
    private List<PaymentDatum> data = new ArrayList<PaymentDatum>();

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public List<PaymentDatum> getData() {
        return data;
    }

    public void setData(List<PaymentDatum> data) {
        this.data = data;
    }

}
