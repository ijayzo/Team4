import com.example.demo.DAO.ReviewCommentRepository;
import com.example.demo.Models.ReviewComments;
import com.example.demo.Services.ReviewServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

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

        Mockito.when(reviewCommentRepository.save(any())).thenReturn(newReviewComment());
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


}
