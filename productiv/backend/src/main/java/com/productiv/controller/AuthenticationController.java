package com.productiv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productiv.dto.LoginDTO;
import com.productiv.repository.UserRepository;
import com.productiv.service.UserService;

//import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1")
public class AuthenticationController {

	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private PasswordEncoder passwordEncoder;

//	@PostConstruct
//	void setGlobalSecurityContext() {
//	  SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
//	}

	@PostMapping("/login")
	public ResponseEntity<String> getUser(HttpServletRequest request, @RequestBody LoginDTO loginDTO) {

//		request.getSession(true);

//		SecurityContextHolder.setStrategyName(SecurityContextHolder.MODE_INHERITABLETHREADLOCAL);
		// Authenticate the user
//		request.getSession(true);

//		try {
//			request.login(loginDTO.getUsername(), loginDTO.getPassword());
//			
//		} catch (ServletException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		Authentication authentication = this.authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
		SecurityContext securityContext = SecurityContextHolder.getContext();
		securityContext.setAuthentication(authentication);
//        request.getSession().toString();
//        System.out.println(request.getSession());
//        
//        UserDetails object = userService.loadUserByUsername(loginDTO.getUsername());
//        
//        
//        System.out.println(object);

		return new ResponseEntity<String>("Welcome, " + loginDTO.getUsername() + "!", HttpStatus.OK);

//		return  ResponseEntity.status(400).body("Some error occurred.");
	}

	@GetMapping("/currentUser")
	public ResponseEntity<String> getCurrentUser(Authentication authentication) {

		// SecurityContextHolder.getContext().getAuthentication().getName();

		String username = authentication.getName();

		System.out.println(username);

//		HttpSession session2 = (HttpSession) request.getUserPrincipal();
//		HttpSession session = request.getSession(true);
//		System.out.println(session.getAttribute("SPRING_SECURITY_CONTEXT"));

//		System.out.println(session2);
//SecurityContextHolder.getContext().getAuthentication().getName();

		return new ResponseEntity<String>("Welcome", HttpStatus.OK);
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(@RequestBody LoginDTO loginDTO) {

		return new ResponseEntity<String>("Welcome, " + loginDTO.getUsername() + "!", HttpStatus.OK);

	}

	@PostMapping("/registeration")
	public ResponseEntity<String> registration(@RequestBody LoginDTO loginDTO) {

		return new ResponseEntity<String>("Welcome, " + loginDTO.getUsername() + "!", HttpStatus.OK);

	}
}