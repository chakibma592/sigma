package com.optimgov.spring.elearning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.optimgov.spring.elearning.repository.StudiesLevelRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/level")
public class StudiesLevelController {
	@Autowired
    private StudiesLevelRepository studiesLevelRepository;
}
