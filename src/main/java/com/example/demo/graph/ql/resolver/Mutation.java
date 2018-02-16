package com.example.demo.graph.ql.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.demo.graph.ql.exception.AuthorNotFoundException;
import com.example.demo.graph.ql.exception.BookNotFoundException;
import com.example.demo.graph.ql.exception.ClassroomNotFoundException;
import com.example.demo.graph.ql.model.Author;
import com.example.demo.graph.ql.model.Book;
import com.example.demo.graph.ql.model.Classroom;
import com.example.demo.graph.ql.model.Student;
import com.example.demo.graph.ql.repository.AuthorRepository;
import com.example.demo.graph.ql.repository.BookRepository;
import com.example.demo.graph.ql.repository.ClassroomRepository;
import com.example.demo.graph.ql.repository.StudentRepository;

public class Mutation implements GraphQLMutationResolver {
	private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private StudentRepository studentRepository;
    private ClassroomRepository classroomRepository;
    
    public Mutation(AuthorRepository authorRepository, 
    		BookRepository bookRepository,
    		StudentRepository studentRepository,
    		ClassroomRepository classroomRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
        this.classroomRepository = classroomRepository;
    }

    public Author newAuthor(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        authorRepository.save(author);

        return author;
    }

    public Book newBook(String title, String isbn, Integer pageCount, Long authorId) {
    	Author author = authorRepository.findOne(authorId);
    	if(author == null) {
    		throw new AuthorNotFoundException("Not found AuthorId", authorId);
    	}
        Book book = new Book();
        book.setAuthor(new Author(authorId));
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPageCount(pageCount != null ? pageCount : 0);

        bookRepository.save(book);

        return book;
    }

    public boolean deleteBook(Long id) {
        bookRepository.delete(id);
        return true;
    }

    public Book updateBookPageCount(Integer pageCount, Long id) {
        Book book = bookRepository.findOne(id);
        if(book == null) {
            throw new BookNotFoundException("Not found bookid", id);
        }
        book.setPageCount(pageCount);

        bookRepository.save(book);

        return book;
    }
    
    public Student newStudent(String firstName, String lastName, Long ClassroomId) {
    	Classroom classroom = classroomRepository.findOne(ClassroomId);
    	if(classroom == null) {
    		throw new ClassroomNotFoundException("Not found ClassroomId", ClassroomId);
    	}
    	Student student = new Student();
    	student.setFirstName(firstName);
    	student.setLastName(lastName);
    	student.setClassroom(new Classroom(ClassroomId) );
    	
    	studentRepository.save(student);

        return student;
    }
    
    public Classroom newClassroom(String title) {
    	Classroom classroom = new Classroom();
    	classroom.setTitle(title);
    	classroomRepository.save(classroom);

        return classroom;
    }
}
