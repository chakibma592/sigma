package com.optimgov.spring.elearning.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimgov.spring.elearning.models.ERole;
import com.optimgov.spring.elearning.models.Filiere;
import com.optimgov.spring.elearning.models.Role;
import com.optimgov.spring.elearning.models.Student;
import com.optimgov.spring.elearning.payload.request.StudentRequest;
import com.optimgov.spring.elearning.payload.response.MessageResponse;
import com.optimgov.spring.elearning.repository.FiliereRepository;
import com.optimgov.spring.elearning.repository.RoleRepository;
import com.optimgov.spring.elearning.repository.StudentRepository;
import com.optimgov.spring.elearning.repository.UserRepository;
import com.optimgov.spring.elearning.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/students")
public class StudentController {
	@Autowired
    private StudentRepository studentRepository;
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	FiliereRepository filiereRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	@GetMapping("/list")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<ArrayList<Student>>getStudentsList() {
		 try {
			 ArrayList<Student> students = new ArrayList<Student>();
			 studentRepository.findAll().forEach(students::add);
			 if (students.isEmpty()) {
			        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			      }

			      return new ResponseEntity<>(students, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	@GetMapping("/findstudent/{id}")
	//@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Student>getStudent(@PathVariable("id") long id) {
		 try {
			 Student student = new Student();
			 Optional<Student> optionalStudent  = studentRepository.findById(id);
			 student=optionalStudent.get();
			// if (student.isEmpty()) {
			   //     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			   //   }

			      return new ResponseEntity<>(student, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	@PostMapping("/add")
	public ResponseEntity<?> addStudent(@Valid @RequestBody StudentRequest studentRequest) {
		
		if (studentRepository.existsByEmail(studentRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		Optional<Filiere> optionnalfiliere= filiereRepository.findById(studentRequest.getFiliereid());
		Filiere filiere=optionnalfiliere.get();
		Student student=null;
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
		roles.add(userRole);
		student= new Student();
		student.setRoles(roles);
		student.setUsername(studentRequest.getEmail());
		student.setPassword( encoder.encode(studentRequest.getFirstname()+studentRequest.getLastname()));
		student.setEmail(studentRequest.getEmail());
		student.setRoles(roles);
		student.setFirstname(studentRequest.getFirstname());
		student.setLastname(studentRequest.getLastname());
		student.setBirthday(studentRequest.getBirthday());
		student.setFiliere(filiere);
		studentRepository.save(student);		
  		return ResponseEntity.ok(new MessageResponse("Student registered successfully!"));
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") long id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update")
	public ResponseEntity<Student> updateStudent(@RequestBody StudentRequest studentrequest) {
		Optional<Student> studentData = studentRepository.findById(studentrequest.getId());

		if (studentData.isPresent()) {
			Student student= studentData.get();
			student.setFirstname(studentrequest.getFirstname());
			student.setLastname(studentrequest.getLastname());
			student.setBirthday(studentrequest.getBirthday());
			student.setEmail(studentrequest.getEmail());
			Optional<Filiere> optionnalfiliere= filiereRepository.findById(studentrequest.getFiliereid());
			Filiere filiere=optionnalfiliere.get();
			student.setFiliere(filiere);
			student.setEmail(studentrequest.getEmail());
			return new ResponseEntity<>(studentRepository.save(student), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
