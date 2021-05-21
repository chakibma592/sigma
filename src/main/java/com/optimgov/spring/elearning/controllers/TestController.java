package com.optimgov.spring.elearning.controllers;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.optimgov.spring.elearning.models.Course;
import com.optimgov.spring.elearning.models.Lesson;
import com.optimgov.spring.elearning.repository.CourseRepository;
import com.optimgov.spring.elearning.repository.LessonRepository;
import com.optimgov.spring.elearning.repository.TopicRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/elearning")
public class TestController {
	@Autowired
    private CourseRepository courseRepository;
	@Autowired
    private TopicRepository topicRepository;
	@Autowired
    private LessonRepository lessonRepository;
	@GetMapping("/alltopics")
	public ModelMap topics(@RequestParam(name = "value", required = false) String value,
			Model model) {
   
		return new ModelMap().addAttribute("topic", topicRepository.findAll());

    }
	@GetMapping("/all")
	public ModelMap course( @RequestParam(name = "value", required = false) String value,
			Model model) {
   
		return new ModelMap().addAttribute("course", courseRepository.findAll());

    }
	@GetMapping("/alllesson")
	public ModelMap lesson( @RequestParam(name = "value", required = false) String value,
			Model model) {
   
		return new ModelMap().addAttribute("lesson", lessonRepository.findAll());

    }
	@GetMapping("/lessons/{id}")
	public ResponseEntity<ArrayList<Lesson>>getLessonByCourseId(@PathVariable("id") String id) {
		 try {
			 ArrayList<Lesson> lessons = new ArrayList<Lesson>();

		      if (id == null)
		        lessonRepository.findAll().forEach(lessons::add);
		      else
		        lessonRepository.findByCourse(Long.parseLong(id)).forEach(lessons::add);

		      if (lessons.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(lessons, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	@GetMapping("/courses/{id}")
	public ResponseEntity<ArrayList<Course>>getCourseByTopicId(@PathVariable("id") String id) {
		 try {
			 ArrayList<Course> courses = new ArrayList<Course>();

		      if (id == null)
		        courseRepository.findAll().forEach(courses::add);
		      else
		        courseRepository.findByTopic(Long.parseLong(id)).forEach(courses::add);

		      if (courses.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }

		      return new ResponseEntity<>(courses, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	@GetMapping("/course/{id}")
	public ResponseEntity<Course>getCourseByCourseId(@PathVariable("id") String id) {
		 try {
			 

		   

		      return new ResponseEntity<>(courseRepository.findByCourseId(Long.parseLong(id)), HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	  }
	
	@GetMapping("/user")
	@PreAuthorize("hasRole('USER') or hasRole('TEACHER') or hasRole('ADMIN')")
	public String userAccess() {
		return "User Content.";
	}

	@GetMapping("/mod")
	@PreAuthorize("hasRole('TEACHER')")
	public String moderatorAccess() {
		return "Teacher Board.";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminAccess() {
		return "Admin Board.";
	}
}
