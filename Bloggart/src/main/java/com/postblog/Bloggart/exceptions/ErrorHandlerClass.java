package com.postblog.Bloggart.exceptions;

import java.lang.System.Logger.Level;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorHandlerClass {

	@ExceptionHandler(NoHandlerFoundException.class)
	public String noResourceHandler(NoHandlerFoundException exception) {
		System.out.println("404 errrroe");
		return "redirect:/error";
	}
}
