package com.example.demo.graph.ql.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.graph.ql.model.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
//	List<Author> findByLastName(String lastName);
}
