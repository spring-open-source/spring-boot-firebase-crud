package com.hardik.rook.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import lombok.AllArgsConstructor;

@Configuration
@EnableConfigurationProperties(OpenApiConfigurationProperties.class)
@AllArgsConstructor
public class OpenApiConfiguration {

	private final OpenApiConfigurationProperties openApiConfigurationProperties;

	@Bean
	public OpenAPI customOpenAPI() {
		final var properties = openApiConfigurationProperties.getProperties();
		final var contact = properties.getContact();
		return new OpenAPI().info(new Info().title(properties.getTitle()).version(properties.getApiVersion())
				.description(properties.getDescription())
				.contact(new Contact().email(contact.getEmail()).name(contact.getName()).url(contact.getUrl())));
	}

}