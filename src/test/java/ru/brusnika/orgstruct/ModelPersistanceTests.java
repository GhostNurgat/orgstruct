package ru.brusnika.orgstruct;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.isNotNull;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import ru.brusnika.orgstruct.model.Company;
import ru.brusnika.orgstruct.model.Location;
import ru.brusnika.orgstruct.repository.CompanyRepository;
import ru.brusnika.orgstruct.repository.LocationRepository;

@DataJpaTest
public class ModelPersistanceTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    LocationRepository locationRepository;

    @BeforeEach
    public void setUp() {
        
    }

    @Test
    public void testSaveCompany() {
        Company company = Company.builder()
            .entityLegal("БСЗ")
            .companyName("Брусника")
            .build();
        company = entityManager.persistFlushFind(company);

        assertThat(company, isNotNull());
    }
}
