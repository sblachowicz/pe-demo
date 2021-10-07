package properit.io.employeecrud.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import properit.io.employeecrud.service.EmployeeService;

@WebMvcTest(controllers = { EmployeeController.class })
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void createEmployee_missingData_shouldReturnError() throws Exception {
        mvc.perform(get("/employees/{id}", "1")).andExpect(status().isOk());
    }
}
