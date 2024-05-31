package ru.brusnika.orgstruct.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "workers")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Worker {
    @Id
    @Column(name = "id", columnDefinition = "CHARACTER(15)")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Group.class)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "FIO", columnDefinition = "CHARACTER(80)")
    private String FIO;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Post.class)
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = TypeWork.class)
    @JoinColumn(name = "type_id")
    private TypeWork type;

    public Worker() {}
}
