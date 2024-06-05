package com.imranmadbar.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.imranmadbar.user.UserEntity;
import com.imranmadbar.user.UserService;


@Controller
public class AuthController {
	

	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String userLogin() {
		return "auth/loginPage";
	}
	
	@GetMapping("/access-denied")
	public String accessDenied() {
		return "auth/accessDenied";
	}
	
	
	@GetMapping("/registration/create")
	public String create(Model model) {
		UserEntity obj = new UserEntity();
		model.addAttribute("userObj", obj);
		return "user/userCreate";
	}

	@PostMapping("/registration/save")
	public String saveuser(UserEntity userObj, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "user/userCreate";
		}
		userService.userSave(userObj);
		return "redirect:/user/home";
	}

}

