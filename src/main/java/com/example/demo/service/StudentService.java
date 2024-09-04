package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository repo;
	
	public List<Student> findAllstudent(){
		return repo.findAll();
	}
	
	@Cacheable(value = "applicationcache",key = "#id")
	public Student findStudent(int id) {
		Student student = repo.findById(id).get();
		return student;
	}
	public Student create(Student student) {
		return repo.save(student);
	}
	public Student editStudent(int id,Student stud) {
		Student student = repo.findById(id).get();
		student.setName(stud.getName());
		student.setBranch(stud.getBranch());
		repo.save(student);
		return student;
	}
	public String removeStudent(int id) {
		Student student = repo.findById(id).get();
		repo.delete(student);
		return "Deleted Id : "+id;
	}
	
	@CacheEvict(value = "applicationcache",allEntries = true)
	public void clearcache() {
		System.out.println("All caches are cleared");
	}
	
}
