package com.example.hotel_reviewsandcomments.Reviews.Services;

import com.example.hotel_reviewsandcomments.Hotels.DAO.HotelPartnerRepository;
import com.example.hotel_reviewsandcomments.Hotels.DTO.CreateHotelPartnerDTO;
import com.example.hotel_reviewsandcomments.Hotels.Models.HotelPartner;
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
    private HotelPartnerRepository hotelPartnerRepository;

    @Autowired
    public ReviewsServices(ReviewCommentRepository reviewCommentRepository, HotelPartnerRepository hotelPartnerRepository) {
        this.reviewCommentRepository = reviewCommentRepository;
        this.hotelPartnerRepository = hotelPartnerRepository;
    }

    public ReviewsServices() {
    }

    public AllReviewsDTO getAllReviewsByPackageID(Integer employee_package_id){
        List<ReviewComment> allReviews = reviewCommentRepository.getAllByEmployeePackageId(employee_package_id);
        List<ReviewDTO> reviewDTOList = allReviews.stream().map(
                r -> new ReviewDTO(r.getId(), r.getEmployeeId(),r.getEmployeePackageId(),r.getReviewComments(),r.getReviewScore())).collect(Collectors.toList());
        return new AllReviewsDTO(reviewDTOList);
    }

    public int createReview(CreateReviewDTO createReviewDTO){
        ReviewComment reviewComment = new ReviewComment(createReviewDTO.getReview_comment_id(),createReviewDTO.getEmployee_id(),createReviewDTO.getEmployee_package_id(),createReviewDTO.getReview_comments(), createReviewDTO.getReview_score());
        return ReviewCommentRepository.save(reviewComment).getId();
    }
}
