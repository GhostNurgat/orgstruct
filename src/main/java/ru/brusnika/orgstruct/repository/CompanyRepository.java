package ru.brusnika.orgstruct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.brusnika.orgstruct.model.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, String> {
    
}
