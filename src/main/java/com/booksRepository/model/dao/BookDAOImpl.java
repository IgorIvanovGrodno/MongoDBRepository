package com.booksRepository.model.dao;

import com.booksRepository.model.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.List;

/**
 * This class is implementation of BookDAO interface.
 *
 * @author Igor Ivanov
 */
@Repository
@Transactional
public class BookDAOImpl implements BookDAO
{
    /**
     * This field is entity manager factory.
     */
    @Autowired
    private EntityManagerFactory entityManagerFactory;
    /**
     * This field is entity manager.
     */
    private EntityManager entityManager;

    /**
     * This method return list of all books.
     *
     * @return list of all books.
     */
    @Override
    public List<Book> findAll()
    {
        EntityTransaction entityTransaction = getEntityManager().getTransaction();
        entityTransaction.begin();
        String nativeQuery = "db.Book.find({});";
        Query query = getEntityManager().createNativeQuery(nativeQuery, Book.class);
        List<Book> books = query.getResultList();
        entityTransaction.commit();
        return books;
    }

    /**
     * This method receives book and save it to database.
     *
     * @param book - book for saving
     */
    @Override
    public void save(Book book)
    {
        EntityTransaction entityTransaction = getEntityManager().getTransaction();
        entityTransaction.begin();
        String nativeQuery = "db.Book.insertOne({ 'title': '" + book.getTitle()
                + "', 'year':'" + book.getYear() + "' });";
        Query query = getEntityManager().createNativeQuery(nativeQuery);
        query.executeUpdate();
        entityTransaction.commit();
    }

    /**
     * This method receives book and update it in database.
     *
     * @param book - book for updating
     */
    @Override
    public void update(Book book)
    {
        EntityManager entityManager = getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        String nativeQuery = "db.Book.update({ '_id': ObjectId('" + book.getId() + "') }," +
                " {$set :{'title': '" + book.getTitle() + "', 'year': '" + book.getYear() + "'}});";
        Query query = entityManager.createNativeQuery(nativeQuery);
        query.executeUpdate();
        Book persistBook = entityManager.find(Book.class, book.getId());
        entityManager.refresh(persistBook);
        entityTransaction.commit();
    }

    /**
     * This method receives id of book and delete it in database.
     *
     * @param id - id of book for deleting.
     */
    @Override
    public void remove(String id)
    {
        EntityTransaction entityTransaction = getEntityManager().getTransaction();
        entityTransaction.begin();
        String nativeQuery = "db.Book.deleteOne({ '_id':  ObjectId('" + id + "') });";
        Query query = getEntityManager().createNativeQuery(nativeQuery);
        query.executeUpdate();
        entityTransaction.commit();
    }

    /**
     * This method returns entity manager.
     *
     * @return entity manager.
     */
    private EntityManager getEntityManager()
    {
        if (entityManager == null)
        {
            entityManager = entityManagerFactory.createEntityManager();
        }
        return entityManager;
    }
}
