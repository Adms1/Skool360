package com.anandniketanshilaj.skool360.skool360.Models.HomeWorkResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by admsandroid on 12/12/2017.
 */

public class HomeWorkInfo {
    @SerializedName("Subject")
    @Expose
    private String subject;
    @SerializedName("HomeWork")
    @Expose
    private String homeWork;
    @SerializedName("ChapterName")
    @Expose
    private String chapterName;
    @SerializedName("Objective")
    @Expose
    private String objective;
    @SerializedName("AssessmentQue")
    @Expose
    private String assessmentQue;
    @SerializedName("Font")
    @Expose
    private String font;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHomeWork() {
        return homeWork;
    }

    public void setHomeWork(String homeWork) {
        this.homeWork = homeWork;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getAssessmentQue() {
        return assessmentQue;
    }

    public void setAssessmentQue(String assessmentQue) {
        this.assessmentQue = assessmentQue;
    }

    public String getFont() {
        return font;
    }

    public void setFont(String font) {
        this.font = font;
    }

}
