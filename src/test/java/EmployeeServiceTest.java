import com.example.demo.DAO.EmployeeRepository;
import com.example.demo.DTO.CreateEmployeeRequest;
import com.example.demo.DTO.EmployeeResponse;
import com.example.demo.DTO.Roles;
import com.example.demo.Models.Employee;
import com.example.demo.Models.HotelPartnerT;
import com.example.demo.Services.EmployeeServices;
import com.example.demo.Services.MailSender;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class EmployeeServiceTest {
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private EmployeeServices employeeServices;

    @Mock
    private MailSender mailSender;

    @Before
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    private CreateEmployeeRequest newEmployee(){
        CreateEmployeeRequest employee = new CreateEmployeeRequest();
//        employee.(42);
        employee.setFirstName("New");
        employee.setLastName("Employee");
        employee.setEmail("worker@new.com");
        employee.setUsername("workinhard");
        employee.setPassword("p@55w0rd");
        employee.setRole(Roles.EMPLOYEE);
//        employee.setDeleted(false);
        return employee;
    }
    //getAll
    @Test
    public void shouldReturnAllEmployees() {
        when(employeeRepository.findAll()).thenReturn(Collections.emptyList());
        List<EmployeeResponse> employeeList = employeeServices.getAllEmployee();
        assertTrue(employeeList.isEmpty());
    }
    //delete
    @Test
    public void shouldDeleteById(){
        Employee employee = new Employee();
        employee.setEmployeeId(76);
        employee.setFirstName("Newww");
        employee.setLastName("Emmployeee");
        employee.setEmail("needto@delete.com");
        employee.setUsername("deleteplease");
        employee.setPassword("banfromrepo");
//        employee.setDeleted();
        employee.setRole(Roles.EMPLOYEE);

        when(employeeRepository.getEmployeeByEmployeeId(employee.getEmployeeId())).thenReturn(Optional.of(employee));

        employeeServices.deleteEmployee(employee.getEmployeeId());

        verify(employeeRepository).save(employee);

        ArgumentCaptor<Employee> captor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository).save(captor.capture());
        Assert.assertEquals(captor.getValue().getEmployeeId(),76);

    }


//    create
    @Test
    public void saveTest(){
    when(mailSender.createdEmployee(any())).thenReturn("");
        employeeServices.createEmployee(newEmployee());

        ArgumentCaptor<Employee> captureEmployee = ArgumentCaptor.forClass(Employee.class);
//        ArgumentCaptor<CreateEmployeeRequest> captureEmployee = ArgumentCaptor.forClass(CreateEmployeeRequest.class);
        verify(employeeRepository).save(captureEmployee.capture());

        Employee employee = captureEmployee.getValue();
//        CreateEmployeeRequest employee = captureEmployee.getValue();

        Assert.assertEquals(employee.getFirstName(), "New");
        Assert.assertEquals(employee.getLastName(), "Employee");
        Assert.assertEquals(employee.getEmail(), "worker@new.com");
        Assert.assertEquals(employee.getUsername(), "workinhard");
        Assert.assertNotEquals(employee.getPassword(), "p@55w0rd");
        Assert.assertEquals(employee.getRole(), Roles.EMPLOYEE);
    }


    //  empExist?
    @Test
    public void doesEmpExist(){
        Employee employee = new Employee(5, "name", "last", "email", "username", "password", Roles.EMPLOYEE, false);

        Employee employee1 = Mockito.spy(employee);

        Assert.assertEquals(false, employee1.isDeleted());

    }
    @Test
    public void doesNotEmpExist(){
        Employee employee = new Employee(5, "name", "last", "email", "username", "password", Roles.EMPLOYEE, true);

        Employee employee1 = Mockito.spy(employee);

        Assert.assertEquals(true, employee1.isDeleted());

    }
}
