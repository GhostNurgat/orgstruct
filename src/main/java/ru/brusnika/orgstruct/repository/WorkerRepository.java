package ru.brusnika.orgstruct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.brusnika.orgstruct.model.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, String> {
    
}
