package com.api.produtos.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API-Produtos")
                        .description("API REST para gerenciamento de produtos")
                        .version("1.0")
                        .contact(new Contact()
                                .name("Lucas Santana Duarte")
                                .url("https://github.com/lucasduartedev")
                                .email("lucasduartdev@gmail.com"))

//                        .license(new License()
//                                .name("Apache 2.0")
//                                .url("http://springdoc.org"))

                )
                .addServersItem(new Server()
                        .url("http://localhost:8080")
                        .description("Server URL"));
    }

}
