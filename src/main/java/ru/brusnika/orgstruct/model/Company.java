package ru.brusnika.orgstruct.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.*;

@Entity
@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Company {
    @Id
    @Column(name = "entity_legal", columnDefinition = "CHARACTER(10)")
    private String entityLegal;

    @Column(nullable = false, name = "name", columnDefinition = "CHARACTER(50)")
    private String companyName;

    public Company() {}
}
