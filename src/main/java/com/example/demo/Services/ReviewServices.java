package com.example.demo.Services;

import com.example.demo.DAO.ReviewCommentRepository;
import com.example.demo.DTO.AllReviewsDTO;
import com.example.demo.DTO.CreateReviewDTO;
import com.example.demo.DTO.ReviewDTO;
import com.example.demo.Models.ReviewComments;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        reviewComment.setReviewComments(createReviewComment.getReviewComments());
        reviewComment.setReviewScore(createReviewComment.getReviewScore());
        reviewComment.setEmployeePackageId(createReviewComment.getEmployeePackageId());
        reviewComment.setDeleted(false);

        reviewCommentRepository.save(reviewComment);
    }

    public ReviewComments getReviewById(int review_comment_id){
        return reviewCommentRepository.findById(review_comment_id).isPresent()  ?(reviewCommentRepository.findById(review_comment_id)).get() : null;
    }

    public void deleteReview(ReviewComments reviewComment){
        reviewComment.setDeleted(true);
       reviewCommentRepository.save(reviewComment);
    }

    public void save(CreateReviewDTO createReviewTest) {
    }
}
