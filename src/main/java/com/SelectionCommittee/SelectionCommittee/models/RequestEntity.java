package com.SelectionCommittee.SelectionCommittee.models;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "request", schema = "admissions")
public class RequestEntity {
    @Basic
    @Id
    @Column(name = "id")
    private Long id;
    @Basic
    @Column(name = "status")
    private String status;
    @Basic
    @Column(name = "faculties_id")
    private int facultiesId;
    @Basic
    @Column(name = "applicant_id")
    private int applicantId;
    @Basic
    @Column(name = "main_subject")
    private int mainSubject;
    @Basic
    @Column(name = "second_subject")
    private int secondSubject;
    @Basic
    @Column(name = "sub_subject")
    private int subSubject;
    @Basic
    @Column(name = "rating_score")
    private int ratingScore;
    @Basic
    @Column(name = "average_attestation_score")
    private double averageAttestationScore;
    @Basic
    @Column(name = "publish_time")
    private Time publishTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFacultiesId() {
        return facultiesId;
    }

    public void setFacultiesId(int facultiesId) {
        this.facultiesId = facultiesId;
    }

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public int getMainSubject() {
        return mainSubject;
    }

    public void setMainSubject(int mainSubject) {
        this.mainSubject = mainSubject;
    }

    public int getSecondSubject() {
        return secondSubject;
    }

    public void setSecondSubject(int secondSubject) {
        this.secondSubject = secondSubject;
    }

    public int getSubSubject() {
        return subSubject;
    }

    public void setSubSubject(int subSubject) {
        this.subSubject = subSubject;
    }

    public int getRatingScore() {
        return ratingScore;
    }

    public void setRatingScore(int ratingScore) {
        this.ratingScore = ratingScore;
    }

    public double getAverageAttestationScore() {
        return averageAttestationScore;
    }

    public void setAverageAttestationScore(double averageAttestationScore) {
        this.averageAttestationScore = averageAttestationScore;
    }

    public Time getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Time publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestEntity that = (RequestEntity) o;
        return facultiesId == that.facultiesId && applicantId == that.applicantId && mainSubject == that.mainSubject && secondSubject == that.secondSubject && subSubject == that.subSubject && ratingScore == that.ratingScore && Double.compare(that.averageAttestationScore, averageAttestationScore) == 0 && Objects.equals(id, that.id) && Objects.equals(status, that.status) && Objects.equals(publishTime, that.publishTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, facultiesId, applicantId, mainSubject, secondSubject, subSubject, ratingScore, averageAttestationScore, publishTime);
    }
}
