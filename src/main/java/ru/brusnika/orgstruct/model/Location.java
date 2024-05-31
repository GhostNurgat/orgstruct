package ru.brusnika.orgstruct.model;

import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "locations")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Location {
    @Id
    @Column(name = "location_id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "name", nullable = false, columnDefinition = "CHARACTER(50)")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Company.class)
    @JoinColumn(name = "entity_legal")
    private Company company;

    public Location() {}
}
