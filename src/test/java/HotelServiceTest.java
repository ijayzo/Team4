import com.example.demo.DAO.HotelPartnerRepository;
import com.example.demo.Models.HotelPartner;
import com.example.demo.Services.HotelPartnerServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

public class HotelServiceTest {

    @Mock
    private HotelPartnerRepository hotelPartnerRepository;

    @InjectMocks
    private HotelPartnerServices hotelPartnerServices;


    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        System.out.println("I am here");
    }
    private HotelPartner newHotelPartner(){
        HotelPartner hotelPartner = new HotelPartner();
        hotelPartner.setId(20);
        hotelPartner.setHotelName("TestHotel");
        hotelPartner.setIsDeleted(false);
        hotelPartner.setHotelLocation("Niceville");
        return hotelPartner;
    }

    @Test
    public void saveTest(){

        Mockito.when(hotelPartnerRepository.save(any())).thenReturn(newHotelPartner());
        hotelPartnerServices.createHotelPartner(newHotelPartner());

        ArgumentCaptor<HotelPartner> captureHotel = ArgumentCaptor.forClass(HotelPartner.class);
        verify(hotelPartnerRepository).save(captureHotel.capture());

        HotelPartner hotel = captureHotel.getValue();
        Assert.assertEquals(Optional.ofNullable(hotel.getId()),Optional.ofNullable(20));
        Assert.assertEquals(Optional.ofNullable(hotel.getHotelName()),Optional.ofNullable("TestHotel"));
        Assert.assertEquals(Optional.ofNullable(hotel.getIsDeleted()),Optional.ofNullable(false));
        Assert.assertEquals(Optional.ofNullable(hotel.getHotelLocation()),Optional.ofNullable("Niceville"));


    }
}
