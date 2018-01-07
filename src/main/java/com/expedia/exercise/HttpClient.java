package com.expedia.exercise;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * HTTP Client class created to simplify HTTP requests.
 *
 * @author ZZiad
 */
public class HttpClient {

	private static final String CONTENT_TYPE = "Content-Type";
	
	private static DefaultHttpClient httpClient;
	
	public static HttpResponse sendGet(String url, String params, String contentType) throws ClientProtocolException, IOException {

		if (params != null) {
			url = url + params;
		}
		httpClient = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		request.addHeader(CONTENT_TYPE, contentType);
		return  httpClient.execute(request);
	}
}