package com.expedia.exercise.service;

import java.text.SimpleDateFormat;

import javax.inject.Singleton;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.entity.StringEntity;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.expedia.exercise.HttpClient;
import com.expedia.exercise.service.impl.HotelServiceImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

@Ignore
@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpClient.class})
public class HotelServiceTest  {

	protected static final String EMPTY_STRING = "";
	
	private Injector injector;
	protected HotelService hotelService;
	protected SimpleDateFormat dateFormat;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Before
	public void setUp() throws Exception {
		
		injector = Guice.createInjector(new AbstractModule() {
			@Override
			protected void configure() {
				bind(HotelService.class).to(HotelServiceImpl.class).in(Singleton.class);
			}
		});
		
		hotelService = injector.getInstance(HotelService.class);
		
		PowerMockito.mockStatic(HttpClient.class);
        
		
		this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	}

	protected void MockResponse(String params, String entityContent, int statusCode) {
		
		HttpResponse mockHttpResponse = Mockito.mock(HttpResponse.class);
		StatusLine mockStatusLine = Mockito.mock(StatusLine.class);
		Mockito.when(mockHttpResponse.getStatusLine()).thenReturn(mockStatusLine);
		Mockito.when(mockStatusLine.getStatusCode()).thenReturn(statusCode);

		StringEntity entity = null;
		try  {
			entity = new StringEntity(entityContent);
			Mockito.when(mockHttpResponse.getEntity()).thenReturn(entity);
			PowerMockito.doReturn(mockHttpResponse).when(HttpClient.class, "sendGet", ArgumentMatchers.any(), ArgumentMatchers.eq(params), ArgumentMatchers.any());
		} catch (Exception encodingException) {
			throw new RuntimeException("Failed while mocking response...");
		}
	}
	
	
}