package com.example.hotel_reviewsandcomments.Reviews.Controllers;

import com.example.hotel_reviewsandcomments.Hotels.DTO.CreateHotelPartnerDTO;
import com.example.hotel_reviewsandcomments.Reviews.DTO.AllReviewsDTO;
import com.example.hotel_reviewsandcomments.Reviews.DTO.CreateReviewDTO;
import com.example.hotel_reviewsandcomments.Reviews.Services.ReviewsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/reviews")
public class ReviewAndCommentsController {
    private ReviewsServices reviewsServices;

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
    //post new review
    @PostMapping("/new")
    public ResponseEntity<?> createReview(@RequestBody CreateReviewDTO createReview) throws URISyntaxException{
        reviewsServices.createReview(createReview);
        return ResponseEntity.created(new URI("http://localhost:" + port + "/reviews/new/created")).build();
    }
}
