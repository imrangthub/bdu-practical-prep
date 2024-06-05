package com.imranmadbar.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.imranmadbar.user.UserEntity;
import com.imranmadbar.user.UserService;

@Service
public class BaseService {

	@Autowired
	private UserService userService;

	public UserEntity getUserInof() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		return userService.findByUserName(username);

	}

}
