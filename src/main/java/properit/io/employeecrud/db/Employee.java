package properit.io.employeecrud.db;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue
    private Long id;

    @JsonProperty("first_name")
    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    @JsonProperty("last_name")
    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    @JsonProperty("age")
    @NotNull
    @Column(name = "age")
    private Integer age;

    @JsonProperty("gender")
    @NotNull
    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(
            mappedBy = "employee",
            fetch = FetchType.EAGER
    )
    private Set<EmployeeAddress> addresses;
}
