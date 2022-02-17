package com.postblog.Bloggart.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.postblog.Bloggart.dto.UserDto;
import com.postblog.Bloggart.entity.UserEntity;
import com.postblog.Bloggart.exceptions.EmailAlreadyExistsException;
import com.postblog.Bloggart.service.UserService;

@Controller
@SessionAttributes({ "user", "successName" })
public class UserController {

	@Autowired
	UserService userService;

	@ModelAttribute("successName")
	private String setSessionName() {
		return "";
	}

	@ModelAttribute("updateUser")
	public UserDto updateUser() {
		return new UserDto();
	}

	@PostMapping("/twitter/save")
	public String setTwitter(@ModelAttribute("updateUser") UserDto twitter,
			@ModelAttribute("successName") String email) {
		userService.updateTwitterId(twitter.getTwitterId(), email);
		return "redirect:/profile";
	}

	@PostMapping("/bio/save")
	public String setBio(@ModelAttribute("updateUser") UserDto bio, @ModelAttribute("successName") String email) {
		userService.updateBio(bio.getBio(), email);
		return "redirect:/profile";
	}

	@PostMapping("/image/save")
	public String setImage(@ModelAttribute("updateUser") UserDto image, @ModelAttribute("successName") String email) {
		userService.updateTwitterId(image.getImage(), email);
		return "redirect:/profile";
	}

	@PostMapping("/instagram/save")
	public String setInstagram(@ModelAttribute("updateUser") UserDto instagram,
			@ModelAttribute("successName") String email) {
		userService.updateInstagramId(instagram.getInstagramId(), email);
		return "redirect:/profile";
	}

	@GetMapping("/loginSuccess")
	public String homePageRequest(Model model, Authentication authentication) {
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

	@RequestMapping(value = "/user/register/save", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute("user") @Valid UserDto userDto, BindingResult bindingResult,
			HttpServletRequest request, Errors errors) {

		if (bindingResult.hasErrors()) {
			return new ModelAndView("register", "user", userDto);
		}
		ModelAndView modelAndView = new ModelAndView();
		try {
			UserEntity userEntity = userService.save(userDto);

		} catch (EmailAlreadyExistsException e) {
			modelAndView.addObject("message", e.getMessage());
			modelAndView.setViewName("register");
			return modelAndView;
		}
		return new ModelAndView("redirect:/login", "user", userDto.getUsername());

	}

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public ModelAndView authenticateUser(@ModelAttribute("successName") String email) {
		UserEntity userE = userService.findByEmail(email);
		return new ModelAndView("user", "userE", userE);
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable("id") Long id) {
		userService.deleteById(id);
		return "redirect:/home";
	}

	@RequestMapping(value = "/username", produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public String getUserDetailsAuthenticate(Authentication authentication) {
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		return authentication.getName();
	}

	@ExceptionHandler(Exception.class)
	public String genericErrorMethod(Exception exception) {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("error",exception.getMessage());
//		mav.setViewName("error/404");
//		return mav;
		return "redirect:/error";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public String noResourceHandler(NoHandlerFoundException exception) {
		return "redirect:/error";
	}
}
