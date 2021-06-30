package com.optimgov.spring.elearning.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.optimgov.spring.elearning.models.Teacher;
import com.optimgov.spring.elearning.repository.TeacherRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
	@Autowired
    private TeacherRepository teacherRepository;
	@GetMapping("/list")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ArrayList<Teacher>>getTeachersList() {
		 try {
			 ArrayList<Teacher> teachers = new ArrayList<Teacher>();
			 teacherRepository.findAll().forEach(teachers::add);
			 if (teachers.isEmpty()) {
			        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			      }

			      return new ResponseEntity<>(teachers, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	@GetMapping("/teacher")
	@PreAuthorize("hasRole('TEACHER')")
	public String moderatorAccess() {
		return "Teacher Board.";
	}
}
