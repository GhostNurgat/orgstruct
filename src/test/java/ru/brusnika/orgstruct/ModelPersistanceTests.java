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
        this.entityManager.persistAndFlush(Company.builder()
            .entityLegal("БСЗ")
            .companyName("Брусника")
            .build());
        
        this.entityManager.persistAndFlush(Location.builder()
            .id((long) 1000)
            .name("Екатеринбург")
            .build());
    }

    @Test
    public void testSaveCompany() {
        Company company = Company.builder()
            .entityLegal("БСЗ")
            .companyName("Брусника")
            .build();
        Company getCompany = entityManager.find(Company.class, company.getEntityLegal());

        assertNotNull(getCompany);
    }
}
