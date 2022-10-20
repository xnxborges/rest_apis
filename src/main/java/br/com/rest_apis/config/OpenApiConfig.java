package br.com.rest_apis.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(new Info()
                        .title("Rest Api's With Java")
                        .version("v1")
                        .description("Project in Java using the main REST concepts, implementation of unit tests, database persistence and documentation with Swaager")
                        .termsOfService("http://nat.rest_apis.com.br/termsOfService")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://nat.rest_apis.com.br")
                        )
                );
    }
}
