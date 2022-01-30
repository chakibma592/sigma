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

import com.optimgov.spring.elearning.models.Absence;
import com.optimgov.spring.elearning.models.AnneeUniversitaire;
import com.optimgov.spring.elearning.models.ElementModule;
import com.optimgov.spring.elearning.models.Inscription;
import com.optimgov.spring.elearning.models.Note;
import com.optimgov.spring.elearning.models.Semestre;
import com.optimgov.spring.elearning.models.Student;
import com.optimgov.spring.elearning.payload.request.AbsenceRequest;
import com.optimgov.spring.elearning.payload.request.NoteRequest;
import com.optimgov.spring.elearning.payload.response.AbsenceListResponse;
import com.optimgov.spring.elearning.payload.response.AbsenceSingleResponse;
import com.optimgov.spring.elearning.payload.response.MessageResponse;
import com.optimgov.spring.elearning.payload.response.Nota;
import com.optimgov.spring.elearning.repository.AbsenceRepository;
import com.optimgov.spring.elearning.repository.AnneeUniversitaireRepository;
import com.optimgov.spring.elearning.repository.InscriptionRepository;
import com.optimgov.spring.elearning.repository.SemestreRepository;
import com.optimgov.spring.elearning.repository.StudentRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/absences")
public class AbsenceController {
	@Autowired
    private StudentRepository studentRepository;
	@Autowired
    private SemestreRepository semestreRepository;
	@Autowired
    private AnneeUniversitaireRepository anneeRepository;
	@Autowired
    private AbsenceRepository absenceRepository;
	@Autowired
    private InscriptionRepository inscriptionRepository;
	@PostMapping("/add")
	public ResponseEntity<?>  createAbsence(@RequestBody AbsenceRequest absencerequest) {
		String text="";
		//text=noterequest.toString();
		try {
			
			Optional<Student> optionnalstudent= studentRepository.findById(absencerequest.getStudentid());
			//if(optionnalstudent.isPresent()) text=text+"Student";
			Student student=optionnalstudent.get();
			Optional<Semestre> optionnalsemestre= semestreRepository.findById(absencerequest.getSemestreid());
			Semestre semestre=optionnalsemestre.get();
			Optional<AnneeUniversitaire> optionnalannee= anneeRepository.findById(absencerequest.getAnneeid());
			//if(optionnalannee.isPresent()) text=text+" Annee";
			AnneeUniversitaire annee=optionnalannee.get();
			
			
			Absence absence = absenceRepository
					.save(new Absence(absencerequest.getDateabsence(),absencerequest.getNombreheures(),false,"non justifiéé",student,semestre,annee));
			return ResponseEntity.ok(new MessageResponse("Mark added successfully!"));
		} catch (Exception e) {
			return ResponseEntity.ok(new MessageResponse(text));
		}
	}
	@GetMapping("/list/{id}")
	public ResponseEntity<ArrayList<Absence>>getAbsenceByStudent(@PathVariable("id") String id) {
		 try {
			 //Liste des notes
			 ArrayList<Absence> absences = new ArrayList<Absence>();
			 Inscription inscription= new Inscription();
			 if (id != null)
				  inscription=inscriptionRepository.findInscriptionEnCoursByStudent(Long.parseLong(id));
		          absenceRepository.findByStudentId(Long.parseLong(id),inscription.getAnnee().getId(),inscription.getSemestre().getId()).forEach(absences::add);

		      if (absences.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		    
		      return new ResponseEntity<>(absences, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	@GetMapping("/nonjustifie/{id}")
	public ResponseEntity<AbsenceListResponse>getAbsenceNonJustifieByStudent(@PathVariable("id") String id) {
		 try {
			 //Liste des notes
			 ArrayList<Absence> absences = new ArrayList<Absence>();
			 ArrayList<AbsenceSingleResponse> singleabsences = new ArrayList<AbsenceSingleResponse>();
			 AbsenceListResponse absenceLsit= new AbsenceListResponse();
			 int nbr=0;
			 Inscription inscription= new Inscription();
			 if (id != null)
				  inscription=inscriptionRepository.findInscriptionEnCoursByStudent(Long.parseLong(id));
		          absenceRepository.findByStudentId(Long.parseLong(id),inscription.getAnnee().getId(),inscription.getSemestre().getId()).forEach(absences::add);

		      if (absences.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      for(Absence n : absences) {
		    	if(!n.isJustifie()) {
		    		singleabsences.add(new AbsenceSingleResponse(n.getNombreheures(),n.getDateabsence(),n.getObservation()));
		    		nbr+=n.getNombreheures();
		    	}
		    	
		      }
		      absenceLsit.setListAbsence(singleabsences);
		      absenceLsit.setTotalabsence(nbr);
		      return new ResponseEntity<>(absenceLsit, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
}
