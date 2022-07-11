package com.fpd.miniwms.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPIConfig() {
        return new OpenAPI().info(
                new Info()
                        .title("mini-wms")
                        .description("mini-wms application")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")
                        )
        );
    }

    @Bean
    public SwaggerUiConfigProperties swaggerUiConfig(SwaggerUiConfigProperties config) {
        config.setDefaultModelExpandDepth(-1);
        config.setDisableSwaggerDefaultUrl(true);
        config.setUrl("/v3/api-docs");
        config.setConfigUrl("/v3/api-docs/swagger-config");

        return config;
    }
}
