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
import com.inpt.spring.note.models.EObservation;
import com.inpt.spring.note.models.ElementModule;
import com.inpt.spring.note.models.Inscription;
import com.inpt.spring.note.models.Module;
import com.inpt.spring.note.models.Semestre;
import com.inpt.spring.note.models.Student;
import com.inpt.spring.note.payload.request.ElementRequest;
import com.inpt.spring.note.payload.request.InscriptionRequest;
import com.inpt.spring.note.payload.response.MessageResponse;
import com.inpt.spring.note.repository.AnneeUniversitaireRepository;
import com.inpt.spring.note.repository.ElementRepositoy;
import com.inpt.spring.note.repository.InscriptionRepository;
import com.inpt.spring.note.repository.ModuleRepository;
import com.inpt.spring.note.repository.SemestreRepository;
import com.inpt.spring.note.repository.StudentRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/inscriptions")
public class InscriptionController {
	@Autowired
    private InscriptionRepository inscriptionRepository;
	@Autowired
    private StudentRepository studentRepository;
	@Autowired
    private AnneeUniversitaireRepository anneeRepository;
	@Autowired
    private SemestreRepository semestreRepository;
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteinscription(@PathVariable("id") long id) {
		try {
			inscriptionRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/add")
	public ResponseEntity<?>  createInscription(@RequestBody InscriptionRequest inscriptionRequest) {
		try {
			
			Optional<Student> optionnalstudent= studentRepository.findById(inscriptionRequest.getStudentid());
			Student student=optionnalstudent.get();
			Optional<AnneeUniversitaire> optionnalannee= anneeRepository.findById(inscriptionRequest.getAnneeid());
			AnneeUniversitaire annee=optionnalannee.get();
			Optional<Semestre> optionnalsemestre= semestreRepository.findById(inscriptionRequest.getSemestreid());
			Semestre semestre=optionnalsemestre.get();
			/*String obs="En cours";
			if(inscriptionRequest.getObservation().equals("Admis")) {
				obs=EObservation.Admis.toString();
			} else if(inscriptionRequest.getObservation().equals("Ajournée")) {
				obs=EObservation.Ajourné.toString();
			}
			else if(inscriptionRequest.getObservation().equals("Redouble")) {
				obs=EObservation.Ajourné.toString();
			}*/
			Inscription inscription = inscriptionRepository
					.save(new Inscription(student,annee,semestre,EObservation.En_cours));
			return ResponseEntity.ok(new MessageResponse("Inscription added successfully!"));
		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse("Inscription not added!"));
		}
	}
	@PutMapping("/updateObservation")
	public ResponseEntity<Inscription> updateInscriptionObs(@PathVariable("id") long id,@RequestBody InscriptionRequest inscriptionrequest) {
		Optional<Inscription> inscriptionData = inscriptionRepository.findById(id);

		if (inscriptionData.isPresent()) {
			Inscription inscription = inscriptionData.get();
			EObservation obs= EObservation.En_cours;
			if(inscriptionrequest.getObservation().equals("Admis")) {
				obs= EObservation.Admis;
			}else if(inscriptionrequest.getObservation().equals("Non Admis")) {
				obs= EObservation.Redouble;
			}else if(inscriptionrequest.getObservation().equals("Ajourné")) {
				obs= EObservation.Ajourné;
			}
			inscription.setObservation(obs);
			
			
			
			return new ResponseEntity<>(inscriptionRepository.save(inscription), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/encours/{id}")
	public ResponseEntity<Inscription>getAnneeEnCours(@PathVariable("id") String id) {
		 try {
			 Inscription inscription = new Inscription();

		      if (id != null)
		        
		     inscription =   inscriptionRepository.findInscriptionEnCoursByStudent(Long.parseLong(id));

		     

		      return new ResponseEntity<>(inscription, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
}
