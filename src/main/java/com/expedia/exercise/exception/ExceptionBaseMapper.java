package com.expedia.exercise.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.expedia.exercise.bean.ErrorMessage;

/**
 * This class will catch unhandled {@link Exception}.
 *
 * @author ZZiad
 */
@Provider
public class ExceptionBaseMapper implements ExceptionMapper<Exception> {
	
	public ExceptionBaseMapper() {
	}
	
	@Override
	public javax.ws.rs.core.Response toResponse(Exception exception) {
		return Response.status(Status.INTERNAL_SERVER_ERROR).entity(new ErrorMessage(exception.getMessage())).build();
	}
}
