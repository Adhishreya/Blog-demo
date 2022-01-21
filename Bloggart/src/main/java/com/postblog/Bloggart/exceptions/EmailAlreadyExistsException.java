package com.postblog.Bloggart.exceptions;

public class EmailAlreadyExistsException extends Exception {

	public EmailAlreadyExistsException() {
		super("This email already exists");
	}

}
