package com.optimgov.spring.elearning.controllers;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

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
import com.optimgov.spring.elearning.models.Subscribe;
import com.optimgov.spring.elearning.models.Teacher;
import com.optimgov.spring.elearning.models.Topic;
import com.optimgov.spring.elearning.models.User;
import com.optimgov.spring.elearning.payload.request.CourseRequest;
import com.optimgov.spring.elearning.payload.request.SubscribeRequest;
import com.optimgov.spring.elearning.repository.CourseRepository;
import com.optimgov.spring.elearning.repository.TeacherRepository;
import com.optimgov.spring.elearning.repository.TopicRepository;
import com.optimgov.spring.elearning.repository.UserRepository;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/course")
public class CourseController {
	@Autowired
    private CourseRepository courseRepository;
	@Autowired
    private TopicRepository topicRepository;
	@Autowired
    private TeacherRepository teacherRepository;
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deletecourse(@PathVariable("id") long id) {
		try {
			courseRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/form")
	public ResponseEntity<Course>createCourse(@RequestBody CourseRequest courserequest) {
		try {
			
			Optional<Topic> optionnaltopic= topicRepository.findById(courserequest.getTopicid());
			Topic topic=optionnaltopic.get();
			 
			Teacher teacher=teacherRepository.findByTeacherId(courserequest.getTeacherid());
			Course course = courseRepository
					.save(new Course(courserequest.getCoursename(),courserequest.getCourseimageurl(), courserequest.getDescription(),
							courserequest.getPrice(), courserequest.getRate(), false, topic,teacher));
			return new ResponseEntity<>(course, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable("id") long id,@RequestBody CourseRequest courserequest) {
		Optional<Course> courseData = courseRepository.findById(id);

		if (courseData.isPresent()) {
			Course course = courseData.get();
			course.setCoursename(courserequest.getCoursename());
			course.setCourseimageurl(courserequest.getCourseimageurl());
			course.setCreated_at(courserequest.getCreated_at());
			course.setLooked(courserequest.isLocked());
			course.setDescription(courserequest.getDescription());
			course.setPrice(courserequest.getPrice());
			course.setRate(courserequest.getRate());
			Optional<Topic> optionnaltopic= topicRepository.findById(courserequest.getTopicid());
			Topic topic=optionnaltopic.get();
			course.setTopic(topic);
			
			
			return new ResponseEntity<>(courseRepository.save(course), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
