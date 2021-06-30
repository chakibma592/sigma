package com.optimgov.spring.elearning.controllers;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/api/carts")
public class CartController {
	@Autowired
    private SubscribeRepository subscribeRepository;
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private CourseRepository courseRepository;
	@GetMapping("/cartlist/{id}")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<AllCartResponse> getMyCart(@PathVariable("id") Long id) {
		try {
			ArrayList<Subscribe> subscribers = new ArrayList<Subscribe>();
			ArrayList<CartResponse> cart= new ArrayList<CartResponse>();
            AllCartResponse allcart= new AllCartResponse();
            allcart.setCartlist(cart);
			subscribeRepository.findByCourseBySubscriberId(id).forEach(subscribers::add);
			int i=0;
			double cost=0.0;
			Iterator it= subscribers.iterator();
			while(it.hasNext()) {
				Subscribe s= (Subscribe) it.next();
				if(!s.isPayed()) {
					allcart.getCartlist().add(new CartResponse(s.getCourse().getCoursename(),s.getCourse().getCourseimageurl(),s.getCourse().getDescription(),s.getCourse().getPrice(),s.getCourse().getDiscount(),s.getCourse().isLocked(),s.getCourse().getTopic().getTopicname(),s.getId(),s.getCourse().getId(),s.getCourse().getLevel().getLevelname(),s.getCourse().getShortdescription()));
				    i++;
				    cost= cost+s.getCourse().getPrice()-(s.getCourse().getPrice()*s.getCourse().getDiscount()/100);
				}
			}
			allcart.setCount(i);
			allcart.setCost(cost);
			if (cart.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(allcart, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("/form")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Subscribe> AddToCart(@RequestBody SubscribeRequest subscribe) {
		try {
			Course course= courseRepository.findByCourseId(subscribe.getCourseid());
			User user=userRepository.findByUserId(subscribe.getUserid());
			Subscribe subscriber = subscribeRepository
					.save(new Subscribe(false,course,user));
			return new ResponseEntity<>(subscriber, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	public ResponseEntity<HttpStatus> deleteFromCart(@PathVariable("id") long id) {
		try {
			subscribeRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
