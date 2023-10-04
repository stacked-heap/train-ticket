package edu.fudan.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * @author fdse
 */
@Configuration
public class SwaggerConfig {

    @Value("${swagger.controllerPackage}")
    private String controllerPackagePath;

    private static final Logger LOGGER = LoggerFactory.getLogger(SwaggerConfig.class);

    @Bean
    public GroupedOpenApi publicApi() {
        SwaggerConfig.LOGGER.info("[createRestApi][create][controllerPackagePath: {}]", controllerPackagePath);
        return GroupedOpenApi.builder()
                .pathsToMatch("**/*")
                .build();
    }

    @Bean
    private OpenAPI apiInfo() {
        return new OpenAPI()
                .info(new Info().title("Springboot builds the API documentation with swagger")
                        .description("Simple and elegant restful style")
                        .termsOfService("https://github.com/FudanSELab/train-ticket").version("1.0"));
    }

}
