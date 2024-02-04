package br.com.perinity.api.util;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.WebApplicationException;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<WebApplicationException> {

	@Override
	public Response toResponse(WebApplicationException exception) {
		return Response.status(Status.BAD_REQUEST).entity(exception.getMessage()).build();
	}
}