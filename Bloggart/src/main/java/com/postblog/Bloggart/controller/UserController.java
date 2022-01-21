package com.postblog.Bloggart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import com.postblog.Bloggart.dto.UserDto;
import com.postblog.Bloggart.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/register")
	public String registerUser(WebRequest request , Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user",userDto);
		return "register";
		
	}
//	@PostMapping("/register/save")
//	public String saveUser(@ModelAttribute("user") @Valid UserDto userDto) {
//		userService.save(userDto);
//		return "redirect:/home";
//	}
//	
}
