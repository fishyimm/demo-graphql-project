package com.example.demo.graph.ql.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.graph.ql.model.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
