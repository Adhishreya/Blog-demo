package com.postblog.Bloggart.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.postblog.Bloggart.entity.PostEntity;

@Controller
public class MainController {
	@GetMapping("/home")
	public String homePageRequest() {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (!(authentication instanceof AnonymousAuthenticationToken)) {
//			String currentUserName = authentication.getName();
////			return currentUserName;
//			System.out.println(authentication);
//		}
		return "home";
	}

	@GetMapping("/")
	public String defaultPage() {
		return "home";
	}

	@GetMapping("/erorPage")
	public String errorRequest() {
		return "pagenotfound";
	}

	@GetMapping("/trending")
	public String trendingRender() {
		return "trending";
	}

	@GetMapping("/error")
	public String pageNotFound() {
		return "pagenotfound";
	}

}
