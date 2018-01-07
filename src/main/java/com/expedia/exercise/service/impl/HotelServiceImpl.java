package com.expedia.exercise.service.impl;

import java.io.IOException;

import javax.inject.Singleton;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.log4j.Logger;

import com.expedia.exercise.HttpClient;
import com.expedia.exercise.bean.HotelOffersRequest;
import com.expedia.exercise.control.config.AppConfig;
import com.expedia.exercise.exception.ExerciseException;
import com.expedia.exercise.service.HotelService;
import com.expedia.exercise.validation.HotelQueryParamValidator;

/**
 * Hotel offers service implementation:
 * 
 * This class calls another API to fetch Hotels data
 * 
 * @see ServiceConfig#getOffersServiceURL()
 * @since 1.0
 * @author ZZiad
 */
@Singleton
public class HotelServiceImpl implements HotelService {

	private static final Logger LOGGER = Logger.getLogger(HotelServiceImpl.class);
	private String OffersURL = AppConfig.INSTANCE.getOffersServiceURL();
	
	@Override
	public String getOffers(HotelOffersRequest hotelOffersRequest) {

		if (hotelOffersRequest == null) {
			throw new ExerciseException("invalid.hotel.offer.request");
		}
		HotelQueryParamValidator.INSATNCE.validate(hotelOffersRequest);
		return requestOffers(hotelOffersRequest.toString());
	}

	private String requestOffers(String hotelQueryParam) {

		HttpResponse httpResponse;
		try {
			httpResponse = HttpClient.sendGet(OffersURL, hotelQueryParam, MediaType.APPLICATION_JSON);
			if (httpResponse.getStatusLine().getStatusCode() != 200) {
				throw new ExerciseException("offers.api.response.failure");
			}
			return IOUtils.toString(httpResponse.getEntity().getContent());
		} catch (IOException e) {
			LOGGER.error("Failed to fetch data from API, root cause: " + e.getMessage());
			throw new ExerciseException("offers.api.communication.failure");
		} catch (Exception e) {
			LOGGER.error("Failed to fetch data from API, root cause: " + e.getMessage());
			throw new ExerciseException("api.call.unexpected.error");
		}
	}
}