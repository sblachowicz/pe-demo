package properit.io.employeecrud.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import properit.io.employeecrud.db.Employee;
import properit.io.employeecrud.db.EmployeeRepository;
import properit.io.employeecrud.exception.ResourceNotFoundException;

@Service
public class EmployeeService {

    private final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        logger.info("Adding new employee to database name={} lastName={}",
                employee.getFirstName(),
                employee.getLastName()
        );
        return employeeRepository.save(employee);
    }

    public Employee getEmployee(Long id) {
        logger.info("Trying to fetch employee with id={}", id);
        return employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee not found!")
        );
    }
}
