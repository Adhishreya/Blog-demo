package com.postblog.Bloggart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.postblog.Bloggart.entity.CommentEntity;
import com.postblog.Bloggart.entity.PostEntity;
import com.postblog.Bloggart.entity.UserEntity;
import com.postblog.Bloggart.service.CommentService;
import com.postblog.Bloggart.service.PostService;
import com.postblog.Bloggart.service.UserService;

@Controller
@SessionAttributes({ "successName", "id" })
public class CommentController {

	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;

	@Autowired
	private CommentService commentService;

	@ModelAttribute("successName")
	public String getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			String currentUserName = authentication.getName();
			System.out.println("logged in s    " + currentUserName);
			return currentUserName;
		}
		return "asdasdasdasd";
	}

	@RequestMapping(value = "/comment", method = RequestMethod.GET)
	public ModelAndView comment(@RequestParam("id") Long id, Model model,
			@ModelAttribute("successName") String successName) {
		model.addAttribute("id", id);
		System.out.println("commenting " + successName);
		ModelAndView modelAndView = new ModelAndView("comment", "commentObject", new CommentEntity());
//		modelAndView.addObject("successName", successName);
		return modelAndView;
	}

	@RequestMapping(value = "/comment/save", method = RequestMethod.POST)
	public String commentSave(@ModelAttribute("commentObject") CommentEntity commentEntity,
			@ModelAttribute("successName") String email, @ModelAttribute("id") Long id, Model model) {
		UserEntity entity = userService.findByEmail(email);

		PostEntity postEntity = postService.findById(id);
		commentEntity.setPost(postEntity);
		commentEntity.setUser(entity);
		commentService.save(commentEntity);

		System.out.println(commentEntity);
		return "redirect:/loginSuccess/post";
	}

	@RequestMapping(value = "/commentList/{id}", method = RequestMethod.GET)
	public ModelAndView getCommentsByPost(@PathVariable("id") Long id,
			@ModelAttribute("successName") String successName) {
		System.out.println("name is  " + successName);
		PostEntity post = postService.findById(id);
		List<CommentEntity> comments = commentService.findAllByPost(post);
		System.out.println(comments);
		ModelAndView model = new ModelAndView();
		model.addObject("commentsE", comments);
		model.addObject("postE", post);
		model.setViewName("commentList");
		model.addObject("successName", successName);
		return model;
	}

	@RequestMapping(value = "/deleteComment", method = RequestMethod.GET)
	public String deleteComment(@RequestParam("id") Long id) {
		commentService.deleteById(id);
		return "redirect:/loginSuccess/post";
	}
}
