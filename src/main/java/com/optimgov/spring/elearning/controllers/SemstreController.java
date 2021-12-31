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

import com.optimgov.spring.elearning.models.Filiere;
import com.optimgov.spring.elearning.models.Semestre;
import com.optimgov.spring.elearning.payload.response.MessageResponse;
import com.optimgov.spring.elearning.repository.FiliereRepository;
import com.optimgov.spring.elearning.repository.SemestreRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/Semestres")
public class SemstreController {
	@Autowired
    private SemestreRepository semestreRepository;
	
	@GetMapping("/list/{id}")
	public ResponseEntity<Semestre>getSemestre(@PathVariable("id") String id) {
		 try {
			 

		   

		      return new ResponseEntity<>(semestreRepository.findBySemestreId(Long.parseLong(id)), HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	@GetMapping("/list")
	public ResponseEntity<ArrayList<Semestre>>getSemestreList() {
		 try {
			 ArrayList<Semestre> semestres = new ArrayList<Semestre>();
			 semestreRepository.findAll().forEach(semestres::add);
			 if (semestres.isEmpty()) {
			        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			      }

			      return new ResponseEntity<>(semestres, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	@PostMapping("/add")
	public ResponseEntity<?>  createSemestre(@RequestBody Semestre semestrerequest) {
		try {
			Semestre semestre = semestreRepository
					.save(new Semestre(semestrerequest.getSemetername()));
			return ResponseEntity.ok(new MessageResponse("Semester added succesfully!"));
		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse("Semester not added!"));
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Semestre> updateSemestre(@PathVariable("id") long id,@RequestBody Semestre semestrerequest) {
		Optional<Semestre> semestreData = semestreRepository.findById(id);

		if (semestreData.isPresent()) {
			Semestre semestre = semestreData.get();
			semestre.setSemetername(semestrerequest.getSemetername());
			
			
			
			return new ResponseEntity<>(semestreRepository.save(semestre), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deletesemestre(@PathVariable("id") long id) {
		try {
			semestreRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
