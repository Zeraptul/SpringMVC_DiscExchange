package com.discExchange.dao;

import com.discExchange.model.AuthorEntity;
import com.discExchange.model.BookEntity;

import java.util.List;

public interface BookDao {
    List<BookEntity> getAllBooks();
    List<AuthorEntity> getAllAuthors();
}
