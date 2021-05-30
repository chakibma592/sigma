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
import com.optimgov.spring.elearning.models.Topic;
import com.optimgov.spring.elearning.payload.request.CourseRequest;
import com.optimgov.spring.elearning.payload.request.LessonRequest;
import com.optimgov.spring.elearning.repository.CourseRepository;
import com.optimgov.spring.elearning.repository.LessonRepository;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lesson")
public class LessonController {
	@Autowired
    private LessonRepository lessonRepository;
	@Autowired
    private CourseRepository courseRepository;
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deletecourse(@PathVariable("id") long id) {
		try {
			lessonRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/form")
	public ResponseEntity<Lesson> createLesson(@RequestBody LessonRequest lessonrequest) {
		try {
			
			Optional<Course> optionnalcourse= courseRepository.findById(lessonrequest.getCourseid());
			Course course=optionnalcourse.get();
			Lesson lesson = lessonRepository
					.save(new Lesson(lessonrequest.getLessonname(), lessonrequest.getDescription(),lessonrequest.getVideourl(),
							 course));
			return new ResponseEntity<>(lesson, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Lesson> updateLesson(@PathVariable("id") long id,@RequestBody LessonRequest lessonrequest) {
		Optional<Lesson> lessonData = lessonRepository.findById(id);

		if (lessonData.isPresent()) {
			Lesson lesson = lessonData.get();
			lesson.setLessonname(lessonrequest.getLessonname());
			lesson.setVideourl(lessonrequest.getVideourl());
			lesson.setDescription(lessonrequest.getDescription());
			Optional<Course> optionnalcourse= courseRepository.findById(lessonrequest.getCourseid());
			Course course=optionnalcourse.get();
			lesson.setCourse(course);
			
			
			return new ResponseEntity<>(lessonRepository.save(lesson), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
