package ru.brusnika.orgstruct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.brusnika.orgstruct.model.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    
}
