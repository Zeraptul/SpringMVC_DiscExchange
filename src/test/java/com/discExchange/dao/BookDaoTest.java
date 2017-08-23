package com.discExchange.dao;

import com.discExchange.model.AuthorEntity;
import com.discExchange.model.BookEntity;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

@ContextConfiguration("classpath:spring/servlet-context-test_2.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class BookDaoTest {

    @Autowired
    @Qualifier("bookDao")
    BookDao booksDao;

    @Transactional
    @Test
    public void getAllBooks(){
        List<BookEntity> bookEntityList = booksDao.getAllBooks();
        Assert.assertTrue(bookEntityList.size() > 0);
    }

    @Transactional
    @Test
    public void getBook_firstMultipleAuthors_success(){
        List<BookEntity> bookEntityList = booksDao.getAllBooks();
        BookEntity book = bookEntityList.get(0);
        Assert.assertTrue(book.getAuthors().size() >= 2);
    }

    @Transactional
    @Test
    public void getAllAuthors(){
        List<AuthorEntity> allAuthors = booksDao.getAllAuthors();
        Assert.assertTrue(allAuthors.size() > 0);
    }


    @Transactional
    @Test
    public void getBook_itsAuthors_success(){
        List<BookEntity> bookEntityList = booksDao.getAllBooks();
        BookEntity book = bookEntityList.get(0);
        Assert.assertTrue(book.getAuthors().size() > 0);
    }

    @Transactional
    @Test
    public void getAuthor_itsBooks_success(){
        List<AuthorEntity> allAuthors = booksDao.getAllAuthors();
        AuthorEntity authorEntity = allAuthors.get(0);

        Assert.assertTrue(authorEntity.getBooks().size() > 0);
    }
}

