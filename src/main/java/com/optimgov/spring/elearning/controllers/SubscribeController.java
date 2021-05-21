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

import com.optimgov.spring.elearning.models.Lesson;
import com.optimgov.spring.elearning.models.Subscribe;
import com.optimgov.spring.elearning.repository.CourseRepository;
import com.optimgov.spring.elearning.repository.SubscribeRepository;
import com.optimgov.spring.elearning.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/subscribe")
public class SubscribeController {
	@Autowired
    private SubscribeRepository subscribeRepository;
	@Autowired
    private CourseRepository courseRepository;
	@Autowired
    private UserRepository userRepository;
	@GetMapping("/list")
	@PreAuthorize("hasRole('USER') OR hasRole('TEACHER') OR hasRole('ADMIN')")
	public ModelMap subscribe(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {
   
		return new ModelMap().addAttribute("subscribe", subscribeRepository.findAll(pageable));

    }

	@GetMapping("/form")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
    public String showForm(@RequestParam(value = "id", required = false) Subscribe subscribe, Model m ) {
        if (subscribe == null) {
        	subscribe= new Subscribe();
        }
        m.addAttribute("subscribe", subscribe);
        m.addAttribute("course", courseRepository.findAll());
        m.addAttribute("user", userRepository.findAll());
        
        return "/form";
    }

	@PostMapping("/form")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
	public String save(@Valid @ModelAttribute("subscribe") Subscribe subscribe, BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
			return "/form";
        }
        subscribeRepository.save(subscribe);
        status.setComplete();
		return "redirect:/list";
    }

	@GetMapping("/delete")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Subscribe subscribe ) {
		return new ModelMap("subscribe", subscribe);
    }

	@PostMapping("/delete")
	@PreAuthorize("hasRole('ADMIN')OR hasRole('TEACHER')")
    public Object delete(@ModelAttribute Subscribe subscribe, SessionStatus status) {
        try{
        	subscribeRepository.delete(subscribe);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
					.addObject("entityId", subscribe.getId())
					.addObject("entityName", "subscribe")
                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/list");
        }
        status.setComplete();
		return "redirect:/list";
    }
}
