package com.discExchange.dao;

import com.discExchange.model.AuthorEntity;
import com.discExchange.model.BookEntity;
import lombok.Setter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class BooksDaoImpl implements BooksDao {

    private static final Logger logger = LoggerFactory.getLogger(BooksDaoImpl.class);

    @Setter
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<BookEntity> getAllBooks() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from BookEntity order by id");

        List<BookEntity> result = query.list();
        return result;
    }

    @Override
    public List<AuthorEntity> getAllAuthors() {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from AuthorEntity order by id");

        List<AuthorEntity> result = query.list();
        return result;
    }
}
