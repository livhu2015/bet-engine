package com.sohotech.betengine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String TITLE = "Bet System";
    private static final String DESCRIPTION = "api";

    @Bean
    public Docket api() {

        return new Docket(DocumentationType.SWAGGER_12)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sohotech.betengine.controller"))
                .paths(any()).build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(TITLE).description(DESCRIPTION)
                .contact(new Contact("Ovalleaf", "https://ovalleaf.com", "livhu@ovalleaf.com"))
                .license("Open Source").licenseUrl("https://www.ovalleaf.com").version("1.0.0").build();
    }

}
