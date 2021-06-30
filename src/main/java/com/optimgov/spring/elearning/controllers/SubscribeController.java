package com.optimgov.spring.elearning.controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.optimgov.spring.elearning.models.Course;
import com.optimgov.spring.elearning.models.Subscribe;
import com.optimgov.spring.elearning.models.User;
import com.optimgov.spring.elearning.payload.request.SubscribeRequest;
import com.optimgov.spring.elearning.payload.response.AllCartResponse;
import com.optimgov.spring.elearning.payload.response.CartResponse;
import com.optimgov.spring.elearning.repository.CourseRepository;
import com.optimgov.spring.elearning.repository.SubscribeRepository;
import com.optimgov.spring.elearning.repository.UserRepository;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/subscribes")
public class SubscribeController {
	@Autowired
    private SubscribeRepository subscribeRepository;
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private CourseRepository courseRepository;
	
	@GetMapping("/mylist/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<ArrayList<CartResponse>> getMyList(@PathVariable("id") Long id) {
		try {
			ArrayList<Subscribe> subscribers = new ArrayList<Subscribe>();
			ArrayList<CartResponse> cart= new ArrayList<CartResponse>();

			subscribeRepository.findByCourseBySubscriberId(id).forEach(subscribers::add);
			Iterator it= subscribers.iterator();
			while(it.hasNext()) {
				Subscribe s= (Subscribe) it.next();
				if(s.isPayed())
				cart.add(new CartResponse(s.getCourse().getCoursename(),s.getCourse().getCourseimageurl(),s.getCourse().getDescription(),s.getCourse().getPrice(),s.getCourse().getDiscount(),s.getCourse().isLocked(),s.getCourse().getTopic().getTopicname(),s.getId(),s.getCourse().getId(),s.getCourse().getLevel().getLevelname(),s.getCourse().getShortdescription()));
			}
			if (cart.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(cart, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/paid/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Subscribe> paidCourseSubscription(@PathVariable("id") long id) {
		Optional<Subscribe> subscribeData = subscribeRepository.findById(id);

		if (subscribeData.isPresent()) {
			Subscribe subscriber = subscribeData.get();
			subscriber .setPayed(true);
			return new ResponseEntity<>(subscribeRepository.save(subscriber), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

}
