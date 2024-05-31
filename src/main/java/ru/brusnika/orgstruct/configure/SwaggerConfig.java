package ru.brusnika.orgstruct.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class SwaggerConfig {
    @Bean
    public OpenAPI swaggerApiConfig() {
        var info = new Info()
            .title("Орг. Структура компании 'Брусника'")
            .version("1.0");
        return new OpenAPI().info(info);
    }
}
