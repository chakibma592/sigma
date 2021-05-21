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

import com.optimgov.spring.elearning.models.Answer;
import com.optimgov.spring.elearning.models.Question;
import com.optimgov.spring.elearning.repository.AnswerRepository;
import com.optimgov.spring.elearning.repository.QuestionRepository;
import com.optimgov.spring.elearning.repository.QuizRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/Answer")
public class AnswerController {
	@Autowired
    private QuestionRepository questionRepository;
	@Autowired
    private AnswerRepository answerRepository;
	@GetMapping("/list")
	@PreAuthorize("hasRole('USER') OR hasRole('TEACHER') OR hasRole('ADMIN')")
	public ModelMap answer(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {
   
		return new ModelMap().addAttribute("answer", answerRepository.findAll(pageable));

    }

	@GetMapping("/form")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
    public String showForm(@RequestParam(value = "id", required = false) Answer answer, Model m ) {
        if (answer == null) {
        	answer = new Answer();
        }
        m.addAttribute("answer", answer);
        m.addAttribute("question", questionRepository.findAll());
        
        return "/form";
    }

	@PostMapping("/form")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
	public String save(@Valid @ModelAttribute("answer") Answer answer, BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
			return "/form";
        }
        answerRepository.save(answer);
        status.setComplete();
		return "redirect:/list";
    }

	@GetMapping("/delete")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Answer answer ) {
		return new ModelMap("answer", answer);
    }

	@PostMapping("/delete")
	@PreAuthorize("hasRole('ADMIN')OR hasRole('TEACHER')")
    public Object delete(@ModelAttribute Answer answer, SessionStatus status) {
        try{
        	answerRepository.delete(answer);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
					.addObject("entityId",answer.getAnswerlib())
					.addObject("entityName", "answer")
                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/list");
        }
        status.setComplete();
		return "redirect:/list";
    }
}
