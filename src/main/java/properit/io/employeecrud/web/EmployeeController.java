package properit.io.employeecrud.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import properit.io.employeecrud.db.Employee;
import properit.io.employeecrud.service.EmployeeService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.http.HttpResponse;
import java.util.List;

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
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@NotNull @PathVariable("employeeId") Long id) {
        return employeeService.getEmployee(id);
    }

    @PutMapping
    public ResponseEntity<String> updateEmployee(@Valid @RequestBody Employee employee) {
        if (employee.getId() == null) {
            return ResponseEntity.badRequest().body("ID of the employee cannot be null");
        }
        Boolean result = employeeService.updateEmployee(employee);
        if (result) {
            return ResponseEntity.ok("UPDATED");
        } else {
            return ResponseEntity.ok("UPDATE_FAILED");
        }
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity deleteEmployee(@NotNull @PathVariable("employeeId") Long userId) {
        employeeService.deleteUserById(userId);
        return ResponseEntity.ok().build();
    }
}
