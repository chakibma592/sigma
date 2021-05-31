package com.optimgov.spring.elearning.controllers;

import java.util.ArrayList;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.optimgov.spring.elearning.models.Lesson;
import com.optimgov.spring.elearning.models.Profession;
import com.optimgov.spring.elearning.models.Question;
import com.optimgov.spring.elearning.models.Quiz;
import com.optimgov.spring.elearning.payload.request.QuestionRequest;
import com.optimgov.spring.elearning.payload.request.QuizRequest;
import com.optimgov.spring.elearning.repository.QuestionRepository;
import com.optimgov.spring.elearning.repository.QuizRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/Question")
public class QuestionController {
	@Autowired
    private QuestionRepository questionRepository;
	@Autowired
    private QuizRepository quizRepository;
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deletequestion(@PathVariable("id") long id) {
		try {
			questionRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/form")
	public ResponseEntity<Question> createQuestion(@RequestBody QuestionRequest questionrequest) {
		try {
			
			Optional<Quiz> optionnalquiz= quizRepository.findById(questionrequest.getQuizid());
			Quiz quiz=optionnalquiz.get();
			Question question = questionRepository
					.save(new Question(questionrequest.getQuestionlib(), quiz));
			return new ResponseEntity<>(question, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Question> updateQuestion(@PathVariable("id") long id,@RequestBody QuestionRequest questionrequest) {
		Optional<Question> questionData = questionRepository.findById(id);

		if (questionData.isPresent()) {
			Question question = questionData.get();
			question.setQuetionlib(questionrequest.getQuestionlib());
			Optional<Quiz> optionnalquiz= quizRepository.findById(questionrequest.getQuizid());
			Quiz quiz=optionnalquiz.get();
			question.setQuiz(quiz);
			
			
			return new ResponseEntity<>(questionRepository.save(question), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/list/{id}")
	public ResponseEntity<ArrayList<Question>>getListQuestions(@PathVariable("id") long id) {
		 try {
			 ArrayList<Question> questions = new ArrayList<Question>();
			 questionRepository.findByQuiz(id).forEach(questions::add);
			 if (questions.isEmpty()) {
			        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			      }

			      return new ResponseEntity<>(questions, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
}
