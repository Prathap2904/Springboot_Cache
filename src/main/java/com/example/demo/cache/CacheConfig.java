package com.example.demo.cache;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;

import jakarta.annotation.PostConstruct;
@Configuration
@EnableCaching
public class CacheConfig {
	
	@Autowired
	private CacheManager cachemanager;
	
	@Autowired
	private StudentService service;
	
	@PostConstruct
	public void preloadCache() {
		
		Cache cache = cachemanager.getCache("applicationcache");
		
		System.out.println("Initializing cache");
		
		List<Student> studentlist = service.findAllstudent();
		
		for(Student student:studentlist) {
			cache.put(student.getRollno(),student);
		}
	}

}
