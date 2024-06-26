package com.imranmadbar;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		List<GrantedAuthority> authoritiesList = new ArrayList<GrantedAuthority>();
		
		if(userName.equals("admin")){
		    authoritiesList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			authoritiesList.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new User("admin", "admin", authoritiesList);
		}
		
		if(userName.equals("user")){
			authoritiesList.add(new SimpleGrantedAuthority("ROLE_USER"));
			return new User("user", "user", authoritiesList);
		}
		
		if(userName.equals("imran")){
			authoritiesList.add(new SimpleGrantedAuthority("ROLE_USER"));
			authoritiesList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			authoritiesList.add(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"));
			return new User("imran", "12345", authoritiesList);
		}

        return null;
	}
}
