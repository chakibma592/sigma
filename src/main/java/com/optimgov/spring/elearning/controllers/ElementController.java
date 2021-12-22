package com.optimgov.spring.elearning.controllers;

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

import com.optimgov.spring.elearning.models.ElementModule;
import com.optimgov.spring.elearning.models.Filiere;
import com.optimgov.spring.elearning.models.Module;
import com.optimgov.spring.elearning.payload.request.ElementRequest;
import com.optimgov.spring.elearning.payload.request.ModuleRequest;
import com.optimgov.spring.elearning.repository.ElementRepositoy;
import com.optimgov.spring.elearning.repository.FiliereRepository;
import com.optimgov.spring.elearning.repository.ModuleRepository;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/elements")
public class ElementController {
	@Autowired
    private ModuleRepository moduleRepository;
	@Autowired
    private ElementRepositoy elementRepository;
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
	public ResponseEntity<ElementModule> createModule(@RequestBody ElementRequest elementrequest) {
		try {
			
			Optional<Module> optionnalmodule= moduleRepository.findById(elementrequest.getModuleid());
			Module module=optionnalmodule.get();
			ElementModule element = elementRepository
					.save(new ElementModule(elementrequest.getElementname(),module));
			return new ResponseEntity<>(element, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<ElementModule> updateElement(@PathVariable("id") long id,@RequestBody ElementRequest elementrequest) {
		Optional<ElementModule> elementData = elementRepository.findById(id);

		if (elementData.isPresent()) {
			ElementModule element = elementData.get();
			element.setElementname(elementrequest.getElementname());
			Optional<Module> optionnalmodule= moduleRepository.findById(elementrequest.getModuleid());
			Module module=optionnalmodule.get();
			element.setModule(module);
			
			
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
