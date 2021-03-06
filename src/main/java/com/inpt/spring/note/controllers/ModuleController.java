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

import com.inpt.spring.note.models.Filiere;
import com.inpt.spring.note.models.Module;
import com.inpt.spring.note.payload.request.ModuleRequest;
import com.inpt.spring.note.payload.response.MessageResponse;
import com.inpt.spring.note.repository.FiliereRepository;
import com.inpt.spring.note.repository.ModuleRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/modules")
public class ModuleController {
	@Autowired
    private ModuleRepository moduleRepository;
	@Autowired
    private FiliereRepository filiereRepository;
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteModule(@PathVariable("id") long id) {
		try {
			moduleRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/add")
	public ResponseEntity<?>  createModule(@RequestBody ModuleRequest modulerequest) {
		try {
			
			Optional<Filiere> optionnalfiliere= filiereRepository.findById(modulerequest.getFiliereid());
			Filiere filiere=optionnalfiliere.get();
			Module module = moduleRepository
					.save(new Module(modulerequest.getModulename(),filiere));
			return ResponseEntity.ok(new MessageResponse("Module added successfully!"));
		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse("Module not added!"));
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Module> updateModule(@PathVariable("id") long id,@RequestBody ModuleRequest modulerequest) {
		Optional<Module> moduleData = moduleRepository.findById(id);

		if (moduleData.isPresent()) {
			Module module = moduleData.get();
			module.setModulename(modulerequest.getModulename());
			Optional<Filiere> optionnalfiliere= filiereRepository.findById(modulerequest.getFiliereid());
			Filiere filiere=optionnalfiliere.get();
			module.setFiliere(filiere);
			
			
			return new ResponseEntity<>(moduleRepository.save(module), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/list/{id}")
	public ResponseEntity<ArrayList<Module>>getModuleByFiliere(@PathVariable("id") String id) {
		 try {
			 ArrayList<Module> modules = new ArrayList<Module>();

		      if (id != null)
		        
		        moduleRepository.findModuleByFiliere(Long.parseLong(id)).forEach(modules::add);

		      if (modules.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(modules, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	
}
