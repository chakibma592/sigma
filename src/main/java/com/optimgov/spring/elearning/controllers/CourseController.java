package com.optimgov.spring.elearning.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import com.optimgov.spring.elearning.models.Course;
import com.optimgov.spring.elearning.repository.CourseRepository;
import com.optimgov.spring.elearning.repository.TopicRepository;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/course")
public class CourseController {
	@Autowired
    private CourseRepository courseRepository;
	@Autowired
    private TopicRepository topicRepository;
	@GetMapping("/list")
	@PreAuthorize("hasRole('USER') OR hasRole('TEACHER')")
	public ModelMap course(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {
   
		return new ModelMap().addAttribute("course", courseRepository.findAll(pageable));

    }

	@GetMapping("/form")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
    public String showForm(@RequestParam(value = "id", required = false) Course course, Model m ) {
        if (course == null) {
        	course = new Course();
        }
        m.addAttribute("course", course);
        m.addAttribute("topic", topicRepository.findAll());
        
        return "/form";
    }

	@PostMapping("/form")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
	public String save(@Valid @ModelAttribute("course") Course course, BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
			return "/form";
        }
        courseRepository.save(course);
        status.setComplete();
		return "redirect:/list";
    }

	@GetMapping("/delete")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Course course ) {
		return new ModelMap("course", course);
    }

	@PostMapping("/delete")
	@PreAuthorize("hasRole('ADMIN')OR hasRole('TEACHER')")
    public Object delete(@ModelAttribute Course course, SessionStatus status) {
        try{
        	courseRepository.delete(course);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
					.addObject("entityId", course.getCoursename())
					.addObject("entityName", "course")
                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/list");
        }
        status.setComplete();
		return "redirect:/list";
    }
}
