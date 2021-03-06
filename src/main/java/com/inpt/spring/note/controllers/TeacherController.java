package com.inpt.spring.note.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inpt.spring.note.models.ERole;
import com.inpt.spring.note.models.Filiere;
import com.inpt.spring.note.models.Note;
import com.inpt.spring.note.models.Role;
import com.inpt.spring.note.models.Student;
import com.inpt.spring.note.models.Teacher;
import com.inpt.spring.note.payload.request.StudentRequest;
import com.inpt.spring.note.payload.response.MessageResponse;
import com.inpt.spring.note.payload.response.TeacherResponse;
import com.inpt.spring.note.repository.RoleRepository;
import com.inpt.spring.note.repository.TeacherRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
	@Autowired
    private TeacherRepository teacherRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	AuthenticationManager authenticationManager;
	@GetMapping("/list1")
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
	@PostMapping("/add")
	public ResponseEntity<?> addTeacher(@Valid @RequestBody StudentRequest studentRequest) {
		
		if (teacherRepository.existsByEmail(studentRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		//Optional<Filiere> optionnalfiliere= filiereRepository.findById(studentRequest.getFiliereid());
		//Filiere filiere=optionnalfiliere.get();
		Teacher teacher=null;
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName(ERole.ROLE_TEACHER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
		teacher= new Teacher();
		teacher.setRoles(roles);
		teacher.setUsername(studentRequest.getEmail());
		teacher.setPassword( encoder.encode(studentRequest.getFirstname()+studentRequest.getLastname()));
		teacher.setEmail(studentRequest.getEmail());
		//student.setRoles(roles);
		teacher.setFirstname(studentRequest.getFirstname());
		teacher.setLastname(studentRequest.getLastname());
		teacher.setBirthday(studentRequest.getBirthday());
		teacher.setNumtph(studentRequest.getNumtph());
		//student.setFiliere(filiere);
		teacherRepository.save(teacher);		
  		return ResponseEntity.ok(new MessageResponse("Teacher registered successfully!"));
	}
	@GetMapping("/list")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ArrayList<TeacherResponse>>getStudentsList() {
		 try {
			 ArrayList<Teacher> teachers = new ArrayList<Teacher>();
			 ArrayList<TeacherResponse> teachersresponse = new ArrayList<TeacherResponse>();
			 teacherRepository.findAll().forEach(teachers::add);
			 if (teachers.isEmpty()) {
			        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			      }
			 for(Teacher t : teachers) {
				 TeacherResponse teach= new TeacherResponse();
				 teach.setId(t.getId());
				 teach.setBirthday(t.getBirthday());
				 teach.setEmail(t.getEmail());
				 teach.setFirstname(t.getFirstname());
				 teach.setLastname(t.getLastname());
				 teach.setNumtph(t.getNumtph());
				 teachersresponse.add(teach);
			 }
			      return new ResponseEntity<>(teachersresponse, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	@GetMapping("/findteacher/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<TeacherResponse>getTeacher(@PathVariable("id") long id) {
		 try {
			 Teacher teacher = new Teacher();
			 TeacherResponse teacherresponse= new TeacherResponse();
			 Optional<Teacher> optionalTeacher  = teacherRepository.findById(id);
			 teacher=optionalTeacher.get();
			 teacherresponse.setId(teacher.getId());
			 teacherresponse.setBirthday(teacher.getBirthday());
			 teacherresponse.setEmail(teacher.getEmail());
			 teacherresponse.setFirstname(teacher.getFirstname());
			 teacherresponse.setLastname(teacher.getLastname());
			 teacherresponse.setNumtph(teacher.getNumtph());
			 teacherresponse.setRoles(teacher.getRoles());
			      return new ResponseEntity<>(teacherresponse, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	@PutMapping("/update")
	public ResponseEntity<Teacher> updateTeacher(@RequestBody StudentRequest studentrequest) {
		Optional<Teacher> studentData = teacherRepository.findById(studentrequest.getId());

		if (studentData.isPresent()) {
			Teacher student= studentData.get();
			student.setFirstname(studentrequest.getFirstname());
			student.setLastname(studentrequest.getLastname());
			student.setBirthday(studentrequest.getBirthday());
			student.setEmail(studentrequest.getEmail());
			student.setNumtph(studentrequest.getNumtph());
			student.setEmail(studentrequest.getEmail());
			return new ResponseEntity<>(teacherRepository.save(student), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
