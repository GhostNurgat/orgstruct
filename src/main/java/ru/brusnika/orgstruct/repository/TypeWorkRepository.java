package ru.brusnika.orgstruct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.brusnika.orgstruct.model.TypeWork;

@Repository
public interface TypeWorkRepository extends JpaRepository<TypeWork, Long> {
    
}
