package ru.brusnika.orgstruct.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "departments")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @Column(name = "depart_id", columnDefinition = "BIGINT")
    private Long departmentId;

    @Column(name = "depart_name", nullable = true, columnDefinition = "CHARACTER(50)")
    private String departmentName;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Subdivision.class)
    @JoinColumn(name = "sub_id")
    private Subdivision subdivision;

    public Department() {}
}
