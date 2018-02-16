package com.example.demo.graph.ql.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.graph.ql.model.Author;
import com.example.demo.graph.ql.model.Book;
import com.example.demo.graph.ql.repository.AuthorRepository;

public class BookResolver implements GraphQLResolver<Book> {
	private AuthorRepository authorRepository;

    public BookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Book book) {
        return authorRepository.findOne(book.getAuthor().getId());
    }
}
