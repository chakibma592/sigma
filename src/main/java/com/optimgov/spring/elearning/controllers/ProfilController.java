package com.optimgov.spring.elearning.controllers;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimgov.spring.elearning.models.ERole;
import com.optimgov.spring.elearning.models.Profession;
import com.optimgov.spring.elearning.models.Role;
import com.optimgov.spring.elearning.models.StudiesLevel;
import com.optimgov.spring.elearning.models.Teacher;
import com.optimgov.spring.elearning.models.User;
import com.optimgov.spring.elearning.payload.request.ProfilRequest;
import com.optimgov.spring.elearning.payload.request.SignupRequest;
import com.optimgov.spring.elearning.payload.response.MessageResponse;
import com.optimgov.spring.elearning.repository.ProfessionRepository;
import com.optimgov.spring.elearning.repository.RoleRepository;
import com.optimgov.spring.elearning.repository.StudiesLevelRepository;
import com.optimgov.spring.elearning.repository.TeacherRepository;
import com.optimgov.spring.elearning.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profil")
public class ProfilController {
	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private ProfessionRepository professionRepository;
	@Autowired
    private StudiesLevelRepository levelRepository;
	@Autowired
    private RoleRepository roleRepository;
	@Autowired
    private TeacherRepository teacherRepository;
	@PutMapping("/update/{id}")
	public ResponseEntity<MessageResponse> updateProfil(@PathVariable("id") long id,@RequestBody ProfilRequest profilrequest) {
				
			
			Set<String> strRoles = profilrequest.getRole();
			Set<Role> roles = new HashSet<>();
			if (strRoles == null) {
				Role userRole = roleRepository.findByName(ERole.ROLE_USER)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				roles.add(userRole);
			} else {
				strRoles.forEach(role -> {
					switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);
					

						break;
					case "teacher":
						
						Role modRole = roleRepository.findByName(ERole.ROLE_TEACHER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(modRole);
	                   
						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
					}
				});
			}
	  
	  if(roles.contains(roleRepository.findByName(ERole.ROLE_TEACHER).get())) {
		    Teacher teacher = teacherRepository.findByTeacherId(id);
		    teacher.setFirstname(profilrequest.getFirstname());
		    teacher.setLastname(profilrequest.getLastname());
		    teacher.setBirthday(profilrequest.getBirthday());
			Optional<Profession> optionnalprofession= professionRepository.findById(profilrequest.getProfessionid());
			if(optionnalprofession.isPresent()) {				
			Profession profession=optionnalprofession.get();
			teacher.setProfession(profession);}
			Optional<StudiesLevel> optionnallevel= levelRepository.findById(profilrequest.getLevelid());
			if(optionnallevel.isPresent()) {
			StudiesLevel level=optionnallevel.get();
			teacher.setStudieslevel(level);}
			teacher.setRoles(roles);
			teacher.setBiographie(profilrequest.getBiographie());
			teacherRepository.save(teacher);
		  return new ResponseEntity<>(new MessageResponse("Teacher updated succesfully"), HttpStatus.OK);
	  }
	  else if(roles.contains(roleRepository.findByName(ERole.ROLE_USER).get())) {
		  User user = userRepository.findByUserId(id);
		    user.setFirstname(profilrequest.getFirstname());
		    user.setLastname(profilrequest.getLastname());
		    user.setBirthday(profilrequest.getBirthday());
			Optional<Profession> optionnalprofession= professionRepository.findById(profilrequest.getProfessionid());
			if(optionnalprofession.isPresent()) {
				
			Profession profession=optionnalprofession.get();
			user.setProfession(profession);}
			Optional<StudiesLevel> optionnallevel= levelRepository.findById(profilrequest.getLevelid());
			if(optionnallevel.isPresent()) {
			StudiesLevel level=optionnallevel.get();
			user.setStudieslevel(level);}
			user.setRoles(roles);
		  userRepository.save(user);
		  return new ResponseEntity<>(new MessageResponse("User updated succesfully"), HttpStatus.OK);
	  }
	  else if(roles.contains(roleRepository.findByName(ERole.ROLE_ADMIN).get())) {
		  //userRepository.save(user);
		  return new ResponseEntity<>(new MessageResponse("Admin updated succesfully"), HttpStatus.OK);
	  }
			
		//} else {
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//}
	}
	@PutMapping("/passwordupdate/{id}")
	public ResponseEntity<?> updatepasswordUser(@PathVariable("id") long id,@RequestBody SignupRequest signUpRequest) {
		User user = userRepository.findByUserId(id);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(signUpRequest.getUsername(), signUpRequest.getPassword()));
		
		
		if(authentication.isAuthenticated()) {
			user.setPassword(encoder.encode(signUpRequest.getNewpassword()));
			userRepository.save(user);
			return ResponseEntity.ok(new MessageResponse("password changed successfully!"));
		}
		else {
			return ResponseEntity.ok(new MessageResponse("Old password not matchs"));
		}

		
		
	}

}
