package com.expedia.exercise.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import com.expedia.exercise.bean.HotelOffersRequest;
import com.expedia.exercise.exception.ExerciseException;

@RunWith(PowerMockRunner.class)
public class InvalidHotelServiceTest extends HotelServiceTest {

	private static final String INVALID_TRIP_DATE_MIN_GT_MAX = "invalid.trip.date.min.gt.max";
	private static final String INVALID_MIN_MAX_STAR_RATING_FORMAT = "invalid.min.max.star.rating.format";

	@Test
	public void testGetOffersWithNullHotelOffersRequest() {

		thrown.expect(ExerciseException.class);
		thrown.expectMessage("invalid.hotel.offer.request");
		hotelService.getOffers(null);
	}
	
	@Test
	public void testGetOffersWithNegativeLengthOfStay() throws ExerciseException {
		checkGetOffersWithInvalidLengthOfStay(-1);
	}
	
	@Test
	public void testGetOffersWithZeroLengthOfStay() throws ExerciseException {
		checkGetOffersWithInvalidLengthOfStay(0);
	}
	
	@Test
	public void testGetOffersWithValueExceedLimitLengthOfStay() throws ExerciseException {
		checkGetOffersWithInvalidLengthOfStay(61);
	}

	@Test
	public void testGetOffersWithNumericMinTripStartDate() throws ExerciseException {
		checkGetOffersWithInvalidMinMaxTripStartDate("123", null, INVALID_MIN_MAX_STAR_RATING_FORMAT);
	}
	
	@Test
	public void testGetOffersWithStringMinTripStartDate() throws ExerciseException {
		checkGetOffersWithInvalidMinMaxTripStartDate("asd", null, INVALID_MIN_MAX_STAR_RATING_FORMAT);
	}
	
	public void testGetOffersWithEmptyStringMinTripStartDate() throws ExerciseException {
		checkGetOffersWithInvalidMinMaxTripStartDate(" ", null, INVALID_MIN_MAX_STAR_RATING_FORMAT);
	}
	
	@Test
	public void testGetOffersWithNumericMaxTripStartDate() throws ExerciseException {
		checkGetOffersWithInvalidMinMaxTripStartDate(null, "123", INVALID_MIN_MAX_STAR_RATING_FORMAT);
	}
	
	@Test
	public void testGetOffersWithStringMaxTripStartDate() throws ExerciseException {
		checkGetOffersWithInvalidMinMaxTripStartDate(null, "asd", INVALID_MIN_MAX_STAR_RATING_FORMAT);
	}
	
	@Test
	public void testGetOffersWithEmptyStringMaxTripStartDate() throws ExerciseException {
		checkGetOffersWithInvalidMinMaxTripStartDate(null, " ", INVALID_MIN_MAX_STAR_RATING_FORMAT);
	}
	
	@Test
	public void testGetOffersWithMinGreaterThanMaxTripStartDate() throws ExerciseException {
		checkGetOffersWithInvalidMinMaxTripStartDate("2018-06-01", "2018-03-03", INVALID_TRIP_DATE_MIN_GT_MAX);
	}
	
	@Test
	public void testGetOffersWithZeroMinStarRating() throws ExerciseException {
		checkInvalidMinMaxStarRating(-10f, null);
	}

	@Test
	public void testGetOffersWithZeroMaxStarRating() throws ExerciseException {
		checkInvalidMinMaxStarRating(null, -1f);
	}

	@Test
	public void testGetOffersWithMinStarRatingValueExceedlimit() throws ExerciseException {
		checkInvalidMinMaxStarRating(6f, null);
	}
	
	@Test
	public void testGetOffersWithMaxStarRatingValueExceedlimit() throws ExerciseException {
		checkInvalidMinMaxStarRating(null, 6f);
	}
	
	@Test
	public void testGetOffersWithMinGreaterThanMaxStarRating() throws ExerciseException {
		checkInvalidMinMaxStarRating(5f, 3f);
	}
	
	@Test
	public void testGetOffersWithNegativeMinGuestRating() throws ExerciseException {
		checkInvalidMinMaxGuestRating(-9f, null);
	}

	@Test
	public void testGetOffersWithNegativeMaxGuestRating() throws ExerciseException {
		checkInvalidMinMaxGuestRating(null, -4f);
	}

	@Test
	public void testGetOffersWithMinGuestRatingValueExceedlimit() throws ExerciseException {
		checkInvalidMinMaxGuestRating(6f, null);
	}
	
	@Test
	public void testGetOffersWithMaxGuestRatingValueExceedlimit() throws ExerciseException {
		checkInvalidMinMaxGuestRating(null, 6f);
	}
	
	@Test
	public void testGetOffersWithMinGreaterThanMaxGuestRating() throws ExerciseException {
		checkInvalidMinMaxGuestRating(5f, 3f);
	}
	
	@Test
	public void testGetOffersWithNegativeMinTotalRating() throws ExerciseException {
		checkInvalidMinMaxTotalRating(-1, null);
	}

	@Test
	public void testGetOffersWithNegativeMaxTotalRating() throws ExerciseException {
		checkInvalidMinMaxTotalRating(null, -3);
	}

	@Test
	public void testGetOffersWithMinTotalRatingValueExceedlimit() throws ExerciseException {
		checkInvalidMinMaxTotalRating(6, null);
	}
	
	@Test
	public void testGetOffersWithMaxTotalRatingValueExceedlimit() throws ExerciseException {
		checkInvalidMinMaxTotalRating(null, 6);
	}
	
	@Test
	public void testGetOffersWithMinGreaterThanMaxTotaltRating() throws ExerciseException {
		checkInvalidMinMaxTotalRating(5, 3);
	}
	
	private void checkGetOffersWithInvalidLengthOfStay(int lengthOfStay) throws ExerciseException {
		
		HotelOffersRequest hotelOffersRequest = new HotelOffersRequest();
		hotelOffersRequest.setLengthOfStay(lengthOfStay);
		thrown.expect(ExerciseException.class);
		thrown.expectMessage("invalid.stay.length");
		hotelService.getOffers(hotelOffersRequest);
	}

	private void checkGetOffersWithInvalidMinMaxTripStartDate(String minDate, String maxDate, String expectedErrorMessage) throws ExerciseException {
		
		HotelOffersRequest hotelOffersRequest = new HotelOffersRequest();
		if (minDate != null) {
			hotelOffersRequest.setMinTripStartDate(minDate);
		}
		if (maxDate != null) {
			hotelOffersRequest.setMaxTripStartDate(maxDate);
		}
		thrown.expect(ExerciseException.class);
		thrown.expectMessage(expectedErrorMessage);
		hotelService.getOffers(hotelOffersRequest);
	}
	
	private void checkInvalidMinMaxStarRating(Float min, Float max) {

		HotelOffersRequest hotelOffersRequest = new HotelOffersRequest();
		if (min != null) {
			hotelOffersRequest.setMinStarRating(min);
		}
		if (max != null) {
			hotelOffersRequest.setMaxStarRating(max);
		}
		thrown.expect(ExerciseException.class);
		thrown.expectMessage("invalid.min.max.star.rating");
		hotelService.getOffers(hotelOffersRequest);
	}

	private void checkInvalidMinMaxGuestRating(Float min, Float max) {

		HotelOffersRequest hotelOffersRequest = new HotelOffersRequest();
		if (min != null) {
			hotelOffersRequest.setMinGuestRating(min);
		}
		if (max != null) {
			hotelOffersRequest.setMaxGuestRating(max);
		}
		thrown.expect(ExerciseException.class);
		thrown.expectMessage("invalid.min.max.guest.rating");
		hotelService.getOffers(hotelOffersRequest);
	}
	
	private void checkInvalidMinMaxTotalRating(Integer min, Integer max) {

		HotelOffersRequest hotelOffersRequest = new HotelOffersRequest();
		if (min != null) {
			hotelOffersRequest.setMinTotalRate(min);
		}
		if (max != null) {
			hotelOffersRequest.setMaxTotalRate(max);
		}
		thrown.expect(ExerciseException.class);
		thrown.expectMessage("invalid.min.max.total.rating");
		hotelService.getOffers(hotelOffersRequest);
	}
}
