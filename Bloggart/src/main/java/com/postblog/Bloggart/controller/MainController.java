package com.postblog.Bloggart.controller;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.postblog.Bloggart.entity.PostEntity;
import com.postblog.Bloggart.service.PostService;
import com.sun.net.httpserver.HttpsServer;

@Controller
public class MainController implements ErrorController {
	
	RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	@Autowired
	private PostService postService;
	
//	implements ErrorController
	@ModelAttribute("successName")
	private String setSessionName() {
		return "";
	}
	
	@GetMapping("/home")
	public ResponseEntity<List<List<PostEntity>>> sendTemplate() {
		List<PostEntity> postList = postService.postFindAll();
		ResponseEntity<List<List<PostEntity>>>  response = new ResponseEntity(postList,HttpStatus.OK);
		return response;
	}
	
//	@GetMapping("/home")
//	public String homePageRequest() {
//		return "redirect:/home/post";
//	}

	@GetMapping("/")
	public String defaultPage() {
		return "redirect:/home";
	}

	@GetMapping("/erorPage")
	public String errorRequest() {
		return "pagenotfound";
	}

	@GetMapping("/trending")
	public String trendingRender() {
		return "trending";
	}

	@RequestMapping("/error")
	public String handleError(HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		System.out.println(status);
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			if (statusCode == HttpStatus.NOT_FOUND.value())
				return "error/404";
			else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
				return "error/server";
		}
		return "error/404";
	}

	@ExceptionHandler(Exception.class)
	public String genericErrorMethod(Exception exception) {
		return "redirect:/error";
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public String noResourceHandler(NoHandlerFoundException exception) {
		return "redirect:/error";
	}
}
