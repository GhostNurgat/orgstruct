package ru.brusnika.orgstruct.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "typeWorks")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class TypeWork {
    @Id
    @GeneratedValue
    @Column(name = "type_id", columnDefinition = "BIGINT")
    private Long typeId;

    @Column(name = "type_name")
    private String typeName;

    public TypeWork() {}
}
