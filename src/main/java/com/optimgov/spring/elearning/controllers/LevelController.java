package com.optimgov.spring.elearning.controllers;
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
import com.optimgov.spring.elearning.models.Level;
import com.optimgov.spring.elearning.repository.LevelRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/levels")
public class LevelController {
	@Autowired
    private LevelRepository levelRepository;
	@PostMapping("/form")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Level> createLevel(@RequestBody Level levelRequest) {
		try {
			
			Level level = levelRepository
					.save(new Level(levelRequest.getLevelname()));
			return new ResponseEntity<>(level, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Level> updateLevel(@PathVariable("id") long id,@RequestBody Level levelRequest) {
		Optional<Level> levelData = levelRepository.findById(id);

		if (levelData.isPresent()) {
			Level level = levelData.get();
			level.setLevelname(levelRequest.getLevelname());
			return new ResponseEntity<>(levelRepository.save(level), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteLevel(@PathVariable("id") long id) {
		try {
			levelRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping("/list")
	@PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')or hasRole('USER')")
	public ResponseEntity<ArrayList<Level>>getLevelsList() {
		 try {
			 ArrayList<Level> levels = new ArrayList<Level>();
			 levelRepository.findAll().forEach(levels::add);
			 if (levels.isEmpty()) {
			        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			      }

			      return new ResponseEntity<>(levels, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
}
