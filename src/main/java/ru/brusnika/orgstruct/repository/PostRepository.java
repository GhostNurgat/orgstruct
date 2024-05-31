package ru.brusnika.orgstruct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.brusnika.orgstruct.model.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    
}
