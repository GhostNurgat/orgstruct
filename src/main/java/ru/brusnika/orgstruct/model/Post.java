package ru.brusnika.orgstruct.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "posts")
@Getter
@Setter
@AllArgsConstructor
@Builder
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "post_id", columnDefinition = "BIGINT")
    private Long postId;

    @Column(name = "name")
    private String postName;

    public Post() {}
}
