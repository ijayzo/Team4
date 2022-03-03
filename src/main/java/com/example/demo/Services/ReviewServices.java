package com.example.demo.Services;

import com.example.demo.DAO.ReviewCommentRepository;
import com.example.demo.DTO.AllReviewsDTO;
import com.example.demo.DTO.CreateReviewDTO;
import com.example.demo.DTO.DeleteReviewCommentDTO;
import com.example.demo.DTO.ReviewDTO;
import com.example.demo.Exceptions.ReviewCommentDoesNotExist;
import com.example.demo.Models.ReviewComments;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Review Services
 * With the correct authorization, allows one to get view all reviews by package ID, create a review, get a review by
 * review comment ID, and/or delete a review by review comment ID.
 * Mainly seen on front end.
 */
@Service
public class ReviewServices {

    private ReviewCommentRepository reviewCommentRepository;

    @Autowired
    public ReviewServices(ReviewCommentRepository reviewCommentRepository) {
        this.reviewCommentRepository = reviewCommentRepository;
    }

    public ReviewServices() {
    }

    public AllReviewsDTO getAllReviewsByPackageID(Integer employee_package_id){
        List<ReviewComments> allReviews = reviewCommentRepository.getAllByEmployeePackageId(employee_package_id);
        List<ReviewDTO> reviewDTOList = allReviews.stream().map(
                r -> new ReviewDTO(r.getReviewCommentId(), r.getEmployeeId(),r.getEmployeePackageId(),r.getReviewComments(),r.getReviewScore())).collect(Collectors.toList());
        return new AllReviewsDTO(reviewDTOList);
    }

    public void createReview(CreateReviewDTO createReviewComment) {
        int generateID = Integer.parseInt(RandomStringUtils.randomNumeric(5));
        ReviewComments reviewComment = new ReviewComments();
        reviewComment.setReviewCommentId(generateID);
        reviewComment.setEmployeeId(createReviewComment.getEmployeeId());
        reviewComment.setEmployeePackageId(createReviewComment.getEmployeePackageId());
        reviewComment.setReviewComments(createReviewComment.getReviewComments());
        reviewComment.setReviewScore(createReviewComment.getReviewScore());
        reviewComment.setEmployeePackageId(createReviewComment.getEmployeePackageId());
        reviewComment.setDeleted(false);

        reviewCommentRepository.save(reviewComment);
    }
    //getReviewByReviewId
    public ReviewComments getReviewById(int review_comment_id){
        return reviewCommentRepository.findById(review_comment_id).isPresent()  ?(reviewCommentRepository.findById(review_comment_id)).get() : null;
    }

    public void deleteReview(int review_comment_id){
        ReviewComments reviewComment = reviewCommentRepository.findById(review_comment_id).isPresent()  ?(reviewCommentRepository.findById(review_comment_id)).get() : null;
        if(reviewComment != null){
            reviewComment.setDeleted(true);
            reviewCommentRepository.save(reviewComment);
        }else {
            throw new ReviewCommentDoesNotExist();
        }
    }


}
