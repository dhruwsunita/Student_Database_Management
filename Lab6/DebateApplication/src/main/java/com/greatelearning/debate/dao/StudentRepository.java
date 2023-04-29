package com.greatelearning.debate.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greatelearning.debate.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}
