package com.postblog.Bloggart.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomEmailValidator implements ConstraintValidator<ValidEmail, String> {
	private Pattern pattern;
	private Matcher matcher;

//	private final String emailPattern = "(_a-zA-Z0-9-+)+" + "(.a-zA-Z0-9)*@" + "(_a-zA-Z0-9)+(.a-zA-Z0-9)*"
//			+ "(.a-zA-Z0-9)$";
	private static final String emailPattern = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$"; 

	@Override
	public void initialize(ValidEmail constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return checkPattern(value);
	}

	public boolean checkPattern(String email) {
		pattern = Pattern.compile(emailPattern);
		matcher = pattern.matcher(email);
		return matcher.matches();
	}

}
