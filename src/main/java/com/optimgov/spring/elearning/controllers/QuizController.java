package com.optimgov.spring.elearning.controllers;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.optimgov.spring.elearning.models.Lesson;
import com.optimgov.spring.elearning.models.Quiz;
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
	@PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER')")
	public ResponseEntity<HttpStatus> deleteQuiz(@PathVariable("id") long id) {
		try {
			quizRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/form")
	@PreAuthorize(" hasRole('TEACHER')")
	public ResponseEntity<Quiz> createQuiz(@RequestBody QuizRequest quizrequest) {
		try {
			
			Optional<Lesson> optionnallesson= lessonRepository.findById(quizrequest.getLessonid());
			Lesson lesson=optionnallesson.get();
			Quiz quiz = quizRepository
					.save(new Quiz(quizrequest.getQuizname(), lesson));
			return new ResponseEntity<>(quiz, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{id}")
	@PreAuthorize("hasRole('TEACHER')")
	public ResponseEntity<Quiz> updateQuiz(@PathVariable("id") long id,@RequestBody QuizRequest quizrequest) {
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
