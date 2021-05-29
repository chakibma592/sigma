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
import com.optimgov.spring.elearning.models.Subscribe;
import com.optimgov.spring.elearning.models.Topic;
import com.optimgov.spring.elearning.models.User;
import com.optimgov.spring.elearning.payload.request.SubscribeRequest;
import com.optimgov.spring.elearning.repository.TopicRepository;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/topic")
public class TopicController {
	@Autowired
    private TopicRepository topicRepository;
	@PostMapping("/form")
	public ResponseEntity<Topic> createTopic(@RequestBody Topic topicRequest) {
		try {
			
			Topic topic = topicRepository
					.save(new Topic(topicRequest.getTopicname()));
			return new ResponseEntity<>(topic, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PutMapping("/update/{id}")
	public ResponseEntity<Topic> updateTopic(@PathVariable("id") long id,@RequestBody Topic topicRequest) {
		Optional<Topic> topicData = topicRepository.findById(id);

		if (topicData.isPresent()) {
			Topic topic = topicData.get();
			topic.setTopicname(topicRequest.getTopicname());
			return new ResponseEntity<>(topicRepository.save(topic), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteTopic(@PathVariable("id") long id) {
		try {
			topicRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
