package ru.brusnika.orgstruct.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class DepartmentResponse {
    private Long departmentId;
    private String departmentName;
    private SubdivisionResponse subdivisionResponse;
}
