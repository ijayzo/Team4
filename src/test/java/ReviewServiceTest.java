import com.example.demo.DAO.ReviewCommentRepository;
import com.example.demo.DTO.CreateReviewDTO;
import com.example.demo.Models.ReviewComments;
import com.example.demo.Services.ReviewServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ReviewServiceTest {
    @Mock
    private ReviewCommentRepository reviewCommentRepository;

    @InjectMocks
    private ReviewServices reviewsServices;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private CreateReviewDTO newReviewComment() {
        CreateReviewDTO reviewComment = new CreateReviewDTO();
        reviewComment.setEmployeeId(17);
        reviewComment.setEmployeePackageId(56);
        reviewComment.setReviewComments("Great package!");
        reviewComment.setReviewScore(7);
        return reviewComment;
    }

    // getReviewByReviewId
    @Test
    public void getReviewByReviewId() {
        Optional<ReviewComments> getReview = reviewCommentRepository.findById(56);
        Assert.assertEquals(Optional.ofNullable(newReviewComment().getEmployeeId()), Optional.ofNullable(17));
//        Assert.assertEquals(Optional.ofNullable(newReviewComment().getIsDeleted()),Optional.ofNullable(false));
        Assert.assertEquals(Optional.ofNullable(newReviewComment().getReviewComments()), Optional.ofNullable("Great package!"));
        Assert.assertEquals(Optional.ofNullable(newReviewComment().getReviewScore()), Optional.ofNullable(7));
    }

    //getALLByPackageId
    @Test
    public void getAllReviewsByPackageId() {
        List<ReviewComments> reviewCommentList = reviewCommentRepository.findAllById(Collections.singleton(56));
        when(reviewCommentRepository.findAll()).thenReturn(reviewCommentList);
//        List<ReviewComment> result = reviewsServices.find
        Assert.assertTrue(reviewCommentList.isEmpty());
    }

    // createReview
    @Test
    public void saveTest() {
        reviewsServices.createReview(newReviewComment());

        ArgumentCaptor<ReviewComments> captureReview = ArgumentCaptor.forClass(ReviewComments.class);
        verify(reviewCommentRepository).save(captureReview.capture());

        ReviewComments comment = captureReview.getValue();

        Assert.assertEquals(comment.getEmployeeId(), 17);
        Assert.assertEquals(comment.getEmployeePackageId(), 56);
        Assert.assertEquals(comment.isDeleted(), false);
        Assert.assertEquals(comment.getReviewComments(), "Great package!");
        Assert.assertEquals(comment.getReviewScore(), 7);
        Assert.assertTrue(comment.getReviewCommentId() > 0);
    }

    // delete
    @Test
    public void shouldDeleteByCommentId() {

        ReviewComments reviewComments = new ReviewComments();
        reviewComments.setReviewCommentId(5865);
        reviewComments.setEmployeeId(89);
        reviewComments.setDeleted(false);
        reviewComments.setReviewComments("Please Delete");
        reviewComments.setReviewScore(5);
        reviewComments.setEmployeePackageId(9);

        when(reviewCommentRepository.findById(reviewComments.getReviewCommentId())).thenReturn(Optional.of(reviewComments));

        reviewsServices.deleteReview(reviewComments.getReviewCommentId());

        verify(reviewCommentRepository).save(reviewComments);

        ArgumentCaptor<ReviewComments> captor = ArgumentCaptor.forClass(ReviewComments.class);
        verify(reviewCommentRepository).save(captor.capture());
        Assert.assertEquals(captor.getValue().getReviewCommentId(), 5865);
    }

}
