package properit.io.employeecrud.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import properit.io.employeecrud.db.Employee;
import properit.io.employeecrud.service.EmployeeService;

import java.io.IOException;
import java.util.List;

@Component
public class DbImport {

    @Value("classpath:employees.json")
    private Resource resourceFile;

    @Autowired
    private EmployeeService employeeService;

    @EventListener(ContextRefreshedEvent.class)
    public void populateExampleData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        List<Employee> employees = mapper.readValue(resourceFile.getFile(), new TypeReference<>() {});
        for(Employee employee : employees) {
            employeeService.createEmployee(employee);
        }
    }
}
