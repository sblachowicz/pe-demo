package properit.io.employeecrud.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import properit.io.employeecrud.db.Employee;
import properit.io.employeecrud.db.EmployeeRepository;
import properit.io.employeecrud.service.EmployeeService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee createEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.createEmployee(employee);
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Employee getEmployee(@NotNull @PathVariable("id") Long id) {
        return employeeService.getEmployee(id);
    }

}
