package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private CustomAccessDeniedHandler customAccessDeniedHandler;
	
	@Autowired
	private CustomAuthenticationProvider customAuthenticationProvider;
	
	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
	
	@Autowired
	private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
	

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(customAuthenticationProvider);
    }
    

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers("/home/**").permitAll()
        .antMatchers("/user/**").permitAll()
        .antMatchers("/supervisor/**").permitAll()
        //.antMatchers("/user/**").hasAnyRole("USER","SUPIRVISOR")
       // .antMatchers("/manager/**").hasAnyRole("MANAGER")
      //  .antMatchers("/hr/**").hasAnyRole("HR")
       // .antMatchers("/supervisor/**").hasAnyRole("SUPIRVISOR")
        .antMatchers("/anonymous*").anonymous()
        .antMatchers("/login/").permitAll()
        .antMatchers("/resources/**", "/static/**").permitAll()
        .and()
            .formLogin()
            .loginPage("/login")
            .permitAll()
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(customAuthenticationSuccessHandler)
			.failureHandler(customAuthenticationFailureHandler)
        .and()
            .logout()
            .logoutSuccessUrl("/home")
            .invalidateHttpSession(true)
            .permitAll()
        .and()
        .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
        .and()
            .csrf()
            .disable();
    }
	

	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
	 return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}

	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return new
	 * BCryptPasswordEncoder(); }
	 */
	

}
