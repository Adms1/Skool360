package com.anandniketanshilaj.skool360.skool360.Models.PaymentResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admsandroid on 1/5/2018.
 */

public class FeesDatum {
    @SerializedName("PreviousBalance")
    @Expose
    private String previousBalance;
    @SerializedName("AdmissionFees")
    @Expose
    private String admissionFees;
    @SerializedName("CautionFees")
    @Expose
    private String cautionFees;
    @SerializedName("TutionFees")
    @Expose
    private String tutionFees;
    @SerializedName("TransportFees")
    @Expose
    private String transportFees;
    @SerializedName("Imprest")
    @Expose
    private String imprest;
    @SerializedName("LateFees")
    @Expose
    private String lateFees;
    @SerializedName("Discount")
    @Expose
    private String discount;
    @SerializedName("TotalPayableFees")
    @Expose
    private String totalPayableFees;
    @SerializedName("PaidFees")
    @Expose
    private String paidFees;
    @SerializedName("Balance")
    @Expose
    private String balance;
    @SerializedName("URL")
    @Expose
    private String uRL;
    @SerializedName("ButtonVisiblity")
    @Expose
    private Boolean buttonVisiblity;

    public String getPreviousBalance() {
        return previousBalance;
    }

    public void setPreviousBalance(String previousBalance) {
        this.previousBalance = previousBalance;
    }

    public String getAdmissionFees() {
        return admissionFees;
    }

    public void setAdmissionFees(String admissionFees) {
        this.admissionFees = admissionFees;
    }

    public String getCautionFees() {
        return cautionFees;
    }

    public void setCautionFees(String cautionFees) {
        this.cautionFees = cautionFees;
    }

    public String getTutionFees() {
        return tutionFees;
    }

    public void setTutionFees(String tutionFees) {
        this.tutionFees = tutionFees;
    }

    public String getTransportFees() {
        return transportFees;
    }

    public void setTransportFees(String transportFees) {
        this.transportFees = transportFees;
    }

    public String getImprest() {
        return imprest;
    }

    public void setImprest(String imprest) {
        this.imprest = imprest;
    }

    public String getLateFees() {
        return lateFees;
    }

    public void setLateFees(String lateFees) {
        this.lateFees = lateFees;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getTotalPayableFees() {
        return totalPayableFees;
    }

    public void setTotalPayableFees(String totalPayableFees) {
        this.totalPayableFees = totalPayableFees;
    }

    public String getPaidFees() {
        return paidFees;
    }

    public void setPaidFees(String paidFees) {
        this.paidFees = paidFees;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getURL() {
        return uRL;
    }

    public void setURL(String uRL) {
        this.uRL = uRL;
    }

    public Boolean getButtonVisiblity() {
        return buttonVisiblity;
    }

    public void setButtonVisiblity(Boolean buttonVisiblity) {
        this.buttonVisiblity = buttonVisiblity;
    }



}
