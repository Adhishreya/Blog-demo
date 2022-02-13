package com.postblog.Bloggart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.postblog.Bloggart.entity.PostEntity;
import com.postblog.Bloggart.entity.UserEntity;
import com.postblog.Bloggart.service.LikesService;
import com.postblog.Bloggart.service.PostService;
import com.postblog.Bloggart.service.UserService;

@Controller
public class LikesController {

//	@Autowired
//	private UserService userService;
//
	@Autowired
	private PostService postService;

	@Autowired
	private LikesService likesService;

	@RequestMapping("/liked/{postId}/byUser/{userId}")
	public String likes(@PathVariable("postId") Long postId, @PathVariable("userId") String successName) {
		likesService.save(postId, successName);
		System.out.println("postId " + postId + " userid " + successName);
		return "redirect:/loginSuccess";
	}

	@RequestMapping("/removeLike/{postId}/byUser/{userId}")
	public String likesRemove(@PathVariable("postId") Long postId, @PathVariable("userId") String successName) {
		likesService.removeLike(postId, successName);
		System.out.println("postId " + postId + " userid " + successName);
		return "redirect:/loginSuccess";
	}

	@RequestMapping(value = "/likedBy/{postId}", method = RequestMethod.GET)
	public ModelAndView findLikedBy(@PathVariable("postId") Long id) {
		PostEntity pe = postService.findById(id);
		List<Object[]> likes = likesService.findLikedBy(pe);
		System.out.println("count is " + likes.size());
		ModelAndView model = new ModelAndView();
		model.addObject("likes", likes);
		model.setViewName("likes");
		return model;
	}
}
