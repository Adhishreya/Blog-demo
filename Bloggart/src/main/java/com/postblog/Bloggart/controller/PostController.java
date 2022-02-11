package com.postblog.Bloggart.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.postblog.Bloggart.dto.UserDto;
import com.postblog.Bloggart.entity.PostEntity;
import com.postblog.Bloggart.entity.UserEntity;
import com.postblog.Bloggart.service.PostService;
import com.postblog.Bloggart.service.UserService;

@SessionAttributes({ "successName", "postList" })
@Controller
public class PostController {

	@Autowired
	private UserService userService;

	@Autowired
	private PostService postService;

	@ModelAttribute("successName")
	private String setSessionName() {
		return "";
	}
//	private String name;

	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public ModelAndView editPageRequest(@ModelAttribute("successName") String email, Model model) {
		ModelAndView modelAndView = new ModelAndView("edit", "post", new PostEntity());

		UserEntity entity = userService.findByEmail(email);

		return modelAndView;
	}

	@RequestMapping(value = "/edit/save", method = RequestMethod.POST)
	public String savePost(@ModelAttribute("post") @Valid PostEntity postEntity,
			@ModelAttribute("successName") String email) {
		UserEntity entity = userService.findByEmail(email);
//		entity.getPost().add(postEntity);
//		UserDto userDto = new UserDto();
//		BeanUtils.copyProperties(userDto, entity);
		postEntity.setUser(entity);
		postService.savePost(postEntity);
//		userService.update(entity);
		return "redirect:/loginSuccess/post";
	}

	@RequestMapping(value = "/loginSuccess/post", method = RequestMethod.GET)
	public ModelAndView onLoadLogin(@ModelAttribute("successName") String email) {
		UserEntity entity = userService.findByEmail(email);
		List<PostEntity> postList = postService.postFindAll();
		ModelAndView modelAndView = new ModelAndView("loginSuccess", "postList", postList);
		return modelAndView;
	}

	@RequestMapping(value = "/home/post", method = RequestMethod.GET)
	public ModelAndView homeRender() {
		List<PostEntity> postList = postService.postFindAll();
		ModelAndView modelAndView = new ModelAndView("home", "postList", postList);
		return modelAndView;
	}

	@RequestMapping(value = "/deletePost", method = RequestMethod.GET)
	public String deletePost(@RequestParam("id") Long id) {
		postService.deleteById(id);
		return "redirect:/loginSuccess/post";
	}
}
