package com.hardik.rook.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "com.hardik.rook.swagger")
public class OpenApiConfigurationProperties {

	private Properties properties = new Properties();

	@Data
	public class Properties {
		private String title;
		private String description;
		private String apiVersion;

		private Contact contact = new Contact();

		@Data
		public class Contact {
			private String email;
			private String name;
			private String url;
		}
	}

}