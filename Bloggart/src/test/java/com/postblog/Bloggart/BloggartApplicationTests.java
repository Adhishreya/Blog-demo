package com.postblog.Bloggart;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.MethodMode;

import com.postblog.Bloggart.dto.UserDto;
import com.postblog.Bloggart.exceptions.EmailAlreadyExistsException;
import com.postblog.Bloggart.service.UserService;

@SpringBootTest
class BloggartApplicationTests {

	private UserDto userDto = new UserDto();
	@Autowired
	private UserService userService;

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
