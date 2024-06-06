package com.imranmabar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.imranmabar.blog.BlogService;

@Controller
public class HomeController {

	@Autowired
	private BlogService blogService;

	@GetMapping("/home")
	public String Home(Model model) {
		 model.addAttribute("postList",blogService.getAllBlogs());
		return "home";
	}

	@GetMapping("/")
	public String index(Model model) {
		 model.addAttribute("postList",blogService.getAllBlogs());
		return "home";
	}

}
