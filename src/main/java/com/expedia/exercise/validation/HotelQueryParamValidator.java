package com.expedia.exercise.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.expedia.exercise.bean.HotelOffersRequest;
import com.expedia.exercise.exception.ExerciseException;

/**
 * Hotel parameters representation.
 * 
 * <ul>
 * <li>Validates hotel URL parameter</li>
 * <li>Generate URL query params</li>
 * </ul>
 * 
 * @author ZZiad
 */
public enum HotelQueryParamValidator {

	INSATNCE;
	
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public void validate(HotelOffersRequest hotelOffersRequest) {

		if (hotelOffersRequest.getLengthOfStay() != null && (hotelOffersRequest.getLengthOfStay() < 1 || hotelOffersRequest.getLengthOfStay() > 60)) {
			throw new ExerciseException("invalid.stay.length");
		}
		
		try {
			validateTripDate(hotelOffersRequest.getMinTripStartDate(), hotelOffersRequest.getMaxTripStartDate());
		} catch (ParseException | NullPointerException e) {
			throw new ExerciseException("invalid.min.max.star.rating.format");
		}

		if (!validMinMaxValues(hotelOffersRequest.getMinStarRating(), hotelOffersRequest.getMaxStarRating())) {
			throw new ExerciseException("invalid.min.max.star.rating");
		}

		if (!validMinMaxValues(hotelOffersRequest.getMinGuestRating(), hotelOffersRequest.getMaxGuestRating())) {
			throw new ExerciseException("invalid.min.max.guest.rating");
		}
		
		Integer minTotalRating = hotelOffersRequest.getMinTotalRate();
		Integer maxTotalRating = hotelOffersRequest.getMaxTotalRate();

		if (!validMinMaxValues(minTotalRating != null ? minTotalRating.floatValue(): null, maxTotalRating != null ? maxTotalRating.floatValue() : null)) {
			throw new ExerciseException("invalid.min.max.total.rating");
		}
	}

	private boolean validMinMaxValues(Float min, Float max) {

		if ((min != null && (min < 0 || min > 5)) || (max != null && (max < 0 || max > 5))) {
			return false;
		}
		
		if (min == null || max == null) {
			return true;
		}
		
		if (min > max) {
			return false;
		}
		
		return true;
	}

	private void validateTripDate(String minTripStartDate, String maxTripStartDate) throws ParseException {

		if (minTripStartDate == null && maxTripStartDate == null) {
			return;
		}
		
		Date minTripDate = null;
		if (minTripStartDate != null) {
			minTripDate = dateFormat.parse(minTripStartDate);
		}

		Date maxTripDate;
		if (maxTripStartDate != null) {
			maxTripDate = dateFormat.parse(maxTripStartDate);
			if (minTripDate != null && minTripDate.after(maxTripDate)) {
				throw new ExerciseException("invalid.trip.date.min.gt.max");
			}
		}
	}
}