package com.optimgov.spring.elearning.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimgov.spring.elearning.models.AnneeUniversitaire;
import com.optimgov.spring.elearning.models.ElementModule;
import com.optimgov.spring.elearning.models.Inscription;
import com.optimgov.spring.elearning.models.Note;
import com.optimgov.spring.elearning.models.Semestre;
import com.optimgov.spring.elearning.models.Student;
import com.optimgov.spring.elearning.payload.request.NoteRequest;
import com.optimgov.spring.elearning.payload.response.MessageResponse;
import com.optimgov.spring.elearning.repository.AnneeUniversitaireRepository;
import com.optimgov.spring.elearning.repository.ElementRepositoy;
import com.optimgov.spring.elearning.repository.InscriptionRepository;
import com.optimgov.spring.elearning.repository.NoteRepository;
import com.optimgov.spring.elearning.repository.SemestreRepository;
import com.optimgov.spring.elearning.repository.StudentRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/notes")
public class NoteController {
	@Autowired
    private ElementRepositoy elementRepository;
	@Autowired
    private InscriptionRepository  inscriptionRepository;
	@Autowired
    private StudentRepository studentRepository;
	@Autowired
    private SemestreRepository semestreRepository;
	@Autowired
    private AnneeUniversitaireRepository anneeRepository;
	@Autowired
    private NoteRepository noteRepository;
	@PostMapping("/add")
	public ResponseEntity<?>  createNote(@RequestBody NoteRequest noterequest) {
		String text="";
		//text=noterequest.toString();
		try {
			
			Optional<Student> optionnalstudent= studentRepository.findById(noterequest.getStudentid());
			if(optionnalstudent.isPresent()) text=text+"Student";
			Student student=optionnalstudent.get();
			Optional<Semestre> optionnalsemestre= semestreRepository.findById(noterequest.getSemestreid());
			if(optionnalsemestre.isPresent()) text=text+" Semestre";
			Semestre semestre=optionnalsemestre.get();
			Optional<AnneeUniversitaire> optionnalannee= anneeRepository.findById(noterequest.getAnneeid());
			if(optionnalannee.isPresent()) text=text+" Annee";
			AnneeUniversitaire annee=optionnalannee.get();
			Optional<ElementModule> elementData = elementRepository.findById(noterequest.getElementid());
			if(elementData.isPresent()) text=text+" element";
			ElementModule element=elementData.get();
			
			Note note = noteRepository
					.save(new Note(noterequest.getNote(),student,semestre,annee,element));
			return ResponseEntity.ok(new MessageResponse("Mark added successfully!"));
		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse(text));
		}
	}
	@GetMapping("/bulletin/{id}")
	public ResponseEntity<ArrayList<Note>>getNotesByStudent(@PathVariable("id") String id) {
		 try {
			 ArrayList<Note> notes = new ArrayList<Note>();
			 Inscription inscription= new Inscription();
			 if (id != null)
				 inscription=inscriptionRepository.findInscriptionEnCoursByStudent(Long.parseLong(id));
		     
		      noteRepository.findByStudentId(Long.parseLong(id),inscription.getAnnee(),inscription.getSemestre()).forEach(notes::add);
		      
		      if (notes.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(notes, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
}
