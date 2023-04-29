package com.greatelearning.debate.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatelearning.debate.entity.Student;
import com.greatelearning.debate.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/list")
	public String listStudents(Model model) {
		List<Student> theStudents = studentService.findAll();
		model.addAttribute("Students", theStudents);
		return "list_students";
	}

	@RequestMapping("/showFormForAdd")
	public String addStudent(Model model) {
		Student theStudent = new Student();
		model.addAttribute("Student", theStudent);
		return "add_student";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("studentId") int theId, Model theModel) {

		// get the student from the service
		Student theStudent = studentService.findById(theId);

		// set student as a model attribute to pre-populate the form
		theModel.addAttribute("Student", theStudent);

		// send to form
		return "add_student";
	}

	// save the book
	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("course") String course,
			@RequestParam("country") String country) {

		Student theStudent;
		if (id != 0) {
			theStudent = studentService.findById(id);
			theStudent.setFirstName(firstName);
			theStudent.setLastName(lastName);
			theStudent.setCourse(course);
			theStudent.setCountry(country);
		} else {
			theStudent = new Student(firstName, lastName, course, country);
		}
		studentService.save(theStudent);
		return "redirect:/students/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int theId) {
		studentService.deleteById(theId);
		return "redirect:/students/list";
	}

	@RequestMapping("/403")
	public ModelAndView accessDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + " you do not have permission to access to this page!");
		} else {
			model.addObject("msg", "You have permission to access this page!");
		}
		model.setViewName("403");
		return model;
	}

}
