package com.koreait.Springtest4.student.domain.web.controller;



import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.koreait.Springtest4.student.domain.student.Student;
import com.koreait.Springtest4.student.domain.student.StudentRepository;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
public class StudentController {
	private final StudentRepository studentRepository;
	
	// 목록
	@GetMapping("/students")
	public String findAllList(Model model) {
		List<Student> student = studentRepository.findAll();
		model.addAttribute("student",student);
		return "student/students";
	}
	
	// 등록
	@GetMapping("/add")
	public String studentAddForm(Model model) {
		return "student/studentAddForm";
	}
	
	@PostMapping("/add")
	public String studentAdd(@ModelAttribute("student")Student student ) {
		studentRepository.save(student);
		return "/student/student";
	}
	
	@GetMapping("/{Id}")
	public String student(@PathVariable Long Id, Model model) {
		Student student = studentRepository.findById(Id);
		model.addAttribute("student", student);
		return "student/student";
	}
	
	
	
	// 수정
	@GetMapping("/{Id}/edit")
	public String editForm(@PathVariable Long Id, Model model) {
		Student student = studentRepository.findById(Id);
		model.addAttribute("student", student);
		return "student/studentEditForm";
	}
	
	// 수정저장
	@PostMapping("/{Id}/edit")
	public String edit(@PathVariable Long Id, @ModelAttribute Student student) {
		studentRepository.update(Id, student);
		return "redirect:/student/{Id}";
	}
	
	
	
	
	
	
	@PostConstruct
	public void init() {
		studentRepository.save(new Student("학생A", 20, 5, "01012345678", "역삼1동"));
		studentRepository.save(new Student("학생B", 22, 5, "01012345678", "역삼2동"));
	}
	
	
	
	
	
	
}
