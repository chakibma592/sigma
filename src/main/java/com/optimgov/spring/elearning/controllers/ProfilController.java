package com.optimgov.spring.elearning.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.optimgov.spring.elearning.models.Profession;
import com.optimgov.spring.elearning.models.StudiesLevel;
import com.optimgov.spring.elearning.models.User;
import com.optimgov.spring.elearning.payload.request.ProfilRequest;
import com.optimgov.spring.elearning.repository.ProfessionRepository;
import com.optimgov.spring.elearning.repository.StudiesLevelRepository;
import com.optimgov.spring.elearning.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profil")
public class ProfilController {
	@Autowired
    private UserRepository userRepository;
	@Autowired
    private ProfessionRepository professionRepository;
	@Autowired
    private StudiesLevelRepository levelRepository;
	@PutMapping("/update/{id}")
	public ResponseEntity<User> updateProfil(@PathVariable("id") long id,@RequestBody ProfilRequest profilrequest) {
		//Optional<User> userData = userRepository.findById(id);
		//System.out.println("dkhalt");
		//if (userData.isPresent()) {
			
			User user = userRepository.findByUserId(id);
		    user.setFirstname(profilrequest.getFirstname());
		    user.setLastname(profilrequest.getLastname());
		    user.setEmail(profilrequest.getEmail());
		    user.setBirthday(profilrequest.getBirthday());
			Optional<Profession> optionnalprofession= professionRepository.findById(profilrequest.getProfessionid());
			if(optionnalprofession.isPresent()) {
				
			Profession profession=optionnalprofession.get();
			user.setProfession(profession);}
			Optional<StudiesLevel> optionnallevel= levelRepository.findById(profilrequest.getLevelid());
			if(optionnallevel.isPresent()) {
			StudiesLevel level=optionnallevel.get();
			user.setStudieslevel(level);}
			
			return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
		//} else {
		//	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		//}
	}


}
