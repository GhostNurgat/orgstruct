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
@Getter
@Setter
@AllArgsConstructor
@Table(name = "subdivisions")
@Builder
public class Subdivision {
    @Id
    @GeneratedValue
    @Column(name = "sub_id", columnDefinition = "BIGINT")
    private Long subId;

    @Column(name = "sub_name", unique = true, nullable = true)
    private String subName;

    @ManyToOne
    @JoinColumn(name = "EntityLegal")
    Company company;
}
