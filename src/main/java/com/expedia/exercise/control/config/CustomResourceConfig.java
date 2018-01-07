package com.expedia.exercise.control.config;

import org.glassfish.jersey.server.ResourceConfig;

public class CustomResourceConfig extends ResourceConfig {

	private static final String CONTROLLER = "com.expedia.exercise";

	public CustomResourceConfig() {

		packages(CONTROLLER);
	}
}