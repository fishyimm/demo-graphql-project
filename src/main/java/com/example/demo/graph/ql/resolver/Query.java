package com.example.demo.graph.ql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.graph.ql.model.Author;
import com.example.demo.graph.ql.model.Book;
import com.example.demo.graph.ql.model.Classroom;
import com.example.demo.graph.ql.model.Student;
import com.example.demo.graph.ql.repository.AuthorRepository;
import com.example.demo.graph.ql.repository.BookRepository;
import com.example.demo.graph.ql.repository.ClassroomRepository;
import com.example.demo.graph.ql.repository.StudentRepository;

public class Query implements GraphQLQueryResolver {
	private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private StudentRepository studentRepository;
    private ClassroomRepository classroomRepository;
    
    public Query(
    		AuthorRepository authorRepository, 
    		BookRepository bookRepository,
    		StudentRepository studentRepository,
    		ClassroomRepository classroomRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
    }
    
    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public Iterable<Student> findAllStudents() {
    	return studentRepository.findAll();
    }
    
    public Iterable<Classroom> findAllClassrooms() {
    	return classroomRepository.findAll();
    }
    
    public long countBooks() {
        return bookRepository.count();
    }
    public long countAuthors() {
        return authorRepository.count();
    }
    public long countStudents() {
        return studentRepository.count();
    }
    public long countClassrooms() {
        return classroomRepository.count();
    }
    
    public String test() {
    	return "test function";
    }
}
