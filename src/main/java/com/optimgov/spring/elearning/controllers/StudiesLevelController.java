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

import com.optimgov.spring.elearning.models.Profession;
import com.optimgov.spring.elearning.models.StudiesLevel;
import com.optimgov.spring.elearning.repository.StudiesLevelRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/level")
public class StudiesLevelController {
	@Autowired
    private StudiesLevelRepository studiesLevelRepository;
	@PostMapping("/form")
	public ResponseEntity<StudiesLevel> createLevel(@RequestBody StudiesLevel studiesLevelRequest) {
		try {
			
			StudiesLevel studiesLevel = studiesLevelRepository
					.save(new StudiesLevel(studiesLevelRequest.getLevelname()));
			return new ResponseEntity<>(studiesLevel, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<StudiesLevel> updateLevel(@PathVariable("id") long id,@RequestBody StudiesLevel studiesLevelRequest) {
		Optional<StudiesLevel> studiesLevelData = studiesLevelRepository.findById(id);

		if (studiesLevelData.isPresent()) {
			StudiesLevel studiesLevel = studiesLevelData.get();
			studiesLevel.setLevelname(studiesLevelRequest.getLevelname());
			return new ResponseEntity<>(studiesLevelRepository.save(studiesLevel), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteLevel(@PathVariable("id") long id) {
		try {
			studiesLevelRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/list")
	public ResponseEntity<ArrayList<StudiesLevel>>getListLevel() {
		 try {
			 ArrayList<StudiesLevel> studiesLevels = new ArrayList<StudiesLevel>();
			 studiesLevelRepository.findAll().forEach(studiesLevels::add);
			 if (studiesLevels.isEmpty()) {
			        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			      }

			      return new ResponseEntity<>(studiesLevels, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
}
