package ru.brusnika.orgstruct.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/style/**")
            .addResourceLocations("classpath:/static/style/");

        registry.addResourceHandler("/lib/**")
            .addResourceLocations("classpath:/static/lib/");
    }
}
