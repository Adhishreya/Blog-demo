package com.postblog.Bloggart.controller;

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
//		model.addAttribute("m1","m2");
		return "home";
	}

//	@GetMapping("/edit")
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView editPageRequest() {
		ModelAndView modelAndView = new ModelAndView("edit", "post", new PostEntity());
		return modelAndView;
	}
}
