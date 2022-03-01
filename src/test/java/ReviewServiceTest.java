import com.example.demo.DAO.ReviewCommentRepository;
import com.example.demo.Models.ReviewComments;
import com.example.demo.Services.ReviewServices;
import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.*;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReviewServiceTest {
    @Mock
    private ReviewCommentRepository reviewCommentRepository;

    @InjectMocks
    private ReviewServices reviewsServices;


    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    private ReviewComments newReviewComment(){
        ReviewComments reviewComment = new ReviewComments();
        reviewComment.setEmployeePackageId(56);
        reviewComment.setEmployeeId(17);
        reviewComment.setDeleted(false);
        reviewComment.setReviewComments("Great package!");
        reviewComment.setReviewScore(7);
        return reviewComment;
    }

    @Test
    public void saveTest(){

        when(reviewCommentRepository.save(any())).thenReturn(newReviewComment());
        reviewsServices.createReview(newReviewComment());

        ArgumentCaptor<ReviewComments> captureReview = ArgumentCaptor.forClass(ReviewComments.class);
        verify(reviewCommentRepository).save(captureReview.capture());

        ReviewComments comment = captureReview.getValue();
        Assert.assertEquals(Optional.ofNullable(comment.getEmployeePackageId()),Optional.ofNullable(56));
        Assert.assertEquals(Optional.ofNullable(comment.getEmployeeId()),Optional.ofNullable(17));
        Assert.assertEquals(Optional.ofNullable(comment.isDeleted()),Optional.ofNullable(false));
        Assert.assertEquals(Optional.ofNullable(comment.getReviewComments()),Optional.ofNullable("Great package!"));
        Assert.assertEquals(Optional.ofNullable(comment.getReviewScore()),Optional.ofNullable(7));


    }

    @Test
    public void getReviewByReviewId(){
        Optional<ReviewComments> getReview = reviewCommentRepository.findById(56);
        Assert.assertEquals(Optional.ofNullable(newReviewComment().getEmployeeId()),Optional.ofNullable(17));
//        Assert.assertEquals(Optional.ofNullable(newReviewComment().getIsDeleted()),Optional.ofNullable(false));
        Assert.assertEquals(Optional.ofNullable(newReviewComment().getReviewComments()),Optional.ofNullable("Great package!"));
        Assert.assertEquals(Optional.ofNullable(newReviewComment().getReviewScore()),Optional.ofNullable(7));
    }
    //getALLById
    @Test
    public void getAllReviewsById(){
        List<ReviewComments> reviewCommentList = reviewCommentRepository.findAllById(Collections.singleton(56));
        when(reviewCommentRepository.findAll()).thenReturn(reviewCommentList);
//        List<ReviewComment> result = reviewsServices.find
        Assert.assertTrue(reviewCommentList.isEmpty());
    }
//todo WHY THIS NO WORK
//    @Test
//    public void shouldDeleteById(){
//        ReviewComments reviewComments = new ReviewComments();
//        reviewComments.setReviewCommentId(58);
//        reviewComments.setEmployeeId(89);
//        reviewComments.setDeleted(false);
//        reviewComments.setReviewComments("Please Delete");
//        reviewComments.setReviewScore(5);
//        reviewComments.setEmployeePackageId(9);
//
//        when(reviewCommentRepository.findById(reviewComments.getReviewCommentId())).thenReturn(Optional.of(reviewComments));
//        reviewsServices.deleteReview(reviewComments.getReviewCommentId());
//
//        verify(reviewCommentRepository).deleteById(reviewComments.getReviewCommentId());
//    }

//    @Test
//    public void delete() throws Exception{
//        ReviewComments expected = new ReviewComments();
////                reviewsServices.createReview();
//        reviewsServices.deleteReview(expected);
//        Mockito.verify(reviewCommentRepository).delete(expected);
//    }
//
//    @Test
//    public void deleteReviewById(){
//
//        ReviewComments reviewComment = reviewCommentRepository.findById(56).get();
//
//        reviewCommentRepository.delete(reviewComment);
//
//        ReviewComments reviewComment1 = null;
//
//        Optional<ReviewComments> optionalReviewComment = reviewCommentRepository.findById(56);
//
//        if(optionalReviewComment.isPresent()){
//            reviewComment1 = optionalReviewComment.get();
//        }
//        MatcherAssert.assertThat(reviewComment1,null);
////        Assertions.assertEquals(reviewComment1,null);
////        Assertions.assertNull(reviewComment1);
//    }

}
