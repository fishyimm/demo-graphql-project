package com.example.demo.graph.ql.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.graph.ql.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> {

}
