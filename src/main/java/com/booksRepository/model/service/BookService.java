package com.booksRepository.model.service;

import com.booksRepository.model.domain.Book;

import java.util.List;

/**
 * This class is interface of book's service.
 *
 * @author Igor Ivanov
 */
public interface BookService
{
    /**
     * This method return list of all books.
     *
     * @return list of all books.
     */
    List<Book> getAllBooks();

    /**
     * This method receives book and passes it to DAO.
     *
     * @param book - book for saving
     */
    void save(Book book);

    /**
     * This method receives id of book and passes it to DAO.
     *
     * @param id - id of book for deleting.
     */
    void delete(String id);

    /**
     * This method receives book and and passes it to DAO.
     *
     * @param book - book for updating
     */
    void update(Book book);
}
