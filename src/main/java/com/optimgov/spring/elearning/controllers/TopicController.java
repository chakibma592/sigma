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

import com.optimgov.spring.elearning.models.Topic;
import com.optimgov.spring.elearning.repository.TopicRepository;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/topic")
public class TopicController {
	@Autowired
    private TopicRepository topicRepository;
	@GetMapping("/list")
	@PreAuthorize("hasRole('USER')")
	public ModelMap topic(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {
   
		return new ModelMap().addAttribute("grade", topicRepository.findAll(pageable));

    }

	@GetMapping("/form")
	@PreAuthorize("hasRole('ADMIN')")
    public String showForm(@RequestParam(value = "id", required = false) Topic topic, Model m ) {
        if (topic == null) {
        	topic = new Topic();
        }
        m.addAttribute("topic", topic);
        
        return "/form";
    }

	@PostMapping("/form")
	@PreAuthorize("hasRole('ADMIN')")
	public String save(@Valid @ModelAttribute("topic") Topic topic, BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
			return "/form";
        }
        topicRepository.save(topic);
        status.setComplete();
		return "redirect:/list";
    }

	@GetMapping("/delete")
	@PreAuthorize("hasRole('ADMIN')")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Topic topic ) {
		return new ModelMap("topic", topic);
    }

	@PostMapping("/delete")
	@PreAuthorize("hasRole('ADMIN')")
    public Object delete(@ModelAttribute Topic topic, SessionStatus status) {
        try{
        	topicRepository.delete(topic);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
					.addObject("entityId", topic.getTopicname())
					.addObject("entityName", "Topic")
                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/list");
        }
        status.setComplete();
		return "redirect:/list";
    }
}
