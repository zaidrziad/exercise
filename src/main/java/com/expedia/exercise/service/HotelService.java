package com.expedia.exercise.service;

import com.expedia.exercise.bean.HotelOffersRequest;

/**
 * Hotel offers service that provides following service(s):
 * <ul>
 *   <li>Get Hotel Offers</li>
 * <ul>
 * 
 * This class calls another API to fetch Hotels data
 * @see ServiceConfig#getOffersServiceURL()
 * @since 1.0
 * @author ZZiad
 */
public interface HotelService {

	/**
	 * Returns hotel offers from the API.
	 * 
	 * @param hotelQueryParam.getURLparams
	 * @return offers according to the passed parameters.
	 * @author ZZiad
	 */
	public String getOffers(HotelOffersRequest hotelOffersRequest);
}
