package com.inpt.spring.note.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inpt.spring.note.models.User;
import com.inpt.spring.note.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
    private UserRepository userRepository;
	@GetMapping("/list")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ArrayList<User>>getUsersList() {
		 try {
			 ArrayList<User> users = new ArrayList<User>();
			 userRepository.findAll().forEach(users::add);
			 if (users.isEmpty()) {
			        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			      }

			      return new ResponseEntity<>(users, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	@PutMapping("/enable/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> enableUser(@PathVariable("id") long id) {
		Optional<User> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			User user = userData.get();
			user .setActivate(true);
			return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/disable/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> disableUser(@PathVariable("id") long id) {
		Optional<User> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			User user = userData.get();
			user .setActivate(false);
			return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('TEACHER') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	

	@GetMapping("/administrator")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
