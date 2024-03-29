package com.anandniketanshilaj.skool360.skool360.Models.PTMCreateResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admsandroid on 10/25/2017.
 */

public class StudentDatum {
    @SerializedName("StudentID")
    @Expose
    private Integer studentID;
    @SerializedName("StudentName")
    @Expose
    private String studentName;
    @SerializedName("GRNO")
    @Expose
    private String gRNO;

    public Integer getStudentID() {
        return studentID;
    }

    public void setStudentID(Integer studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGRNO() {
        return gRNO;
    }

    public void setGRNO(String gRNO) {
        this.gRNO = gRNO;
    }

}
