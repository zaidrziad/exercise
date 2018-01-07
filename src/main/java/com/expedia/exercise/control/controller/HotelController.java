package com.expedia.exercise.control.controller;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.expedia.exercise.bean.ErrorMessage;
import com.expedia.exercise.bean.HotelOffersRequest;
import com.expedia.exercise.exception.ExerciseException;
import com.expedia.exercise.service.HotelService;

@Singleton
@Path("/v1/hotels")
public class HotelController {

	@Inject
	private HotelService hotelService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getHotelOffers(@QueryParam("destinationName") String destinationName,
			@QueryParam("minTripStartDate") String minTripStartDate,
			@QueryParam("maxTripStartDate") String maxTripStartDate,
			@Min(1) @Max(60) @QueryParam("lengthOfStay") Integer lengthOfStay,
			@Min(0) @Max(5) @QueryParam("minStarRating") Float minStarRating,
			@Min(0) @Max(5) @QueryParam("maxStarRating") Float maxStarRating,
			@Min(0) @Max(5) @QueryParam("minGuestRating") Float minGuestRating,
			@Min(0) @Max(5) @QueryParam("maxGuestRating") Float maxGuestRating,
			@Min(0) @Max(5) @QueryParam("minTotalRate") Integer minTotalRate,
			@Min(0) @Max(5) @QueryParam("maxTotalRate") Integer maxTotalRate) {

		HotelOffersRequest hotelOffersRequest = new HotelOffersRequest(destinationName, minTripStartDate, maxTripStartDate, 
				lengthOfStay, minStarRating, maxStarRating, minGuestRating, maxGuestRating, minTotalRate, maxTotalRate);
		
		try {
			return Response.ok(hotelService.getOffers(hotelOffersRequest)).build();
		} catch (ExerciseException exception) {
			return Response.status(Status.BAD_REQUEST).entity(new ErrorMessage(exception.getMessage())).build();
		}
	}
}
