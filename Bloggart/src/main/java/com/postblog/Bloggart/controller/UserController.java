package com.postblog.Bloggart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.postblog.Bloggart.dto.UserDto;
import com.postblog.Bloggart.entity.UserEntity;
import com.postblog.Bloggart.exceptions.EmailAlreadyExistsException;
import com.postblog.Bloggart.service.UserService;

@Controller
@SessionAttributes({"user","successName"})
public class UserController {

	@Autowired
	UserService userService;

//	@GetMapping("/loginSuccess")
//	public ModelAndView homePageRequest(Authentication authentication) {
//		ModelAndView model = new ModelAndView();
//		System.out.println("authentications is " + authentication.getPrincipal());
//		model.addObject("successName", authentication.getName());
//		model.setViewName("loginSuccess");
//		return model;
//	}
	
	@GetMapping("/loginSuccess")
	public String homePageRequest(Model model,Authentication authentication) {
		System.out.println("authentications is " + authentication);
		model.addAttribute("successName", authentication.getName());
		return "redirect:/loginSuccess/post";
	}

	@GetMapping("/user/register")
	public String registerUser(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "register";

	}

	@GetMapping("/login")
	public String loginUser(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
		model.addAttribute("user", userDto);
		return "login";

	}

//	@PostMapping("/user/register/save")
	@RequestMapping(value = "/user/register/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult,
			HttpServletRequest request, Errors errors) {

		if (bindingResult.hasErrors()) {
			return new ModelAndView("register", "user", userDto);
		}

		System.out.println(errors);
		ModelAndView modelAndView = new ModelAndView();
		try {
			UserEntity userEntity = userService.save(userDto);

		} catch (EmailAlreadyExistsException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("register");
			return modelAndView;
		}
		return new ModelAndView("home", "user", userDto.getUsername());

	}
//	
//	@RequestMapping(value="/user/login/verify",method=RequestMethod.POST)
//	public ModelAndView authenticateUser(@ModelAttribute("user")UserDto userDto) {
////		User
//	}
//	
}
