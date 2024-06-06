package com.imranmadbar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/authenticate")
	public ResponseEntity createAuthenticationToken(@RequestBody AuthRequest authRequest) throws Exception {

		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		System.out.println("LOOG_USER_DETAILS: " + userDetails);
		final String jwt = jwtUtil.generateToken(userDetails.getUsername());

		System.out.println("JWT: " + jwt);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));

	}
}
