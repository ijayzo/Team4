package com.example.hotel_reviewsandcomments.Review;

import com.example.hotel_reviewsandcomments.Reviews.Controllers.ReviewAndCommentsController;
import com.example.hotel_reviewsandcomments.Reviews.DAO.ReviewCommentRepository;
import com.example.hotel_reviewsandcomments.Reviews.Models.ReviewComment;
import com.example.hotel_reviewsandcomments.Reviews.Services.ReviewsServices;
import net.minidev.json.JSONUtil;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


// rely on the Spring Boot container and we want also to add or mock one of the container beans then @MockBean
@ActiveProfiles(value = "test")
@WebMvcTest(ReviewAndCommentsController.class)
public class ReviewControllerTest {
    @MockBean
    private ReviewComment reviewComment;
    @MockBean
    private ReviewsServices testRevService;

    @MockBean
    private ReviewCommentRepository testReviewDAO;

    @InjectMocks
    private ReviewAndCommentsController reviewAndCommentsController;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(reviewAndCommentsController)
                .build();
    }

    private ReviewComment createTestReview(){
        ReviewComment newReview = new ReviewComment();
        newReview.setId(9);
        newReview.setEmployeeId(8);
        newReview.setEmployeePackageId(6);
        newReview.setIsDeleted(false);
        newReview.setReviewComments("testReview");
        newReview.setReviewScore(3);
        return newReview;
    }

//    @Profile("test")
//    @Configuration
//    public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
//        @Override
//        public void configure(WebSecurity web) throws Exception      {
//            web.ignoring().antMatchers("/**");
//        }
//    }

    @Test
    public void shouldReturnModel() throws Exception{
        int id = 10;
        ReviewComment testRev = new ReviewComment();
        testRev = createTestReview();

        when(testRevService.getReviewById(10)).thenReturn(createTestReview());

        mockMvc.perform(get("/reviews/rev-by-package-id/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    //    @Test
//    public void deleteReview() throws Exception{
//        Mockito.when(testRevService.deleteReview(9)).thenReturn("Success!");
//        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteReviews",9))
//                .andExpect(status().isOk());
//    }
    @Test
    public void shouldDeleteReviewById(){
        ReviewComment reviewComment = new ReviewComment();
        reviewComment.setId(15);
        reviewComment.setEmployeeId(16);
        reviewComment.setEmployeePackageId(7);
        reviewComment.setIsDeleted(false);
        reviewComment.setReviewComments("nice");
        reviewComment.setReviewScore(5);

        when(testReviewDAO.findById(reviewComment.getId())).thenReturn(Optional.of(reviewComment));

        testRevService.deleteReview(reviewComment.getId());
        verify(testReviewDAO).deleteById(reviewComment.getId());
    }
    //Patch
    @Test
    public void updateReview(){
        ReviewComment reviewComment = new ReviewComment();
        reviewComment.setId(15);
        reviewComment.setEmployeeId(16);
        reviewComment.setEmployeePackageId(7);
        reviewComment.setIsDeleted(false);
        reviewComment.setReviewComments("nice");
        reviewComment.setReviewScore(5);

        ReviewComment updateReviewComment = new ReviewComment();
        reviewComment.setId(17);
        reviewComment.setEmployeeId(62);
        reviewComment.setEmployeePackageId(73);
        reviewComment.setIsDeleted(false);
        reviewComment.setReviewComments("nicer");
        reviewComment.setReviewScore(6);

        given(testReviewDAO.findById(anyInt())).willReturn(Optional.ofNullable(null));

        testRevService.createReview(reviewComment);


    }

    //Post
    @Test
    public void createReview() throws Exception{
        ReviewComment reviewComment = new ReviewComment();
//        given(testRevService.createReview(reviewComment)).willReturn(reviewComment);
        mockMvc.perform(post("/reviews/new")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(reviewComment)));
//                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.id",is(reviewComment.getId())));


    }
}
