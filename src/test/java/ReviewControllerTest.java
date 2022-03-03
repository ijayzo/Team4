//import com.example.demo.DAO.ReviewCommentRepository;
//import com.example.demo.Models.ReviewComments;
//import com.example.demo.Services.ReviewServices;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//// rely on the Spring Boot container and we want also to add or mock one of the container beans then @MockBean
//@ActiveProfiles(value = "test")
//@WebMvcTest(ReviewAndCommentsController.class)
//public class ReviewControllerTest {
//    @MockBean
//    private ReviewComments reviewComment;
//    @MockBean
//    private ReviewServices testRevService;
//
//    @MockBean
//    private ReviewCommentRepository testReviewDAO;
//
//    @InjectMocks
//    private ReviewAndCommentsController reviewAndCommentsController;
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Before
//    public void setUp() throws Exception{
//        mockMvc = MockMvcBuilders.standaloneSetup(reviewAndCommentsController)
//                .build();
//    }
//
//    private ReviewComments createTestReview(){
//        ReviewComments newReview = new ReviewComments();
//        newReview.setReviewCommentId(9);
//        newReview.setEmployeeId(8);
//        newReview.setEmployeePackageId(6);
//        newReview.setDeleted(false);
//        newReview.setReviewComments("testReview");
//        newReview.setReviewScore(3);
//        return newReview;
//    }
//
////    @Profile("test")
////    @Configuration
////    public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
////        @Override
////        public void configure(WebSecurity web) throws Exception      {
////            web.ignoring().antMatchers("/**");
////        }
////    }
//
//    @Test
//    public void shouldReturnModel() throws Exception{
//        int id = 10;
//        ReviewComments testRev = new ReviewComments();
//        testRev = createTestReview();
//
//        when(testRevService.getReviewById(10)).thenReturn(createTestReview());
//
//        mockMvc.perform(get("/reviews/rev-by-package-id/{id}", id))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.id").value(id));
//    }
//    //new try delete,
//    //TODO change the url
//    @Test
//    public void delete() throws Exception{
//        Mockito.when(testReviewDAO.findById(createTestReview().getReviewCommentId())).thenReturn(Optional.of(createTestReview()));
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/reviews/delete")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//    }
//    // dont use
//    //    @Test
////    public void deleteReview() throws Exception{
////        Mockito.when(testRevService.deleteReview(9)).thenReturn("Success!");
////        mockMvc.perform(MockMvcRequestBuilders.delete("/deleteReviews",9))
////                .andExpect(status().isOk());
////    }
//
//    //TODO
////    @Test
////    public void shouldDeleteReviewById(){
////        ReviewComments reviewComment = new ReviewComments();
////        reviewComment.setReviewCommentId(15);
////        reviewComment.setEmployeeId(16);
////        reviewComment.setEmployeePackageId(7);
////        reviewComment.setDeleted(false);
////        reviewComment.setReviewComments("nice");
////        reviewComment.setReviewScore(5);
////
////        when(testReviewDAO.findById(reviewComment.getReviewCommentId())).thenReturn(Optional.of(reviewComment));
////
//////        testRevService.deleteReview(reviewComment.getReviewCommentId());
////        verify(testReviewDAO).deleteById(reviewComment.getReviewCommentId());
////    }
//    //Patch
//    //TODO
////    @Test
////    public void updateReview(){
////        ReviewComments reviewComment = new ReviewComments();
////        reviewComment.setReviewCommentId(15);
////        reviewComment.setEmployeeId(16);
////        reviewComment.setEmployeePackageId(7);
////        reviewComment.setDeleted(false);
////        reviewComment.setReviewComments("nice");
////        reviewComment.setReviewScore(5);
////
////        ReviewComments updateReviewComment = new ReviewComments();
////        reviewComment.setReviewCommentId(17);
////        reviewComment.setEmployeeId(62);
////        reviewComment.setEmployeePackageId(73);
////        reviewComment.setDeleted(false);
////        reviewComment.setReviewComments("nicer");
////        reviewComment.setReviewScore(6);
////
////        given(testReviewDAO.findById(anyInt())).willReturn(Optional.ofNullable(null));
////
////        testRevService.createReview(reviewComment.getReviewCommentId(),updateReview());
////    }
//
//    //Post
//    //TODO
////    @Test
////    public void createReview() throws Exception{
////        ReviewComments reviewComment = new ReviewComments();
////        given(testRevService.createReview(reviewComment)).willReturn(reviewComment);
////        mockMvc.perform(post("/reviews/new")
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content(JSONUtil.toJson(reviewComment)))
////                .andExpect(status().isCreated());
//////                .andExpect(jsonPath("$.id",is(reviewComment.getId())));
////    }
//}
