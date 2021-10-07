package properit.io.employeecrud.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeAddressesRepository extends CrudRepository<EmployeeAddress, Long> {
    List<EmployeeAddress> deleteByEmployeeId(Long employeeId);
}
