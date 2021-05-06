package com.hardik.rook.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "com.hardik.rook")
@Data
public class FireBaseConfigurationProperties {

	private Configuration firebase = new Configuration();

	@Data
	public class Configuration {

		private String firebasePrivateKey;

	}

}
