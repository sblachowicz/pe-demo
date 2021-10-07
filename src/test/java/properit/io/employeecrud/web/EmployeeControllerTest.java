package properit.io.employeecrud.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import properit.io.employeecrud.db.Employee;
import properit.io.employeecrud.db.Gender;
import properit.io.employeecrud.service.EmployeeService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = { EmployeeController.class })
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void getEmployee() throws Exception {
        mvc.perform(get("/employees/{id}", "1")).andExpect(status().isOk());
        Mockito.verify(employeeService).getEmployee(1L);
    }

    @Test
    public void postEmployee_validation_failed() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee emp = new Employee();
        emp.setFirstName("Roman");

        String empStr = objectMapper.writeValueAsString(emp);

        mvc.perform(post("/employees")
                .content(empStr)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError());

        Mockito.verifyNoInteractions(employeeService);
    }

    @Test
    public void postEmployee() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        Employee emp = new Employee();

        emp.setFirstName("Roman");
        emp.setLastName("Ziutowski");
        emp.setAge(33);
        emp.setGender(Gender.MALE);

        mvc.perform(post("/employees")
                .content(objectMapper.writeValueAsString(emp))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());

        Mockito.verify(employeeService).createEmployee(Mockito.any());
    }
}
