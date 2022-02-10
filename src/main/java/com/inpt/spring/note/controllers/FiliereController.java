package com.inpt.spring.note.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inpt.spring.note.models.Filiere;
import com.inpt.spring.note.models.Teacher;
import com.inpt.spring.note.models.Topic;
import com.inpt.spring.note.payload.request.CourseRequest;
import com.inpt.spring.note.payload.response.MessageResponse;
import com.inpt.spring.note.repository.FiliereRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/filieres")
public class FiliereController {
	@Autowired
    private FiliereRepository filiereRepository;
	
	@GetMapping("/list/{id}")
	public ResponseEntity<Filiere>getFiliere(@PathVariable("id") String id) {
		 try {
			 

		   

		      return new ResponseEntity<>(filiereRepository.findByFiliereId(Long.parseLong(id)), HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	@GetMapping("/list")
	public ResponseEntity<ArrayList<Filiere>>getFilieresList() {
		 try {
			 ArrayList<Filiere> filieres = new ArrayList<Filiere>();
			 filiereRepository.findAll().forEach(filieres::add);
			 if (filieres.isEmpty()) {
			        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			      }

			      return new ResponseEntity<>(filieres, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	@PostMapping("/add")
	public ResponseEntity<?>  createFiliere(@RequestBody Filiere filiererequest) {
		try {
			Filiere filiere = filiereRepository
					.save(new Filiere(filiererequest.getFilierename()));
			return ResponseEntity.ok(new MessageResponse("Filiere added successfully!"));
		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse("Filiere not added!"));
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Filiere> updateFiliere(@PathVariable("id") long id,@RequestBody Filiere filiererequest) {
		Optional<Filiere> filiereData = filiereRepository.findById(id);

		if (filiereData.isPresent()) {
			Filiere filiere = filiereData.get();
			filiere.setFilierename(filiererequest.getFilierename());
			
			
			
			return new ResponseEntity<>(filiereRepository.save(filiere), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deletefiliere(@PathVariable("id") long id) {
		try {
			filiereRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
