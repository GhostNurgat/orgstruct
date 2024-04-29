package ru.brusnika.orgstruct;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;
import ru.brusnika.orgstruct.model.Company;
import ru.brusnika.orgstruct.repository.CompanyRepository;

@DataJpaTest
public class ModelPersistanceTests {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    CompanyRepository companyRep;

    @Test
    public void testSaveCompany() {
        Company brusnika = Company.builder()
            .entityLegal("БСЗ")
            .companyName("Брусника")
            .build();
        brusnika = entityManager.persistFlushFind(brusnika);

        assertNotNull(brusnika);
        assertEquals(brusnika.getCompanyName(), "Брусника");
    }
}
