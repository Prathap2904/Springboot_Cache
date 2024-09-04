package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;

import jakarta.annotation.PostConstruct;


@RestController
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@GetMapping("/students")
	public List<Student> getAllstudent(){
		List<Student> stud= studentService.findAllstudent();
		return stud;
	}
	
	@GetMapping("/students/{id}")
	public Student getStudent(@PathVariable int id) {
		return studentService.findStudent(id);
	}
	
	@PostMapping("/students/add")
	public Student createStudent(@RequestBody Student student) {
		return studentService.create(student);
	}
	
	@PutMapping("/students/update/{id}")
	public Student updateStudent(@PathVariable int id,Student stud) {
		return studentService.editStudent(id,stud);
	}
	@DeleteMapping("/students/delete/{id}")
	public String deleteStudent(@PathVariable int id) {
		return studentService.removeStudent(id);
	}
	
	@GetMapping("/students/delete")
	public String clearcache() {
		studentService.clearcache();
		return "Caches are cleared";
	}
	
}
