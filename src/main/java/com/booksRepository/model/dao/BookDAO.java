package com.booksRepository.model.dao;

import com.booksRepository.model.domain.Book;

import java.util.List;

/**
 * This class is interface for Book's DAO.
 * @author Igor Ivanov
 */
public interface BookDAO
{
    /**
     * This method return list of all books.
     *
     * @return list of all books.
     */
    List<Book> findAll();

    /**
     * This method receives book and save it to database.
     * @param book - book for saving
     */
    void save(Book book);

    /**
     * This method receives book and update it in database.
     * @param book - book for updating
     */
    void update(Book book);

    /**
     * This method receives id of book and delete it in database.
     * @param id - id of book for deleting.
     */
    void remove(String id);
}
