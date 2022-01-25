package com.optimgov.spring.elearning.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.optimgov.spring.elearning.models.AnneeUniversitaire;
import com.optimgov.spring.elearning.models.ElementModule;
import com.optimgov.spring.elearning.models.Module;
import com.optimgov.spring.elearning.models.Note;
import com.optimgov.spring.elearning.models.Semestre;
import com.optimgov.spring.elearning.models.Student;
import com.optimgov.spring.elearning.models.Teacher;
import com.optimgov.spring.elearning.payload.request.ElementRequest;
import com.optimgov.spring.elearning.payload.request.NoteRequest;
import com.optimgov.spring.elearning.payload.response.MessageResponse;
import com.optimgov.spring.elearning.repository.AnneeUniversitaireRepository;
import com.optimgov.spring.elearning.repository.ElementRepositoy;
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
    private StudentRepository studentRepository;
	@Autowired
    private SemestreRepository semestreRepository;
	@Autowired
    private AnneeUniversitaireRepository anneeRepository;
	@Autowired
    private NoteRepository noteRepository;
	@PostMapping("/add")
	public ResponseEntity<?>  createNote(@RequestBody NoteRequest noterequest) {
		try {
			
			Optional<Student> optionnalstudent= studentRepository.findById(noterequest.getStudentid());
			Student student=optionnalstudent.get();
			Optional<Semestre> optionnalsemestre= semestreRepository.findById(noterequest.getSemestreid());
			Semestre semestre=optionnalsemestre.get();
			Optional<AnneeUniversitaire> optionnalannee= anneeRepository.findById(noterequest.getSemestreid());
			AnneeUniversitaire annee=optionnalannee.get();
			Optional<ElementModule> optionnalelement= elementRepository.findById(noterequest.getElementid());
			ElementModule element=optionnalelement.get();
			Note note = noteRepository
					.save(new Note(noterequest.getNote(),student,semestre,annee,element));
			return ResponseEntity.ok(new MessageResponse("Mark added successfully!"));
		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse("Mark not added!"));
		}
	}
}
