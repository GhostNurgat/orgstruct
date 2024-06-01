package ru.brusnika.orgstruct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.brusnika.orgstruct.model.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
