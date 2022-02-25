package com.example.hotel_reviewsandcomments.Review;

import com.example.hotel_reviewsandcomments.Reviews.DAO.ReviewCommentRepository;
import com.example.hotel_reviewsandcomments.Reviews.DTO.CreateReviewDTO;
import com.example.hotel_reviewsandcomments.Reviews.Models.ReviewComment;
import com.example.hotel_reviewsandcomments.Reviews.Services.ReviewsServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import static org.mockito.ArgumentMatchers.*;

// doesn’t need any dependencies from the Spring Boot container, the Mockito‘s @Mock
@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    @Mock
    private ReviewCommentRepository reviewCommentRepository;

    @InjectMocks
    private ReviewsServices reviewsServices;


    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        System.out.println("I am here");
    }
    private ReviewComment newReviewComment(){
        ReviewComment reviewComment = new ReviewComment();
        reviewComment.setId(56);
        reviewComment.setEmployeeId(17);
        reviewComment.setIsDeleted(false);
        reviewComment.setReviewComments("Great package!");
        reviewComment.setReviewScore(7);
        return reviewComment;
    }

    @Test
    public void saveTest(){

        Mockito.when(reviewCommentRepository.save(any())).thenReturn(newReviewComment());
        reviewsServices.createReview(newReviewComment());

        ArgumentCaptor<ReviewComment> captureReview = ArgumentCaptor.forClass(ReviewComment.class);
        verify(reviewCommentRepository).save(captureReview.capture());

        ReviewComment comment = captureReview.getValue();
        Assert.assertEquals(Optional.ofNullable(comment.getId()),Optional.ofNullable(56));
        Assert.assertEquals(Optional.ofNullable(comment.getEmployeeId()),Optional.ofNullable(17));
        Assert.assertEquals(Optional.ofNullable(comment.getIsDeleted()),Optional.ofNullable(false));
        Assert.assertEquals(Optional.ofNullable(comment.getReviewComments()),Optional.ofNullable("Great package!"));
        Assert.assertEquals(Optional.ofNullable(comment.getReviewScore()),Optional.ofNullable(7));


    }

}
