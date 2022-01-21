package com.postblog.Bloggart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.postblog.Bloggart.dto.UserDto;
import com.postblog.Bloggart.service.UserService;

@Controller
//@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/user/register")
	public String registerUser(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "register";

	}

	@GetMapping("/user/login")
	public String loginUser(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "login";

	}
	@PostMapping("/register/save")
	public ModelAndView saveUser(@ModelAttribute("user") @Valid UserDto userDto,HttpServletRequest request,Errors errors) {
//		userService.save(userDto);
//		return "redirect:/home";
		UserEntity user = 
		
	}
//	
}
