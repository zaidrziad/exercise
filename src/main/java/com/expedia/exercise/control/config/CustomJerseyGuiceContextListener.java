package com.expedia.exercise.control.config;

import java.util.Collections;
import java.util.List;

import javax.inject.Singleton;

import com.expedia.exercise.service.HotelService;
import com.expedia.exercise.service.impl.HotelServiceImpl;
import com.google.inject.servlet.ServletModule;
import com.squarespace.jersey2.guice.JerseyGuiceServletContextListener;

public class CustomJerseyGuiceContextListener extends JerseyGuiceServletContextListener {
	
	@Override
	protected List<? extends com.google.inject.Module> modules() {
		
		return Collections.singletonList(new ServletModule() {
			@Override
			protected void configureServlets() {
				bind(HotelService.class).to(HotelServiceImpl.class).in(Singleton.class);
			}
		});
	}
}