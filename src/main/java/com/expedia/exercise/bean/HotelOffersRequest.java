package com.expedia.exercise.bean;

public class HotelOffersRequest {

	private static final String EMPTY_STRING = "";
	private String destinationName;
	private String minTripStartDate;
	private String maxTripStartDate;
	private Integer lengthOfStay;
	private Float minStarRating;
	private Float maxStarRating;
	private Float minGuestRating;
	private Float maxGuestRating;
	private Integer minTotalRate;
	private Integer maxTotalRate;

	public HotelOffersRequest(String destinationName, String minTripStartDate, String maxTripStartDate, 
			Integer lengthOfStay, Float minStarRating, Float maxStarRating, Float minGuestRating, 
			Float maxGuestRating, Integer minTotalRate, Integer maxTotalRate) {
				
				this.destinationName = destinationName;
				this.lengthOfStay = lengthOfStay;
				this.minStarRating = minStarRating;
				this.maxStarRating = maxStarRating;
				this.minGuestRating = minGuestRating;
				this.maxGuestRating = maxGuestRating;
				this.minTotalRate = minTotalRate;
				this.maxTotalRate = maxTotalRate;
				setMinTripStartDate(minTripStartDate);
				setMaxTripStartDate(maxTripStartDate);
	}

	public HotelOffersRequest() {
	}

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getMinTripStartDate() {
		return minTripStartDate;
	}

	public void setMinTripStartDate(String minTripStartDate) {
		
		if (minTripStartDate != null && minTripStartDate.trim().isEmpty()) {
			minTripStartDate = null;
		}
		this.minTripStartDate = minTripStartDate;
	}

	public String getMaxTripStartDate() {
		return maxTripStartDate;
	}

	public void setMaxTripStartDate(String maxTripStartDate) {
		
		if (maxTripStartDate != null && maxTripStartDate.trim().isEmpty()) {
			maxTripStartDate = null;
		}
		this.maxTripStartDate = maxTripStartDate;
	}

	public Integer getLengthOfStay() {
		return lengthOfStay;
	}

	public void setLengthOfStay(Integer lengthOfStay) {
		this.lengthOfStay = lengthOfStay;
	}

	public Float getMinStarRating() {
		return minStarRating;
	}

	public void setMinStarRating(Float minStarRating) {
		this.minStarRating = minStarRating;
	}

	public Float getMaxStarRating() {
		return maxStarRating;
	}

	public void setMaxStarRating(Float maxStarRating) {
		this.maxStarRating = maxStarRating;
	}

	public Float getMinGuestRating() {
		return minGuestRating;
	}

	public void setMinGuestRating(Float minGuestRating) {
		this.minGuestRating = minGuestRating;
	}

	public Float getMaxGuestRating() {
		return maxGuestRating;
	}

	public void setMaxGuestRating(Float maxGuestRating) {
		this.maxGuestRating = maxGuestRating;
	}

	public Integer getMinTotalRate() {
		return minTotalRate;
	}

	public void setMinTotalRate(Integer minTotalRate) {
		this.minTotalRate = minTotalRate;
	}

	public Integer getMaxTotalRate() {
		return maxTotalRate;
	}

	public void setMaxTotalRate(Integer maxTotalRate) {
		this.maxTotalRate = maxTotalRate;
	}
	
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder();
		builder.append(destinationName != null && !destinationName.isEmpty() ? "&destinationName=" + destinationName : EMPTY_STRING);
		builder.append(minTripStartDate != null && !minTripStartDate.isEmpty() ? "&minTripStartDate=" + minTripStartDate : EMPTY_STRING);
		builder.append(maxTripStartDate != null && !maxTripStartDate.isEmpty() ? "&maxTripStartDate=" + maxTripStartDate : EMPTY_STRING);
		builder.append(lengthOfStay != null ? "&lengthOfStay=" + lengthOfStay : EMPTY_STRING);
		builder.append(maxStarRating != null ? "&maxStarRating=" + maxStarRating : EMPTY_STRING);
		builder.append(minStarRating != null ? "&minStarRating=" + minStarRating : EMPTY_STRING);
		builder.append(maxGuestRating != null ? "&maxGuestRating=" + maxGuestRating : EMPTY_STRING);
		builder.append(minGuestRating != null ? "&minGuestRating=" + minGuestRating : EMPTY_STRING);
		builder.append(minTotalRate != null ? "&minTotalRate=" + minTotalRate : EMPTY_STRING);
		builder.append(maxTotalRate != null ? "&maxTotalRate=" + maxTotalRate : EMPTY_STRING);
		return builder.toString();
	}
}
