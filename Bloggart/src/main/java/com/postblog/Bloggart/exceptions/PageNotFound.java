package com.postblog.Bloggart.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

//public class PageNotFound extends Exception{
//	public PageNotFound() {
//		super("Page does not Exists 404");
//	}
//}

//@ResponseStatus(value = HttpStatus.NOT_FOUND)
//public class PageNotFound extends RuntimeException {
//	public PageNotFound() {
//		super();
//	}
//
//	public PageNotFound(String message) {
//		super(message);
//	}
//
//	public PageNotFound(String message, Throwable clause) {
//		super(message, clause);
//	}
//
//	public PageNotFound(Throwable clause) {
//		super(clause);
//	}
//}

@ControllerAdvice
public class PageNotFound extends ResponseEntityExceptionHandler {
	@ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class }) // responding to the
																								// specified set of
																								// exception encountered
	public ResponseEntity<Object> handleConflict(RuntimeException exception, WebRequest request) {
		String bodyOfResponse = "This should be application specific";
		return handleExceptionInternal(exception, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

}