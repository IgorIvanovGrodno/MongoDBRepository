package com.booksRepository.model.service;

import com.booksRepository.model.dao.BookDAO;
import com.booksRepository.model.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is implementation of BookService.
 *
 * @author Igor Ivanov
 */
@Service
public class BookServiceImpl implements BookService {
    /**
     * This field is book's DAO.
     */
    private BookDAO bookDAO;

    /**
     * Constructor which receives and assigns book's DAO.
     *
     * @param bookDAO - book's DAO.
     */
    @Autowired
    public BookServiceImpl(BookDAO bookDAO)
    {
        this.bookDAO = bookDAO;
    }

    /**
     * This method return list of all books.
     *
     * @return list of all books.
     */
    @Override
    public List<Book> getAllBooks()
    {
        return bookDAO.findAll();
    }

    /**
     * This method receives book and passes it to bookDAO.save(book).
     *
     * @param book - book for saving
     */
    @Override
    public void save(Book book)
    {
        bookDAO.save(book);
    }

    /**
     * This method receives id of book and passes it to bookDAO.remove(id).
     *
     * @param id - id of book for deleting.
     */
    @Override
    public void delete(String id)
    {
        bookDAO.remove(id);
    }

    /**
     * This method receives book and and passes it to  bookDAO.update(book).
     *
     * @param book - book for updating
     */
    @Override
    public void update(Book book)
    {
        bookDAO.update(book);
    }
}
