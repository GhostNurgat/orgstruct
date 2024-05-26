package ru.brusnika.orgstruct.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GroupResponse {
    private Long groupId;
    private String groupName;
    private DepartmentResponse departmentResponse;
}
