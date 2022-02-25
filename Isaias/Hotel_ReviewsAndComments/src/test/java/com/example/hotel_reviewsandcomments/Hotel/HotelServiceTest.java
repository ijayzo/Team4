package com.example.hotel_reviewsandcomments.Hotel;

import com.example.hotel_reviewsandcomments.Hotels.DAO.HotelPartnerRepository;
import com.example.hotel_reviewsandcomments.Hotels.Models.HotelPartner;
import com.example.hotel_reviewsandcomments.Hotels.Services.HotelPartnerServices;
import com.example.hotel_reviewsandcomments.Reviews.DAO.ReviewCommentRepository;
import com.example.hotel_reviewsandcomments.Reviews.Models.ReviewComment;
import com.example.hotel_reviewsandcomments.Reviews.Services.ReviewsServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

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
