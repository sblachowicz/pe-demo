package properit.io.employeecrud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import properit.io.employeecrud.db.Employee;
import properit.io.employeecrud.db.EmployeeAddressesRepository;
import properit.io.employeecrud.db.EmployeeRepository;
import properit.io.employeecrud.exception.ResourceNotFoundException;

import java.util.List;

@Service
public class EmployeeService {

    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeAddressesRepository employeeAddressesRepository;

    public Employee createEmployee(Employee employee) {
        logger.info("Adding new employee to database name={} lastName={}",
                employee.getFirstName(),
                employee.getLastName()
        );

        if(employee.getAddresses() != null && ! employee.getAddresses().isEmpty()) {
            employee.getAddresses().forEach(address -> address.setEmployee(employee));
        }
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id) {
        logger.info("Trying to fetch employee with id={}", id);
        return employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found!")
        );
    }

    public List<Employee> getAllEmployees() {
        logger.info("Getting all employees");
        return Streamable.of(employeeRepository.findAll()).toList();
    }

    @Transactional
    public boolean updateEmployee(Employee employee) {
        Employee dbEmployee = employeeRepository.findById(employee.getId()).orElseThrow(
                () -> new ResourceNotFoundException("User with id=" + employee.getId() + " not found")
        );

        dbEmployee.setFirstName(employee.getFirstName());
        dbEmployee.setLastName(employee.getLastName());
        dbEmployee.setAge(employee.getAge());
        dbEmployee.setGender(employee.getGender());

        // clear addresses
        employeeAddressesRepository.deleteByEmployeeId(employee.getId());
        // add new addresses
        dbEmployee.setAddresses(employee.getAddresses());

        employeeRepository.save(dbEmployee);
        return true;
    }

    public void deleteUserById(Long userId) {
        employeeRepository.deleteById(userId);
    }
}
