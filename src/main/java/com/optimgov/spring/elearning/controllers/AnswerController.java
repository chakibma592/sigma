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
import com.optimgov.spring.elearning.models.Answer;
import com.optimgov.spring.elearning.models.Question;
import com.optimgov.spring.elearning.payload.request.AnswerRequest;
import com.optimgov.spring.elearning.repository.AnswerRepository;
import com.optimgov.spring.elearning.repository.QuestionRepository;


import org.springframework.security.access.prepost.PreAuthorize;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/answers")
public class AnswerController {
	@Autowired
    private QuestionRepository questionRepository;
	@Autowired
    private AnswerRepository answerRepository;
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
	public ResponseEntity<HttpStatus> deleteAnswer(@PathVariable("id") long id) {
		try {
			answerRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/form")
	@PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
	public ResponseEntity<Answer> createAnswer(@RequestBody AnswerRequest answerrequest) {
		try {
			
			Optional<Question> optionnalquestion= questionRepository.findById(answerrequest.getQuestionid());
			Question question=optionnalquestion.get();
			Answer answer = answerRepository
					.save(new Answer(answerrequest.getAnswerlib(), answerrequest.isCorrect(),question));
			return new ResponseEntity<>(answer, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
	public ResponseEntity<Answer> updateAnswer(@PathVariable("id") long id,@RequestBody AnswerRequest answerrequest) {
		Optional<Answer> answerData = answerRepository.findById(id);

		if (answerData.isPresent()) {
			Answer answer = answerData.get();
			answer.setAnswerlib(answerrequest.getAnswerlib());
			Optional<Question> optionnalquestion= questionRepository.findById(answerrequest.getQuestionid());
			Question question=optionnalquestion.get();
			answer.setQuestion(question);
			
			
			return new ResponseEntity<>(answerRepository.save(answer), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/list/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('USER')")
	public ResponseEntity<ArrayList<Answer>>getListAnswers(@PathVariable("id") long id) {
		 try {
			 ArrayList<Answer> answers = new ArrayList<Answer>();
			 answerRepository.findByQuestion(id).forEach(answers::add);
			 if (answers.isEmpty()) {
			        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			      }

			      return new ResponseEntity<>(answers, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
}
