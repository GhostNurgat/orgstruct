package ru.brusnika.orgstruct.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "type_works")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class TypeWork {
    @Id
    @GeneratedValue
    @Column(name = "type_id", columnDefinition = "BIGSERIAL")
    private Long typeId;

    @Column(name = "type_name", columnDefinition = "CHARACTER(50)")
    private String typeName;

    public TypeWork() {}
}
