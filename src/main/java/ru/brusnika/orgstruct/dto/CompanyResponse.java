package ru.brusnika.orgstruct.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class CompanyResponse {
    private String entityLegal;
    private String companyName;
}
