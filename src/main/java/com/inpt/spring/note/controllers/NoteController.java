package com.inpt.spring.note.controllers;

import java.text.DecimalFormat;
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

import com.inpt.spring.note.models.AnneeUniversitaire;
import com.inpt.spring.note.models.ElementModule;
import com.inpt.spring.note.models.Inscription;
import com.inpt.spring.note.models.Note;
import com.inpt.spring.note.models.Semestre;
import com.inpt.spring.note.models.Student;
import com.inpt.spring.note.payload.request.NoteRequest;
import com.inpt.spring.note.payload.response.ElementResponse;
import com.inpt.spring.note.payload.response.MessageResponse;
import com.inpt.spring.note.payload.response.Nota;
import com.inpt.spring.note.payload.response.NoteResponse;
import com.inpt.spring.note.repository.AnneeUniversitaireRepository;
import com.inpt.spring.note.repository.ElementRepositoy;
import com.inpt.spring.note.repository.InscriptionRepository;
import com.inpt.spring.note.repository.NoteRepository;
import com.inpt.spring.note.repository.SemestreRepository;
import com.inpt.spring.note.repository.StudentRepository;


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
	public ResponseEntity<ArrayList<Nota>>getNotesByStudent(@PathVariable("id") String id) {
		 try {
			 //Liste des notes
			 ArrayList<Note> notes = new ArrayList<Note>();
			 ArrayList<Nota> notas = new ArrayList<Nota>();
			 Inscription inscription= new Inscription();
			 if (id != null)
				  inscription=inscriptionRepository.findInscriptionEnCoursByStudent(Long.parseLong(id));
		          noteRepository.findByStudentId(Long.parseLong(id),inscription.getAnnee().getId(),inscription.getSemestre().getId()).forEach(notes::add);

		      if (notes.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      for(Note n : notes) {
		    	  notas.add(new Nota(n.getElement().getModule().getModulename(),n.getElement().getElementname(),n.getObservation(),n.getNote()));
		      }
		      return new ResponseEntity<>(notas, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	@GetMapping("/moyenne/{id}")
	public ResponseEntity<String>getMoyenneByStudent(@PathVariable("id") String id) {
		 try {
			 //Liste des notes
			 ArrayList<Note> notes = new ArrayList<Note>();
			 Inscription inscription= new Inscription();
			 double moyenne=0.0;
			 DecimalFormat df = new DecimalFormat("###.##");
			 if (id != null)
				  inscription=inscriptionRepository.findInscriptionEnCoursByStudent(Long.parseLong(id));
		          noteRepository.findByStudentId(Long.parseLong(id),inscription.getAnnee().getId(),inscription.getSemestre().getId()).forEach(notes::add);

		      if (notes.isEmpty()) {
		        return new ResponseEntity<>(""+moyenne, HttpStatus.OK);
		      }
		      for(Note n : notes) {
		    	moyenne+=(n.getNote()/notes.size() ); 
		      }
		      return new ResponseEntity<>(""+df.format(moyenne), HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	@GetMapping("/bulletins/{id}")
	public ResponseEntity <ArrayList<NoteResponse>>getNotesByStudentbyModule(@PathVariable("id") String id) {
		 try {
			 ArrayList<Note> notes = new ArrayList<Note>();
			 ArrayList<NoteResponse> moyenne = new ArrayList<NoteResponse>();
			 ArrayList<ElementResponse> listelement = new ArrayList<ElementResponse>();
			 Inscription inscription= new Inscription();
			 String modulename="";
			 double notemodule=0.0;
			 int nbrelement=0,i=0;
			// if (id != null)
				  inscription=inscriptionRepository.findInscriptionEnCoursByStudent(Long.parseLong(id));
		          noteRepository.findByStudentId(Long.parseLong(id),inscription.getAnnee().getId(),inscription.getSemestre().getId()).forEach(notes::add);

		      if (notes.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      for(Note n : notes) {
		    	  if(!modulename.equals(n.getElement().getModule().getModulename())) {
		    		  if(nbrelement!=0)
		    		  moyenne.add(new NoteResponse(modulename,notemodule/nbrelement,listelement) );
		    		  modulename=n.getElement().getModule().getModulename();
		    	  nbrelement=0;
		    	  notemodule=0;
		    	  listelement= new ArrayList<ElementResponse>();
		    	  }
		    	  nbrelement++;
		    	  notemodule+=n.getNote();
		    	  ElementResponse elemementrep=new ElementResponse(n.getElement().getElementname(), n.getNote());
		    	  listelement.add(elemementrep);
		    	  i++;
		      }
		      if(nbrelement!=0)
	    		  moyenne.add(new NoteResponse(modulename,notemodule/nbrelement,listelement) );
	    		 
		      return new ResponseEntity<>(moyenne, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
}
