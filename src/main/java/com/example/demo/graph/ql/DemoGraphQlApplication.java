package com.example.demo.graph.ql;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.graph.ql.exception.GraphQLErrorAdapter;
import com.example.demo.graph.ql.model.Author;
import com.example.demo.graph.ql.model.Book;
import com.example.demo.graph.ql.model.Classroom;
import com.example.demo.graph.ql.model.Student;
import com.example.demo.graph.ql.repository.AuthorRepository;
import com.example.demo.graph.ql.repository.BookRepository;
import com.example.demo.graph.ql.repository.ClassroomRepository;
import com.example.demo.graph.ql.repository.StudentRepository;
import com.example.demo.graph.ql.resolver.BookResolver;
import com.example.demo.graph.ql.resolver.Mutation;
import com.example.demo.graph.ql.resolver.Query;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;

@SpringBootApplication
public class DemoGraphQlApplication {

//	https://www.pluralsight.com/guides/java-and-j2ee/building-a-graphql-server-with-spring-boot
//	https://github.com/graphql-java/graphql-spring-boot
//	https://github.com/graphql-java/graphql-java-tools
	
//	http://localhost:9000/graphql/schema.json
//	http://localhost:9000/graphiql
	public static void main(String[] args) {
		SpringApplication.run(DemoGraphQlApplication.class, args);
	}
	
	@Bean
	public GraphQLErrorHandler errorHandler() {
		return new GraphQLErrorHandler() {
			@Override
			public List<GraphQLError> processErrors(List<GraphQLError> errors) {
				List<GraphQLError> clientErrors = errors.stream()
						.filter(this::isClientError)
						.collect(Collectors.toList());

				List<GraphQLError> serverErrors = errors.stream()
						.filter(e -> !isClientError(e))
						.map(GraphQLErrorAdapter::new)
						.collect(Collectors.toList());

				List<GraphQLError> e = new ArrayList<>();
				e.addAll(clientErrors);
				e.addAll(serverErrors);
				return e;
			}

			protected boolean isClientError(GraphQLError error) {
				return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
			}
		};
	}
	
//	@Bean
//	public BookResolver authorResolver(AuthorRepository authorRepository) {
//		return new BookResolver(authorRepository);
//	}
	
	@Bean
	public Query query(AuthorRepository authorRepository, 
    		BookRepository bookRepository,
    		StudentRepository studentRepository,
    		ClassroomRepository classroomRepository) {
		return new Query(authorRepository, 
	    		bookRepository,
	    		studentRepository,
	    		classroomRepository);
	}

	@Bean
	public Mutation mutation(AuthorRepository authorRepository, 
    		BookRepository bookRepository,
    		StudentRepository studentRepository,
    		ClassroomRepository classroomRepository) {
		return new Mutation(authorRepository, 
	    		bookRepository,
	    		studentRepository,
	    		classroomRepository);
	}

	@Bean
	public CommandLineRunner demo(
			AuthorRepository authorRepository, 
			BookRepository bookRepository,
			StudentRepository studentRepository,
			ClassroomRepository classroomRepository) {
		return (args) -> {
			Author author = new Author("Fish", "Yimm");
			authorRepository.save(author);

			bookRepository.save(new Book("Fishyimm Edition", "123456789", 123, author));
			
			Classroom classroom = new Classroom("class a");
			classroomRepository.save(classroom);
			
			studentRepository.save(new Student("tum", "ja", classroom));
		};
	}
}
