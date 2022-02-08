package com.postblog.Bloggart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(value = "/comment", method = RequestMethod.GET)
	public ModelAndView comment(@RequestParam("id") Long id, Model model) {
		model.addAttribute("id", id);
		return new ModelAndView("comment", "commentObject", new CommentEntity());
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
}
