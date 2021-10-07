package properit.io.employeecrud.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import properit.io.employeecrud.db.Employee;
import properit.io.employeecrud.db.EmployeeAddressesRepository;
import properit.io.employeecrud.db.EmployeeRepository;
import properit.io.employeecrud.exception.ResourceNotFoundException;

import java.util.Optional;

@SpringBootTest(classes = EmployeeService.class)
public class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @MockBean
    private EmployeeAddressesRepository employeeAddressesRepository;

    @Test
    public void testCreateEmployee() {
        // given
        Employee emp = new Employee();
        emp.setFirstName("Zuitek");
        emp.setLastName("Kowalski");


        // when
        employeeService.createEmployee(emp);

        // then
        Mockito.verify(employeeRepository).save(emp);
    }

    @Test
    public void getEmployeeTest_wrongId_shouldThrowException() {
        // given
        Long id = 1L;
        Mockito.when(employeeRepository.findById(id)).thenThrow(new ResourceNotFoundException("Not found"));

        // when
        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            employeeService.getEmployee(id);
        });

        // then
        Mockito.verify(employeeRepository).findById(id);
    }

    @Test
    public void getEmployeeTest() {
        // given
        Long id = 1L;
        Employee emp = new Employee();
        emp.setId(id);
        Mockito.when(employeeRepository.findById(id)).thenReturn(Optional.of(emp));

        // when
        Employee dbEmp = employeeService.getEmployee(id);


        // then
        Mockito.verify(employeeRepository).findById(id);
        Assertions.assertEquals(dbEmp.getId(), emp.getId());
    }
}
