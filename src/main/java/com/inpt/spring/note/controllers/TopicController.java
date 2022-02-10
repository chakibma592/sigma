package com.inpt.spring.note.controllers;

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

import com.inpt.spring.note.models.Topic;
import com.inpt.spring.note.repository.TopicRepository;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/topics")
public class TopicController {
	@Autowired
    private TopicRepository topicRepository;
	@PostMapping("/form")
	@PreAuthorize("hasRole('ADMIN')")
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
	@PreAuthorize("hasRole('ADMIN')")
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
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteTopic(@PathVariable("id") long id) {
		try {
			topicRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
