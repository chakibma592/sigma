package com.optimgov.spring.elearning.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.optimgov.spring.elearning.models.ERole;
import com.optimgov.spring.elearning.models.Role;
import com.optimgov.spring.elearning.models.Teacher;
import com.optimgov.spring.elearning.models.User;
import com.optimgov.spring.elearning.payload.request.LoginRequest;
import com.optimgov.spring.elearning.payload.request.SignupRequest;
import com.optimgov.spring.elearning.payload.response.JwtResponse;
import com.optimgov.spring.elearning.payload.response.MessageResponse;
import com.optimgov.spring.elearning.repository.RoleRepository;
import com.optimgov.spring.elearning.repository.TeacherRepository;
import com.optimgov.spring.elearning.repository.UserRepository;
import com.optimgov.spring.elearning.security.jwt.JwtUtils;
import com.optimgov.spring.elearning.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;
	@Autowired
	TeacherRepository teacherRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));
		Teacher teacher=null;

		//Set<String> strRoles = signUpRequest.getRole();
		String strRoles = signUpRequest.getPrevilege();
		Set<Role> roles = new HashSet<>();
		

		if (strRoles.equals("user")) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else if (strRoles.equals("admin")){
			
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);
		
		}
		else if(strRoles.equals("teacher")) {
			Role adminRole = roleRepository.findByName(ERole.ROLE_TEACHER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(adminRole);
		}
		
  user.setRoles(roles);
 Role r=roleRepository.findByName(ERole.ROLE_TEACHER).get();
  if(roles.contains(r)) {
	teacher= new Teacher();
	teacher.setUsername(user.getUsername());
	teacher.setPassword(user.getPassword());
	teacher.setEmail(user.getEmail());
	teacherRepository.save(teacher);
  }
  else	{	
  userRepository.save(user);
  }

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
