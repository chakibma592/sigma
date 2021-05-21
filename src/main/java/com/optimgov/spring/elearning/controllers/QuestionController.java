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

import com.optimgov.spring.elearning.models.Question;
import com.optimgov.spring.elearning.repository.QuestionRepository;
import com.optimgov.spring.elearning.repository.QuizRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/Question")
public class QuestionController {
	@Autowired
    private QuestionRepository questionRepository;
	@Autowired
    private QuizRepository quizRepository;
	@GetMapping("/list")
	@PreAuthorize("hasRole('USER') OR hasRole('TEACHER') OR hasRole('ADMIN')")
	public ModelMap question(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {
   
		return new ModelMap().addAttribute("question", quizRepository.findAll(pageable));

    }

	@GetMapping("/form")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
    public String showForm(@RequestParam(value = "id", required = false) Question question, Model m ) {
        if (question == null) {
        	question = new Question();
        }
        m.addAttribute("question", question);
        m.addAttribute("quiz", quizRepository.findAll());
        
        return "/form";
    }

	@PostMapping("/form")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
	public String save(@Valid @ModelAttribute("question") Question question, BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
			return "/form";
        }
        questionRepository.save(question);
        status.setComplete();
		return "redirect:/list";
    }

	@GetMapping("/delete")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Question question ) {
		return new ModelMap("question", question);
    }

	@PostMapping("/delete")
	@PreAuthorize("hasRole('ADMIN')OR hasRole('TEACHER')")
    public Object delete(@ModelAttribute Question question, SessionStatus status) {
        try{
        	questionRepository.delete(question);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
					.addObject("entityId",question.getQuetionlib())
					.addObject("entityName", "question")
                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/list");
        }
        status.setComplete();
		return "redirect:/list";
    }
}
