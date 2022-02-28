import com.example.demo.DAO.ReviewCommentRepository;
import com.example.demo.Models.ReviewComments;
import com.example.demo.Services.ReviewServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.*;

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
        Assert.assertFalse(reviewCommentList.isEmpty());
    }

    @Test
    public void deleteReviewById(){
        ReviewComments reviewComment = reviewCommentRepository.findById(56).get();
        reviewCommentRepository.delete(reviewComment);

        ReviewComments reviewComment1 = null;
        Optional<ReviewComments> optionalReviewComment = reviewCommentRepository.findById(56);

        if(optionalReviewComment.isPresent()){
            reviewComment1 = optionalReviewComment.get();
        }

        Assertions.assertEquals(reviewComment1,null);

    }

}
