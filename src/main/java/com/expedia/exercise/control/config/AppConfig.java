package com.expedia.exercise.control.config;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;

public enum AppConfig {

	INSTANCE;

	private final Logger LOGGER = Logger.getLogger(AppConfig.class);
	private final String SERVICE_CONFIG_FILE_PATH = "/config/config.yml";
	private String offersServiceURL;

	private AppConfig() {

		Yaml yaml = new Yaml();
		try (InputStream in = Files.newInputStream(Paths.get(AppConfig.class.getResource(SERVICE_CONFIG_FILE_PATH).toURI()))) {
			offersServiceURL = yaml.loadAs(in, Config.class).getHotelsApi();
		} catch (IOException | URISyntaxException e) {
			LOGGER.fatal("Failed to read configuration file (config.yml)");
			throw new RuntimeException("config.init.failure");
		}
	}

	public String getOffersServiceURL() {
		return offersServiceURL;
	}
}