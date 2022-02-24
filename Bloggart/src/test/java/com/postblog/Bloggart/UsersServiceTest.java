package com.postblog.Bloggart;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.AssertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;
import org.springframework.test.context.junit4.SpringRunner;

import com.postblog.Bloggart.dto.UserDto;
import com.postblog.Bloggart.exceptions.EmailAlreadyExistsException;
import com.postblog.Bloggart.service.UserService;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class UsersServiceTest {

	private static ValidatorFactory validatorFactory;
	private static Validator validator;

	private UserDto userDto = new UserDto();

	@Autowired
	private UserService userService;

	@BeforeEach
	public void generateValidatorTests() {
		validatorFactory = Validation.buildDefaultValidatorFactory();
		validator = validatorFactory.getValidator();
	}

	@Test
	@DisplayName("Checking for validity errors")
	public void checkValidityErrors() {
		userDto.setEmail("123#@#");
		userDto.setPassword("abcdefgh");
		userDto.setUsername("qwerty");
		Set<ConstraintViolation<UserDto>> validations = validator.validate(userDto);
		System.out.println(validations);
		Assertions.assertTrue(validations.size() > 0);
	}

	@Test
	@DisplayName("Check for existing email")
	@DirtiesContext(methodMode = MethodMode.BEFORE_METHOD)
	public void existEmailUser() {
		userDto.setEmail("demouser");
		userDto.setPassword("abcdefgh");
		userDto.setEmail("demo_user@test.com");
		Exception exception = Assertions.assertThrows(EmailAlreadyExistsException.class,
				() -> userService.save(userDto));
//		Assertions.assertEquals("This email already exists", exception.getMessage());
		Assertions.assertTrue(exception.getMessage().contains("This email already exists"));
	}

}
