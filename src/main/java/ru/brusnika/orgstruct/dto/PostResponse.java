package ru.brusnika.orgstruct.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PostResponse {
    private Long postId;
    private String postName;
}
