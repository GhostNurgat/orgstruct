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
@Getter
@Setter
@AllArgsConstructor
@Table(name = "subdivisions")
@Builder
public class Subdivision {
    @Id
    @Column(name = "sub_id", columnDefinition = "BIGINT")
    private Long subId;

    @Column(name = "sub_name", nullable = true, columnDefinition = "CHARACTER(50)")
    private String subName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Location.class)
    @JoinColumn(name = "location_id")
    private Location location;

    public Subdivision() {}
}
