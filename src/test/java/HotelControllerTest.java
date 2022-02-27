import com.example.demo.DAO.HotelPartnerRepository;
import com.example.demo.Models.HotelPartner;
import com.example.demo.Services.HotelPartnerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;


// rely on the Spring Boot container and we want also to add or mock one of the container beans then @MockBean
@MockBeans({@MockBean(HotelPartnerServices.class), @MockBean(HotelPartnerRepository.class) })
public class HotelControllerTest {
    @Autowired
    HotelPartner hotelPartner;
    @Autowired
    HotelPartnerRepository hotelPartnerRepository;
}
