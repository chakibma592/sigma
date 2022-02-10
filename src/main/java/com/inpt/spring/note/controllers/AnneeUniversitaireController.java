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

import com.inpt.spring.note.models.AnneeUniversitaire;
import com.inpt.spring.note.models.Semestre;
import com.inpt.spring.note.payload.response.MessageResponse;
import com.inpt.spring.note.repository.AnneeUniversitaireRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/annees")
public class AnneeUniversitaireController {
	@Autowired
    private AnneeUniversitaireRepository anneeRepository;
	
	@GetMapping("/list/{id}")
	public ResponseEntity<AnneeUniversitaire>getAnnee(@PathVariable("id") String id) {
		 try {
			 

		   

		      return new ResponseEntity<>(anneeRepository.findByAnneeId(Long.parseLong(id)), HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	@GetMapping("/list")
	public ResponseEntity<ArrayList<AnneeUniversitaire>>getAnneeList() {
		 try {
			 ArrayList<AnneeUniversitaire> annees = new ArrayList<AnneeUniversitaire>();
			 anneeRepository.findAll().forEach(annees::add);
			 if (annees.isEmpty()) {
			        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			      }

			      return new ResponseEntity<>(annees, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	@PostMapping("/add")
	public ResponseEntity<?>  createAnnee(@RequestBody AnneeUniversitaire anneerequest) {
		try {
			AnneeUniversitaire annee = anneeRepository
					.save(new AnneeUniversitaire(anneerequest.getAnneename()));
			return ResponseEntity.ok(new MessageResponse("Year added succesfully!"));
		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse("Year not added!"));
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<AnneeUniversitaire> updateAnnee(@PathVariable("id") long id,@RequestBody AnneeUniversitaire anneerequest) {
		Optional<AnneeUniversitaire> anneeData = anneeRepository.findById(id);

		if (anneeData.isPresent()) {
			AnneeUniversitaire annee = anneeData.get();
			annee.setAnneename(anneerequest.getAnneename());
			
			
			
			return new ResponseEntity<>(anneeRepository.save(annee), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteannee(@PathVariable("id") long id) {
		try {
			anneeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
