package com.postblog.Bloggart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.postblog.Bloggart.entity.PostEntity;
import com.postblog.Bloggart.entity.UserEntity;
import com.postblog.Bloggart.service.PostService;
import com.postblog.Bloggart.service.UserService;

@SessionAttributes({ "successName" })
@Controller
public class PostController {

	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;

//	@PostMapping("/load")
//	public String savePost(@ModelAttribute("post")PostEntity postEntity) {
//		System.out.println();
//		
//	}
//	

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView editPageRequest(@ModelAttribute("successName") String email) {
		ModelAndView modelAndView = new ModelAndView("edit", "post", new PostEntity());
		UserEntity entity = userService.findByEmail(email);
		postService.postList(entity);
		
		return modelAndView;
	}

	@RequestMapping(value = "/edit/save", method = RequestMethod.POST)
	public String savePost(@ModelAttribute("post") PostEntity postEntity, @ModelAttribute("successName") String email) {
		UserEntity entity = userService.findByEmail(email);
		postEntity.setUser(entity);
		postService.savePost(postEntity);
		return "home";
	}
	
//	@RequestMapping(value ="/loginSuccess/post", method = RequestMethod.GET)
//	public String onLoadLogin
}
