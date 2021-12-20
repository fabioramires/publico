package br.com.personal.fabioramires.bank.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig  {

    @Bean
    public GroupedOpenApi bankApi() {
        return GroupedOpenApi.builder()
                .group("fabioramiresbank")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI springBankOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("FabioRamiresBank API")
                        .description("")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
                /*.externalDocs(new ExternalDocumentation()
                        .description("FabioRamiresBank Wiki Documentation")
                        .url("https://fabioramiresbank.wiki.github.org/docs"));*/
    }
}