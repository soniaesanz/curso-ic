package com.grupoesfera.demo.integration.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerApiConfig {

    @Bean
    public Docket swaggerClientsApiv10() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("clients-api-1.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.grupoesfera.demo.resource"))
                .paths(regex("/clients/v1.0.*"))
                .build()
                .apiInfo(
                        new ApiInfoBuilder()
                        .version("1.0")
                        .title("Clients API")
                        .description("Documentation Clients API v1.0")
                        .build()
                );
    }


}
