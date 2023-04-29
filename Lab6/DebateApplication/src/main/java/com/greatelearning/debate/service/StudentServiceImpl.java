package com.greatelearning.debate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatelearning.debate.dao.StudentRepository;
import com.greatelearning.debate.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<Student> findAll() {
		List<Student> students = studentRepository.findAll();
		return students;
	}

	@Override
	public Student findById(int theId) {
		Student student = new Student();
		student = studentRepository.findById(theId).get();
		return student;
	}

	@Override
	public void save(Student theStudent) {
		studentRepository.save(theStudent);

	}

	@Override
	public void deleteById(int theId) {
		studentRepository.deleteById(theId);

	}

}
