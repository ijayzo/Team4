package com.example.hotel_reviewsandcomments.Reviews.Controllers;

import com.example.hotel_reviewsandcomments.Hotels.DTO.CreateHotelPartnerDTO;
import com.example.hotel_reviewsandcomments.Reviews.DTO.AllReviewsDTO;
import com.example.hotel_reviewsandcomments.Reviews.DTO.CreateReviewDTO;
import com.example.hotel_reviewsandcomments.Reviews.Models.ReviewComment;
import com.example.hotel_reviewsandcomments.Reviews.Services.ReviewsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/reviews")
public class ReviewAndCommentsController {
    private ReviewsServices reviewsServices;
    int port;

    @Autowired
    public ReviewAndCommentsController(ReviewsServices reviewsServices) {
        this.reviewsServices = reviewsServices;
    }

    @GetMapping("/rev-by-package-id/{id}")
    public ResponseEntity<?> getAllReviewsByPackageId(@PathVariable Integer employee_package_id){
        AllReviewsDTO allReviews = reviewsServices.getAllReviewsByPackageID(employee_package_id);

        if(allReviews.getReviewDTOList().isEmpty()){
            return ResponseEntity.status(404).build();
        }
        return ResponseEntity.ok(allReviews);
    }

    @PostMapping("/new")
    public ResponseEntity createReview(@RequestBody ReviewComment newReviewComment){
        reviewsServices.createReview(newReviewComment);
        return ResponseEntity.accepted().build();
    }

   //update review
    @PatchMapping("/update")
    public ResponseEntity updateReview(@RequestBody ReviewComment newReviewComment){
        reviewsServices.createReview(newReviewComment);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/deleteReview/{review_comment_id}")
    public ResponseEntity deleteReview(@PathVariable int review_comment_id){
        ReviewComment reviewComment= reviewsServices.getReviewById(review_comment_id);
        if (reviewComment==null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        reviewComment.setIsDeleted(true);
        reviewComment.save(reviewComment);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
