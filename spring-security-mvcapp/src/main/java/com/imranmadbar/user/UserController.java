package com.imranmadbar.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/user/home")
	public String index(Model model) {
		model.addAttribute("currentUser", userService.getUserInof());
		model.addAttribute("userList", userService.findAll());
		return "user/home";
	}

	@GetMapping("/user/status/create")
	public String userStatus(Model model) {
		UserEntity obj = new UserEntity();
		model.addAttribute("userObj", obj);
		return "user/userCreate";
	}

}
