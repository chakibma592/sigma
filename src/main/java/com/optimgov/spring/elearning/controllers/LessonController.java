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
import com.optimgov.spring.elearning.repository.CourseRepository;
import com.optimgov.spring.elearning.repository.LessonRepository;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/lesson")
public class LessonController {
	@Autowired
    private LessonRepository lessonRepository;
	@Autowired
    private CourseRepository courseRepository;
	@GetMapping("/list")
	@PreAuthorize("hasRole('USER') OR hasRole('TEACHER') OR hasRole('ADMIN')")
	public ModelMap lesson(Pageable pageable, @RequestParam(name = "value", required = false) String value,
			Model model) {
   
		return new ModelMap().addAttribute("lesson", lessonRepository.findAll(pageable));

    }

	@GetMapping("/form")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
    public String showForm(@RequestParam(value = "id", required = false) Lesson lesson, Model m ) {
        if (lesson == null) {
        	lesson = new Lesson();
        }
        m.addAttribute("lesson", lesson);
        m.addAttribute("course", courseRepository.findAll());
        
        return "/form";
    }

	@PostMapping("/form")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
	public String save(@Valid @ModelAttribute("lesson") Lesson lesson, BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
			return "/form";
        }
        lessonRepository.save(lesson);
        status.setComplete();
		return "redirect:/list";
    }

	@GetMapping("/delete")
	@PreAuthorize("hasRole('ADMIN') OR hasRole('TEACHER')")
    public ModelMap deleteConfirm(@RequestParam(value = "id", required = true) Lesson lesson ) {
		return new ModelMap("lesson", lesson);
    }

	@PostMapping("/delete")
	@PreAuthorize("hasRole('ADMIN')OR hasRole('TEACHER')")
    public Object delete(@ModelAttribute Lesson lesson, SessionStatus status) {
        try{
        	lessonRepository.delete(lesson);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
					.addObject("entityId", lesson.getLessonname())
					.addObject("entityName", "lesson")
                    .addObject("errorCause", exception.getRootCause().getMessage())
					.addObject("backLink", "/list");
        }
        status.setComplete();
		return "redirect:/list";
    }
}
