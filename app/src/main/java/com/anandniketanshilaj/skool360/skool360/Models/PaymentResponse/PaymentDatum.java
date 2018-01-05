package com.anandniketanshilaj.skool360.skool360.Models.PaymentResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admsandroid on 1/5/2018.
 */

public class PaymentDatum {
    @SerializedName("Term")
    @Expose
    private String term;
    @SerializedName("TermDetail")
    @Expose
    private String termDetail;
    @SerializedName("GRNO")
    @Expose
    private String gRNO;
    @SerializedName("PayMode")
    @Expose
    private String payMode;
    @SerializedName("Cheque Number")
    @Expose
    private String chequeNumber;
    @SerializedName("Bank Name")
    @Expose
    private String bankName;
    @SerializedName("PaidFee")
    @Expose
    private String paidFee;
    @SerializedName("ReceiptNo")
    @Expose
    private String receiptNo;
    @SerializedName("AdmissionFee")
    @Expose
    private String admissionFee;
    @SerializedName("CautionFee")
    @Expose
    private String cautionFee;
    @SerializedName("PreviousFees")
    @Expose
    private String previousFees;
    @SerializedName("TuitionFee")
    @Expose
    private String tuitionFee;
    @SerializedName("Transport")
    @Expose
    private String transport;
    @SerializedName("ImprestFee")
    @Expose
    private String imprestFee;
    @SerializedName("LatesFee")
    @Expose
    private String latesFee;
    @SerializedName("DiscountFee")
    @Expose
    private String discountFee;
    @SerializedName("PayPaidFees")
    @Expose
    private String payPaidFees;
    @SerializedName("CurrentOutstandingFees")
    @Expose
    private String currentOutstandingFees;

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTermDetail() {
        return termDetail;
    }

    public void setTermDetail(String termDetail) {
        this.termDetail = termDetail;
    }

    public String getGRNO() {
        return gRNO;
    }

    public void setGRNO(String gRNO) {
        this.gRNO = gRNO;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public String getChequeNumber() {
        return chequeNumber;
    }

    public void setChequeNumber(String chequeNumber) {
        this.chequeNumber = chequeNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPaidFee() {
        return paidFee;
    }

    public void setPaidFee(String paidFee) {
        this.paidFee = paidFee;
    }

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
    }

    public String getAdmissionFee() {
        return admissionFee;
    }

    public void setAdmissionFee(String admissionFee) {
        this.admissionFee = admissionFee;
    }

    public String getCautionFee() {
        return cautionFee;
    }

    public void setCautionFee(String cautionFee) {
        this.cautionFee = cautionFee;
    }

    public String getPreviousFees() {
        return previousFees;
    }

    public void setPreviousFees(String previousFees) {
        this.previousFees = previousFees;
    }

    public String getTuitionFee() {
        return tuitionFee;
    }

    public void setTuitionFee(String tuitionFee) {
        this.tuitionFee = tuitionFee;
    }

    public String getTransport() {
        return transport;
    }

    public void setTransport(String transport) {
        this.transport = transport;
    }

    public String getImprestFee() {
        return imprestFee;
    }

    public void setImprestFee(String imprestFee) {
        this.imprestFee = imprestFee;
    }

    public String getLatesFee() {
        return latesFee;
    }

    public void setLatesFee(String latesFee) {
        this.latesFee = latesFee;
    }

    public String getDiscountFee() {
        return discountFee;
    }

    public void setDiscountFee(String discountFee) {
        this.discountFee = discountFee;
    }

    public String getPayPaidFees() {
        return payPaidFees;
    }

    public void setPayPaidFees(String payPaidFees) {
        this.payPaidFees = payPaidFees;
    }

    public String getCurrentOutstandingFees() {
        return currentOutstandingFees;
    }

    public void setCurrentOutstandingFees(String currentOutstandingFees) {
        this.currentOutstandingFees = currentOutstandingFees;
    }

}
