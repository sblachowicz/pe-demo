package properit.io.employeecrud.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "employee_addresses")
public class EmployeeAddress {
    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("street")
    @NotBlank
    @Column(name = "street")
    private String street;

    @JsonProperty("street_number")
    @NotBlank
    @Column(name = "street_number")
    private String streetNum;

    @JsonProperty("city")
    @NotBlank
    @Column(name = "city")
    private String city;

    @JsonProperty("zip_code")
    @NotBlank
    @Column(name = "zip_code")
    private String zipCode;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
}
