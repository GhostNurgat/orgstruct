package ru.brusnika.orgstruct.model;

import lombok.*;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
    @GeneratedValue
    @Column(name = "id", columnDefinition = "BIGINT")
    private Long id;

    @Column(name = "Name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "EntityLegal")
    Company company;
}
