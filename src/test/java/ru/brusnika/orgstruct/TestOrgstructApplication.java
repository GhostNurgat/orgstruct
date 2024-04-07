package ru.brusnika.orgstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestOrgstructApplication {

	public static void main(String[] args) {
		SpringApplication.from(OrgstructApplication::main).with(TestOrgstructApplication.class).run(args);
	}

}
