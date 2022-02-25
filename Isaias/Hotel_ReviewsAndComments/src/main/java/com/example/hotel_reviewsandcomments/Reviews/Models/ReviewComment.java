package com.example.hotel_reviewsandcomments.Reviews.Models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "review_comments")
public class ReviewComment {
    @Id
    @Column(name = "review_comment_id", nullable = false)
    private Integer id;

    @Column(name = "employee_id", nullable = false)
    private Integer employeeId;

    @Column(name = "employee_package_id", nullable = false)
    private Integer employeePackageId;

    @Column(name = "is_deleted", nullable = false)
    private Boolean isDeleted = false;

    @Column(name = "review_comments")
    private String reviewComments;

    @Column(name = "review_score", nullable = false)
    private Integer reviewScore;

    public ReviewComment(int review_comment_id, int employee_id, int employee_package_id, String review_comments, int review_score) {
    }

    public Integer getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(Integer reviewScore) {
        this.reviewScore = reviewScore;
    }

    public String getReviewComments() {
        return reviewComments;
    }

    public void setReviewComments(String reviewComments) {
        this.reviewComments = reviewComments;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Integer getEmployeePackageId() {
        return employeePackageId;
    }

    public void setEmployeePackageId(Integer employeePackageId) {
        this.employeePackageId = employeePackageId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void save(ReviewComment reviewComment) {
    }
}