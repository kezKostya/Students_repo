package com.students.domain;

import com.students.domain.common.BaseEntity;
import com.students.domain.common.BaseHistoryEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by kkolesnichenko on 11/13/2015.
 */
public class HomeTask extends BaseHistoryEntity {

    private Course course;

    private Teacher reviewer;

    private Student student;

    private BigDecimal score;

    private Date dueDate;

    private Date deliveryDate;

    private Date reviewDate;

    private String reviewNotes;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Teacher getReviewer() {
        return reviewer;
    }

    public void setReviewer(Teacher reviewer) {
        this.reviewer = reviewer;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getReviewNotes() {
        return reviewNotes;
    }

    public void setReviewNotes(String reviewNotes) {
        this.reviewNotes = reviewNotes;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
