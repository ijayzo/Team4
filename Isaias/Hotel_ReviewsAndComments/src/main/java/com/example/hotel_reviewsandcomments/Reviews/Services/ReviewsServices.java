package com.example.hotel_reviewsandcomments.Reviews.Services;

import com.example.hotel_reviewsandcomments.Hotels.DAO.HotelPartnerRepository;
import com.example.hotel_reviewsandcomments.Reviews.DAO.ReviewCommentRepository;
import com.example.hotel_reviewsandcomments.Reviews.DTO.AllReviewsDTO;
import com.example.hotel_reviewsandcomments.Reviews.DTO.CreateReviewDTO;
import com.example.hotel_reviewsandcomments.Reviews.DTO.ReviewDTO;
import com.example.hotel_reviewsandcomments.Reviews.Models.ReviewComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewsServices {
    private ReviewCommentRepository reviewCommentRepository;

    @Autowired
    public ReviewsServices(ReviewCommentRepository reviewCommentRepository) {
        this.reviewCommentRepository = reviewCommentRepository;
    }

    public ReviewsServices() {
    }

    public AllReviewsDTO getAllReviewsByPackageID(Integer employee_package_id){
        List<ReviewComment> allReviews = reviewCommentRepository.getAllByEmployeePackageId(employee_package_id);
        List<ReviewDTO> reviewDTOList = allReviews.stream().map(
                r -> new ReviewDTO(r.getId(), r.getEmployeeId(),r.getEmployeePackageId(),r.getReviewComments(),r.getReviewScore())).collect(Collectors.toList());
        return new AllReviewsDTO(reviewDTOList);
    }

    public void createReview(ReviewComment reviewComment) {
        reviewCommentRepository.save(reviewComment);
    }

    public ReviewComment getReviewById(int review_comment_id){
        return reviewCommentRepository.findById(review_comment_id).isPresent()  ?(reviewCommentRepository.findById(review_comment_id)).get() : null;
    }

    public void deleteReview(int id){
        reviewCommentRepository.deleteById(id);
    }

    public void save(CreateReviewDTO createReviewTest) {
    }
}
