package com.example.demo.graph.ql.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

public class AuthorNotFoundException extends RuntimeException implements GraphQLError {

	private Map<String, Object> extensions = new HashMap<>();

    public AuthorNotFoundException(String message, Long id) {
        super(message);
        extensions.put("invalidAuthorId", id);
    }
    
    public AuthorNotFoundException(String message) {
        super(message);
    }
    
    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }

}
