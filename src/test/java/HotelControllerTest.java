
import com.example.demo.Controllers.HotelPartnerController;
import com.example.demo.DAO.HotelPartnerRepository;
import com.example.demo.Models.HotelPartnerT;
import com.example.demo.Services.HotelPartnerServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// rely on the Spring Boot container and we want also to add or mock one of the container beans then @MockBean
@ActiveProfiles(value = "test")
@WebMvcTest(HotelPartnerController.class)
public class HotelControllerTest {
    @Mock
    private HotelPartnerT hotelPartner;
    @MockBean
    private HotelPartnerRepository hotelPartnerRepository;
    @MockBean
    private HotelPartnerServices hotelPartnerServices;
    @InjectMocks
    private HotelPartnerController hotelPartnerController;
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception{
        mockMvc = MockMvcBuilders.standaloneSetup(hotelPartnerController)
                .build();
    }

    private HotelPartnerT createHotel(){
        HotelPartnerT testHotel = new HotelPartnerT();
        testHotel.setId(8);
        testHotel.setHotelName("TestHotel");
        testHotel.setHotelLocation("NewLocation");
        testHotel.setIsDeleted(false);
        return testHotel;
    }

    @Test
    public void shouldReturnModel() throws Exception{
        int id = 10;
        HotelPartnerT testHotel = new HotelPartnerT();
        testHotel = createHotel();

        when(hotelPartnerServices.getHotelByHotelId(10)).thenReturn(createHotel());

        mockMvc.perform(get("/hotel/id/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    public void shouldGetAll() throws Exception{
        List<HotelPartnerT> hotelPartnerList = hotelPartnerRepository.findAll();
        when(hotelPartnerServices.getAllHotels()).thenReturn(hotelPartnerList);
        mockMvc.perform(get("/hotel/all"))
                .andExpect(status().isOk());
    }
    //    @Test
//    public void createHotel() throws Exception{
//        HotelPartner hotelPartner = new HotelPartner();
//        given(hotelPartnerServices.createHotelPartner(hotelPartner).retu
//        mockMvc.perform(post("/hotel/new")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(JSONUtil.toJson(hotelPartner)))
//                .andExpect(status().isCreated());
//    }
    @Test
    public void shouldCreateHotel(){
        HotelPartnerT hotelPartner = new HotelPartnerT();
        hotelPartner.setId(15);
        hotelPartner.setHotelName("Testing");
        hotelPartner.setHotelLocation("TestLocation");
        hotelPartner.setIsDeleted(false);

        when(hotelPartnerRepository.save(any(HotelPartnerT.class))).thenReturn(hotelPartner);

        HotelPartnerT savedHotel = hotelPartnerRepository.save(hotelPartner);

        Assert.assertFalse(savedHotel.getHotelName().isEmpty());

//        assertThat(savedHotel.getHotelName()).isNotNull();
    }
}
   