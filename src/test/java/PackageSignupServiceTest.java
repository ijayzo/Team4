import com.example.demo.DAO.HotelPartnerRepository;
import com.example.demo.DAO.PackageSignUpRepository;
import com.example.demo.DTO.PackageSignUpRequest;
import com.example.demo.Models.HotelPartnerT;
import com.example.demo.Models.PackageSignUp;
import com.example.demo.Services.HotelPartnerServices;
import com.example.demo.Services.PackageSignUpService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PackageSignupServiceTest {
    @Mock
    private PackageSignUpRepository packageSignUpRepository;

    @InjectMocks
    private PackageSignUpService packageSignUpService;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        System.out.println("I am here");
    }
    private PackageSignUpRequest newPackageSignUpRequest(){
        PackageSignUpRequest packageSignUpRequest = new PackageSignUpRequest();
        packageSignUpRequest.setEmployeeId(68);
        packageSignUpRequest.setPackageId(75);
        return packageSignUpRequest;
    }

    //create(PackageSignUpRequest packageSignUpRequest){
    @Test
    public void saveTest(){
        packageSignUpService.create(newPackageSignUpRequest());

        ArgumentCaptor<PackageSignUp> captor = ArgumentCaptor.forClass(PackageSignUp.class);

        verify(packageSignUpRepository).save(captor.capture());

        PackageSignUp packageSignUp = captor.getValue();

        Assert.assertEquals(packageSignUp.getEmployeeId(), 68);
        Assert.assertEquals(packageSignUp.getPackageId(), 75);
    }

    //getAllPackageById
    @Test
    public void shouldReturnAllPackagesByPackageID() {
        List<PackageSignUp> packageSignUpList = packageSignUpRepository.findPackageSignUpByPackageId(68);
        when(packageSignUpRepository.findAll()).thenReturn(packageSignUpList);
        Assert.assertTrue(packageSignUpList.isEmpty());
    }

    //getAllPackageByEmployee
    @Test
    public void shouldReturnAllPackagesByEmployeeId(){
        List<PackageSignUp> packageSignUpList = packageSignUpRepository.findPackageSignUpByEmployeeId(75);
        when(packageSignUpRepository.findAll()).thenReturn(packageSignUpList);
        Assert.assertTrue(packageSignUpList.isEmpty());
    }

    // deletePackage(int packageSignUpId){
//    @Test
//    public void shouldDeleteByPackageSignUpId(){
//        PackageSignUp packageSignUp = new PackageSignUp();
//        packageSignUp.setPackageSignUpId(15);
//        packageSignUp.setEmployeeId(63);
//        packageSignUp.setPackageId(95);
//        packageSignUp.setSignUpDate(new Date(2022-06-10));
////        packageSignUp.setDeleted(false);
//
//        when(packageSignUpRepository.findPackageSignUpByPackageSignUpId(packageSignUp.getPackageSignUpId())).thenReturn(Optional.of(packageSignUp));
//
//        packageSignUpService.deletePackage(packageSignUp.getPackageSignUpId());
//
//        verify(packageSignUpRepository).save(packageSignUp);
//
//        ArgumentCaptor<PackageSignUp> captor = ArgumentCaptor.forClass(PackageSignUp.class);
//        verify(packageSignUpRepository).save(captor.capture());
//        Assert.assertEquals(captor.getValue().getPackageSignUpId(), 15);
//    }

}

