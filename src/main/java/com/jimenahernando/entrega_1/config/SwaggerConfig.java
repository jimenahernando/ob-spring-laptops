package com.jimenahernando.entrega_1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails(){
        return new ApiInfo(
            "Spring Boot - LAPTOP API Rest",
            "Laptops Api Rest docs",
            "1.0",
            "http://www.google.com",
            new Contact("Jimena Hernando", "https://github.com/jimenahernando", "jimenahernando@hotmail.com"),
            "MIT",
            "https://github.com/jimenahernando",
            Collections.emptyList());
    }
}
