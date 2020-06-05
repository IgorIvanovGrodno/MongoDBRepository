package com.booksRepository.controllers;

import com.booksRepository.model.domain.Book;
import com.booksRepository.model.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This class is controller for index and update pages.
 *
 * @author Igor Ivanov
 */
@Controller
public class BookController
{
    /**
     * This field is book's service.
     */
    private BookService bookService;

    /**
     * Constructor which receives and assigns book's service.
     *
     * @param bookService book's service.
     */
    @Autowired
    public BookController(BookService bookService)
    {
        this.bookService = bookService;
    }

    /**
     * This method handles GET request "/", adds list of books and "newBook" to model and returns the model and "index" page.
     *
     * @param model model.
     * @return "index" page and model.
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexPage(Model model)
    {
        model.addAttribute("allBooks", bookService.getAllBooks());
        model.addAttribute("newBook", new Book());
        return "index";
    }

    /**
     * This method handles GET request "/book/{id}", adds "updateBook" to model and returns model and "update" page.
     *
     * @param id    - id book for updating
     * @param model - model.
     * @return model and returns model and "update" page.
     */
    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public String getUpdatePage(@PathVariable("id") String id, Model model)
    {
        Book updateBook = new Book();
        updateBook.setId(id);
        model.addAttribute("updateBook", updateBook);
        return "update";
    }

    /**
     * This method handles PUT request "/book", calls and passes received book to bookService.save(book) method.
     *
     * @param book - book for saving.
     * @return redirect to "/".
     */
    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    public String putBook(Book book)
    {
        bookService.save(book);
        return "redirect:/";
    }

    /**
     * This method handles DELETE request "/book/{id}", calls and passes received id to bookService.delete(id) method.
     *
     * @param id - id of book for deleting.
     * @return redirect to "/".
     */
    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public String deleteBook(@PathVariable("id") String id)
    {
        bookService.delete(id);
        return "redirect:/";
    }

    /**
     * This method handles POST request "/book/{id}", calls and passes received id to bookService.update(id) method.
     *
     * @param book - book for updating.
     * @return redirect to "/".
     */
    @RequestMapping(value = "/book/{id}", method = RequestMethod.POST)
    public String updateBook(Book book)
    {
        bookService.update(book);
        return "redirect:/";
    }
}
