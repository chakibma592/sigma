package com.optimgov.spring.elearning.controllers;

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

import com.optimgov.spring.elearning.models.Course;
import com.optimgov.spring.elearning.models.Lesson;
import com.optimgov.spring.elearning.models.Quiz;
import com.optimgov.spring.elearning.payload.request.LessonRequest;
import com.optimgov.spring.elearning.payload.request.QuizRequest;
import com.optimgov.spring.elearning.repository.LessonRepository;
import com.optimgov.spring.elearning.repository.QuizRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/quiz")
public class QuizController {
	@Autowired
    private LessonRepository lessonRepository;
	@Autowired
    private QuizRepository quizRepository;
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deletequiz(@PathVariable("id") long id) {
		try {
			quizRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/form")
	public ResponseEntity<Lesson> createQuiz(@RequestBody QuizRequest quizrequest) {
		try {
			
			Optional<Lesson> optionnallesson= lessonRepository.findById(quizrequest.getLessonid());
			Lesson lesson=optionnallesson.get();
			Quiz quiz = quizRepository
					.save(new Quiz(quizrequest.getQuizname(), lesson));
			return new ResponseEntity<>(lesson, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Quiz> updateLesson(@PathVariable("id") long id,@RequestBody QuizRequest quizrequest) {
		Optional<Quiz> quizData = quizRepository.findById(id);

		if (quizData.isPresent()) {
			Quiz quiz = quizData.get();
			quiz.setQuiztitle(quizrequest.getQuizname());
			Optional<Lesson> optionnallesson= lessonRepository.findById(quizrequest.getLessonid());
			Lesson lesson=optionnallesson.get();
			quiz.setLesson(lesson);
			
			
			return new ResponseEntity<>(quizRepository.save(quiz), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
