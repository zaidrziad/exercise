package com.expedia.exercise;

import com.expedia.exercise.service.HotelService;
import com.expedia.exercise.service.impl.HotelServiceImpl;
import com.google.inject.AbstractModule;

public class AppInjector extends AbstractModule {

	@Override
	protected void configure() {
		bind(HotelService.class).to(HotelServiceImpl.class);
	}
}
