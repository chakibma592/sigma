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

import com.optimgov.spring.elearning.models.Course;
import com.optimgov.spring.elearning.models.Profession;
import com.optimgov.spring.elearning.repository.ProfessionRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profession")
public class ProfessionController {
	@Autowired
    private ProfessionRepository professionRepository;
	@PostMapping("/form")
	public ResponseEntity<Profession> createProfession(@RequestBody Profession professionRequest) {
		try {
			
			Profession profession = professionRepository
					.save(new Profession(professionRequest.getProfessionname()));
			return new ResponseEntity<>(profession, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Profession> updateProfession(@PathVariable("id") long id,@RequestBody Profession professionRequest) {
		Optional<Profession> professionData = professionRepository.findById(id);

		if (professionData.isPresent()) {
			Profession profession = professionData.get();
			profession.setProfessionname(professionRequest.getProfessionname());
			return new ResponseEntity<>(professionRepository.save(profession), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteProfession(@PathVariable("id") long id) {
		try {
			professionRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/list")
	public ResponseEntity<ArrayList<Profession>>getListProfession() {
		 try {
			 ArrayList<Profession> professions = new ArrayList<Profession>();
			 professionRepository.findAll().forEach(professions::add);
			 if (professions.isEmpty()) {
			        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			      }

			      return new ResponseEntity<>(professions, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
}
