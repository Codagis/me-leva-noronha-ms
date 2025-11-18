package com.melevanoronha.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Configuração do Swagger/OpenAPI para documentação da API.
 *
 * @author Sistema Me Leva Noronha
 * @version 1.0
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "bearerAuth";
        
        return new OpenAPI()
                .info(new Info()
                        .title("Me Leva Noronha API")
                        .version("1.0.0")
                        .description("API REST para o aplicativo Me Leva Noronha. " +
                                "Fornece funcionalidades de autenticação, gerenciamento de passeios, " +
                                "dicas de viagem, vida noturna, tabela de marés, previsão do tempo " +
                                "e calculadora de custos de viagem para Fernando de Noronha.")
                        .contact(new Contact()
                                .name("Me Leva Noronha")
                                .email("contato@melevanoronha.com"))
                        .license(new License()
                                .name("Proprietário")
                                .url("https://melevanoronha.com")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de Desenvolvimento"),
                        new Server()
                                .url("https://api.melevanoronha.com")
                                .description("Servidor de Produção")))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("JWT token obtido através do endpoint /api/auth/login")));
    }
}

