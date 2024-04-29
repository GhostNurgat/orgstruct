package ru.brusnika.orgstruct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.brusnika.orgstruct.model.Subdivision;

@Repository
public interface SubdivisionRepository extends JpaRepository<Subdivision, Long> {
    
}
