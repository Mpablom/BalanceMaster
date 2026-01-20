package com.BalanceMaster.gestor_ventas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("BalanceMaster API")
            .description("API para la gestión de ventas, inventario, clientes y proveedores")
            .version("1.0.0")
            .contact(new Contact()
                .name("Pablo")
                .email("ing.pablo.balance@example.com")
                .url("https://github.com/pablo/BalanceMaster"))
            .license(new License()
                .name("MIT License")
                .url("https://opensource.org/licenses/MIT")))
        .servers(java.util.Arrays.asList(
            new Server()
                .url("http://localhost:8080")
                .description("Servidor de desarrollo"),
            new Server()
                .url("https://api.balancemaster.com")
                .description("Servidor de producción")))
        .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
        .components(new io.swagger.v3.oas.models.Components()
            .addSecuritySchemes("bearerAuth",
                new SecurityScheme()
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat("JWT")));
  }
}
