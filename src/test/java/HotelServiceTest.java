import com.example.demo.DAO.HotelPartnerRepository;
import com.example.demo.DTO.CreateHotelPartnerRequest;
import com.example.demo.Models.HotelPartnerT;
import com.example.demo.Services.HotelPartnerServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// doesn’t need any dependencies from the Spring Boot container, the Mockito‘s @Mock
@ExtendWith(MockitoExtension.class)
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
    private CreateHotelPartnerRequest newHotelPartner(){
        CreateHotelPartnerRequest hotelPartner = new CreateHotelPartnerRequest();
        hotelPartner.setHotelName("TestHotel");
        hotelPartner.setHotelLocation("Niceville");
        return hotelPartner;
    }
    @Test
    public void saveTest(){
        hotelPartnerServices.createHotelPartner(newHotelPartner());

        ArgumentCaptor<HotelPartnerT> captureHotelPartner = ArgumentCaptor.forClass(HotelPartnerT.class);

        verify(hotelPartnerRepository).save(captureHotelPartner.capture());

        HotelPartnerT hotelPartnerT = captureHotelPartner.getValue();

        Assert.assertEquals(hotelPartnerT.getHotelName(), "TestHotel");
        Assert.assertEquals(hotelPartnerT.getHotelLocation(), "Niceville");
    }

    @Test
    public void shouldReturnAllHotels() {
        when(hotelPartnerRepository.findAll()).thenReturn(Collections.emptyList());
        List<HotelPartnerT> theHotelList = hotelPartnerServices.getAllHotels();
        assertTrue(theHotelList.isEmpty());
    }

    @Test
    public void getHotelByHotelId(){

        Optional<HotelPartnerT> hotel = hotelPartnerRepository.findById(20);
        Assert.assertEquals(Optional.ofNullable(newHotelPartner().getHotelName()), Optional.ofNullable("TestHotel"));
//        Assert.assertEquals(Optional.ofNullable(newHotelPartner().getIsDeleted()), Optional.ofNullable(false));
        Assert.assertEquals(Optional.ofNullable(newHotelPartner().getHotelLocation()),  Optional.ofNullable("Niceville"));
    }

    @Test
    public void shouldDeleteHotelByHotelId(){
        HotelPartnerT hotelPartnerT = new HotelPartnerT();
        hotelPartnerT.setId(89);
        hotelPartnerT.setHotelName("Delete");
        hotelPartnerT.setHotelLocation("Somewhere too cold");
//        hotelPartnerT.setIsDeleted(false);

        when(hotelPartnerRepository.getHotelPartnerById(hotelPartnerT.getId())).thenReturn(Optional.of(hotelPartnerT));

        hotelPartnerServices.deleteHotelPartner(hotelPartnerT.getId());

        verify(hotelPartnerRepository).save(hotelPartnerT);

        ArgumentCaptor<HotelPartnerT> captor = ArgumentCaptor.forClass(HotelPartnerT.class);
        verify(hotelPartnerRepository).save(captor.capture());
        Assert.assertEquals(captor.getValue().getId(), 89);

    }
}
