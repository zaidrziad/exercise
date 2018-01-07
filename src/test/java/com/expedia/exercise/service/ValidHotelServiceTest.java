package com.expedia.exercise.service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.ws.rs.core.Response.Status;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.expedia.exercise.HttpClient;
import com.expedia.exercise.bean.HotelOffersRequest;
import com.expedia.exercise.exception.ExerciseException;

@RunWith(PowerMockRunner.class)
@PrepareForTest({HttpClient.class})
public class ValidHotelServiceTest extends HotelServiceTest {

	private static final String RESPONSE_TEST_CODE_200 = "RESPONSE_TEST_CODE_200";

	@Test
	public void testGetOffersWithoutParams() {

		MockResponse(EMPTY_STRING, RESPONSE_TEST_CODE_200, Status.OK.getStatusCode());
		HotelOffersRequest hotelOffersRequest = new HotelOffersRequest();
		Assert.assertEquals(RESPONSE_TEST_CODE_200, hotelService.getOffers(hotelOffersRequest));
	}
	
	@Test
	public void testGetOffersWithDestinationName() {

		HotelOffersRequest hotelOffersRequest = new HotelOffersRequest();
		hotelOffersRequest.setDestinationName("Jordan");
		MockResponse(hotelOffersRequest.toString(), RESPONSE_TEST_CODE_200, Status.OK.getStatusCode());
		Assert.assertEquals(RESPONSE_TEST_CODE_200, hotelService.getOffers(hotelOffersRequest));
	}

	@Test
	public void testGetOffersWithValidLengthOfStay() throws ExerciseException {

		HotelOffersRequest hotelOffersRequest = new HotelOffersRequest();
		hotelOffersRequest.setLengthOfStay(33);
		MockResponse(hotelOffersRequest.toString(), RESPONSE_TEST_CODE_200, Status.OK.getStatusCode());
		Assert.assertEquals(RESPONSE_TEST_CODE_200, hotelService.getOffers(hotelOffersRequest));
	}

	@Test
	public void testGetOffersWithValidMinMaxTripStartDate() throws ExerciseException {

		checkGetOffersWithValidMinMaxTripStartDate(0, null);
		checkGetOffersWithValidMinMaxTripStartDate(null, 0);
		
		checkGetOffersWithValidMinMaxTripStartDate(1, null);
		checkGetOffersWithValidMinMaxTripStartDate(null, 1);

		checkGetOffersWithValidMinMaxTripStartDate(5, null);
		checkGetOffersWithValidMinMaxTripStartDate(null, 5);
		
		checkGetOffersWithValidMinMaxTripStartDate(-5, null);
		checkGetOffersWithValidMinMaxTripStartDate(null, -5);
		
		checkGetOffersWithValidMinMaxTripStartDate(-3, 6);
		checkGetOffersWithValidMinMaxTripStartDate(3, 6);
		checkGetOffersWithValidMinMaxTripStartDate(3, 3);
	}

	private void checkGetOffersWithValidMinMaxTripStartDate(Integer minDays, Integer maxDays) {

		HotelOffersRequest hotelOffersRequest = new HotelOffersRequest();
		
		if (minDays != null) {
			hotelOffersRequest.setMinTripStartDate(dateFormat.format(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(minDays))));
		}
		if (maxDays != null) {
			hotelOffersRequest.setMaxTripStartDate(dateFormat.format(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(maxDays))));
		}
		MockResponse(hotelOffersRequest.toString(), RESPONSE_TEST_CODE_200, Status.OK.getStatusCode());
		Assert.assertNotNull(hotelService.getOffers(hotelOffersRequest));
	}
	
	@Test
	public void testGetOffersWithValidMinStarRating() throws ExerciseException {

		checkGetOffersWithValidMinStarRating(1f);
		checkGetOffersWithValidMinStarRating(2.5f);
		checkGetOffersWithValidMinStarRating(5f);
	}
	
	private void checkGetOffersWithValidMinStarRating(Float maxRating) {

		HotelOffersRequest hotelOffersRequest = new HotelOffersRequest();
		hotelOffersRequest.setMinStarRating(maxRating);
		MockResponse(hotelOffersRequest.toString(), RESPONSE_TEST_CODE_200, Status.OK.getStatusCode());
		Assert.assertNotNull(hotelService.getOffers(hotelOffersRequest));
	}

	@Test
	public void testGetOffersWithValidMaxStarRating() throws ExerciseException {

		checkGetOffersWithValidMaxStarRating(1f);
		checkGetOffersWithValidMaxStarRating(2.5f);
		checkGetOffersWithValidMaxStarRating(5f);
	}

	private void checkGetOffersWithValidMaxStarRating(Float maxRating) {

		HotelOffersRequest hotelOffersRequest = new HotelOffersRequest();
		hotelOffersRequest.setMaxStarRating(maxRating);
		MockResponse(hotelOffersRequest.toString(), RESPONSE_TEST_CODE_200, Status.OK.getStatusCode());
		Assert.assertNotNull(hotelService.getOffers(hotelOffersRequest));
	}

	@Test
	public void testGetOffersWithValidMinMaxStarRating() throws ExerciseException {

		checkValidMinMaxStarRating(1f, 1f);
		checkValidMinMaxStarRating(1.4f, 2.5f);
		checkValidMinMaxStarRating(1.5f, 5f);
		checkValidMinMaxStarRating(1f, 4.9f);
		checkValidMinMaxStarRating(1f, 5f);
	}

	private void checkValidMinMaxStarRating(Float min, Float max) {

		HotelOffersRequest hotelOffersRequest = new HotelOffersRequest();
		hotelOffersRequest.setMinStarRating(min);
		hotelOffersRequest.setMaxStarRating(max);
		MockResponse(hotelOffersRequest.toString(), RESPONSE_TEST_CODE_200, Status.OK.getStatusCode());
		Assert.assertNotNull(hotelService.getOffers(hotelOffersRequest));
	}
	
	@Test
	public void testGetOffersWithValidMinMaxGuestRating() throws ExerciseException {

		checkValidMinMaxGuestRating(1.1f, 2f);
		checkValidMinMaxGuestRating(1.4f, 2.5f);
		checkValidMinMaxGuestRating(1.5f, 5f);
		checkValidMinMaxGuestRating(0.4f, 4.9f);
		checkValidMinMaxGuestRating(1f, 5f);
	}

	private void checkValidMinMaxGuestRating(Float min, Float max) {

		HotelOffersRequest hotelOffersRequest = new HotelOffersRequest();
		hotelOffersRequest.setMinGuestRating(min);
		hotelOffersRequest.setMaxGuestRating(max);
		MockResponse(hotelOffersRequest.toString(), RESPONSE_TEST_CODE_200, Status.OK.getStatusCode());
		Assert.assertNotNull(hotelService.getOffers(hotelOffersRequest));
	}
	
	@Test
	public void testGetOffersWithValidMinMaxTotalRating() throws ExerciseException {

		checkValidMinMaxTotalRating(1.3f, 2f);
		checkValidMinMaxTotalRating(1.4f, 2.5f);
		checkValidMinMaxTotalRating(1.2f, 5f);
		checkValidMinMaxTotalRating(3.4f, 4.9f);
		checkValidMinMaxTotalRating(0.6f, 5f);
	}

	private void checkValidMinMaxTotalRating(Float min, Float max) {

		HotelOffersRequest hotelOffersRequest = new HotelOffersRequest();
		hotelOffersRequest.setMinGuestRating(min);
		hotelOffersRequest.setMaxGuestRating(max);
		MockResponse(hotelOffersRequest.toString(), RESPONSE_TEST_CODE_200, Status.OK.getStatusCode());
		Assert.assertNotNull(hotelService.getOffers(hotelOffersRequest));
	}
	
	@Test
	public void testGetOffersWithValidParams() throws ExerciseException {

		HotelOffersRequest hotelOffersRequest = new HotelOffersRequest();
		hotelOffersRequest.setMinGuestRating(1f);
		hotelOffersRequest.setMaxGuestRating(3f);
		
		MockResponse(hotelOffersRequest.toString(), RESPONSE_TEST_CODE_200, Status.OK.getStatusCode());
		Assert.assertNotNull(hotelService.getOffers(hotelOffersRequest));
		
		hotelOffersRequest.setLengthOfStay(6);
		hotelOffersRequest.setMaxTripStartDate(dateFormat.format(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(3))));
		
		MockResponse(hotelOffersRequest.toString(), RESPONSE_TEST_CODE_200, Status.OK.getStatusCode());
		Assert.assertNotNull(hotelService.getOffers(hotelOffersRequest));
		
		hotelOffersRequest.setMinTotalRate(5);
		hotelOffersRequest.setDestinationName("Jordan");
		MockResponse(hotelOffersRequest.toString(), RESPONSE_TEST_CODE_200, Status.OK.getStatusCode());
		Assert.assertNotNull(hotelService.getOffers(hotelOffersRequest));
	}
}
