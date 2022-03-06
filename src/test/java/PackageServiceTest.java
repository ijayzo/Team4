import com.example.demo.DAO.EmployeePackageDataJPA;
import com.example.demo.DTO.PackageCategory;
import com.example.demo.DTO.PackageStatus;
import com.example.demo.DTO.createPackageRequest;
import com.example.demo.Models.EmployeePackages;
import com.example.demo.Services.PackageServices;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class PackageServiceTest {
    @Mock
    private EmployeePackageDataJPA packageRepo;
    @InjectMocks
    private PackageServices packagesServices;
    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    private EmployeePackages newPackage(){
        EmployeePackages employeePackages = new EmployeePackages();
        employeePackages.setPackageCategory(PackageCategory.BEACH);
        employeePackages.setTravelDate(Date.valueOf("2022-01-01"));
        employeePackages.setTravelDestination("Greece");
        employeePackages.setPackageDays(15);
        employeePackages.setPackageStatus(PackageStatus.PUBLISHED);
        employeePackages.setPackageCost(1500);
        employeePackages.setPackageDescription("Flight and stay");
        employeePackages.setTotalPackageSignUp(23);
        employeePackages.setDeleted(false);
        return employeePackages;
    }

    private createPackageRequest newPackageRequest(){
       createPackageRequest employeePackages = new createPackageRequest();
        employeePackages.setPackageCategory(PackageCategory.BEACH);
        employeePackages.setTravelDate(Date.valueOf("2022-01-01"));
        employeePackages.setTravelDestination("Greece");
        employeePackages.setPackageDays(15);
        employeePackages.setPackageStatus(PackageStatus.PUBLISHED);
        employeePackages.setPackageCost(1500);
        employeePackages.setPackageDescription("Flight and stay");
        employeePackages.setTotalPackageSignUp(23);
        employeePackages.setDeleted(false);
        return employeePackages;
    }
    //save
    @Test
    public void saveTest(){
        packagesServices.save(newPackageRequest());

        ArgumentCaptor<EmployeePackages> capturePackages = ArgumentCaptor.forClass(EmployeePackages.class);
        verify(packageRepo).save(capturePackages.capture());

        EmployeePackages employeePackages = capturePackages.getValue();

        Assert.assertEquals(employeePackages.getPackageCategory(), PackageCategory.BEACH);
        Assert.assertEquals(employeePackages.getTravelDate(), Date.valueOf("2022-01-01"));
        Assert.assertEquals(employeePackages.getTravelDestination(), "Greece");
        Assert.assertEquals(employeePackages.getPackageDays(), 15);
        Assert.assertEquals(employeePackages.getPackageStatus(), PackageStatus.PUBLISHED);
        Assert.assertEquals(employeePackages.getPackageCost(), 1500, 0);
        Assert.assertEquals(employeePackages.getPackageDescription(), "Flight and stay");
        Assert.assertEquals(employeePackages.getTotalPackageSignUp(), 23);
//        Assert.assertEquals(employeePackages.getDeleted(false);

    }
//     getAll,
    @Test
    public void shouldReturnAllEmpPackages(){
        when(packageRepo.findAll()).thenReturn(Collections.emptyList());
        List<EmployeePackages> packagesList = packagesServices.getAll();
        assertTrue(packagesList.isEmpty());
    }

//    // getById,
    @Test
    public void getEmployeePackageByPackageId(){
        Optional<EmployeePackages> employeePackages = packageRepo.findById(56);
        Assert.assertEquals(newPackage().getPackageCategory(), PackageCategory.BEACH);
        Assert.assertEquals(newPackage().getTravelDate(), Date.valueOf("2022-01-01"));
        Assert.assertEquals(newPackage().getTravelDestination(), "Greece");
        Assert.assertEquals(newPackage().getPackageDays(), 15);
        Assert.assertEquals(newPackage().getPackageStatus(), PackageStatus.PUBLISHED);
        Assert.assertEquals(newPackage().getPackageCost(), 1500, 0);
        Assert.assertEquals(newPackage().getPackageDescription(), "Flight and stay");
        Assert.assertEquals(newPackage().getTotalPackageSignUp(), 23);
    }

    // deleteById,
    @Test
    public void shouldDeleteById(){
        EmployeePackages employeePackages = new EmployeePackages();
        employeePackages.setPackageCategory(PackageCategory.BEACH);
        employeePackages.setTravelDate(Date.valueOf("2022-01-01"));
        employeePackages.setTravelDestination("Greece");
        employeePackages.setPackageDays(15);
        employeePackages.setPackageStatus(PackageStatus.PUBLISHED);
        employeePackages.setPackageCost(1500);
        employeePackages.setPackageDescription("Flight and stay");
        employeePackages.setTotalPackageSignUp(23);

        when(packageRepo.findById(employeePackages.getEmployeePackageId())).thenReturn(Optional.of(employeePackages));

        packagesServices.deletePackageByID(employeePackages.getEmployeePackageId());

        verify(packageRepo).deleteById(employeePackages.getEmployeePackageId());
    }
}
