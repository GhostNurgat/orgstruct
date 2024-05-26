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
    @Column(name = "EntityLegal", columnDefinition = "CHARACTER(10)", unique = true)
    private String entityLegal;

    @Column(nullable = false, name = "Name", unique = true)
    private String companyName;

    public Company() {}
}
