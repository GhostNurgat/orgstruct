package ru.brusnika.orgstruct.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class WorkerResponse {
    private String id;
    private GroupResponse groupResponse;
    private String FIO;
    private PostResponse postResponse;
    private TypeWorkResponse typeWorkResponse;
}
