package com.inpt.spring.note.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inpt.spring.note.models.ElementModule;
import com.inpt.spring.note.models.Filiere;
import com.inpt.spring.note.models.Module;
import com.inpt.spring.note.models.Teacher;
import com.inpt.spring.note.payload.request.ElementRequest;
import com.inpt.spring.note.payload.request.ModuleRequest;
import com.inpt.spring.note.payload.response.MessageResponse;
import com.inpt.spring.note.repository.ElementRepositoy;
import com.inpt.spring.note.repository.FiliereRepository;
import com.inpt.spring.note.repository.ModuleRepository;
import com.inpt.spring.note.repository.TeacherRepository;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/elements")
public class ElementController {
	@Autowired
    private ModuleRepository moduleRepository;
	@Autowired
    private ElementRepositoy elementRepository;
	@Autowired
    private TeacherRepository teacherRepository;
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteelement(@PathVariable("id") long id) {
		try {
			elementRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/add")
	public ResponseEntity<?>  createElement(@RequestBody ElementRequest elementrequest) {
		try {
			
			Optional<Module> optionnalmodule= moduleRepository.findById(elementrequest.getModuleid());
			Module module=optionnalmodule.get();
			Optional<Teacher> optionnalteacher= teacherRepository.findById(elementrequest.getTeacherid());
			Teacher teacher=optionnalteacher.get();
			
			ElementModule element = elementRepository
					.save(new ElementModule(elementrequest.getElementname(),module,teacher));
			return ResponseEntity.ok(new MessageResponse("Element added successfully!"));
		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse("Element not added!"));
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<ElementModule> updateElement(@PathVariable("id") long id,@RequestBody ElementRequest elementrequest) {
		Optional<ElementModule> elementData = elementRepository.findById(id);

		if (elementData.isPresent()) {
			ElementModule element = elementData.get();
			element.setElementname(elementrequest.getElementname());
			Optional<Teacher> optionnalteacher= teacherRepository.findById(elementrequest.getTeacherid());
			Teacher teacher=optionnalteacher.get();
			element.setTeacher(teacher);
			
			
			return new ResponseEntity<>(elementRepository.save(element), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping("/updateTeacher/{id}")
	public ResponseEntity<ElementModule> updateElementTeacher(@PathVariable("id") long id,@RequestBody ElementRequest elementrequest) {
		Optional<ElementModule> elementData = elementRepository.findById(id);

		if (elementData.isPresent()) {
			ElementModule element = elementData.get();
			
			Optional<Teacher> optionnalteaher= teacherRepository.findById(elementrequest.getTeacherid());
			Teacher teacher=optionnalteaher.get();
			element.setTeacher(teacher);
			
			
			return new ResponseEntity<>(elementRepository.save(element), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/list/{id}")
	public ResponseEntity<ArrayList<ElementModule>>getElementByModule(@PathVariable("id") String id) {
		 try {
			 ArrayList<ElementModule> elements = new ArrayList<ElementModule>();

		      if (id != null)
		        
		        elementRepository.findElementByModule(Long.parseLong(id)).forEach(elements::add);

		      if (elements.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(elements, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
}
