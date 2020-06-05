package com.booksRepository.model.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Objects;

/**
 * This class is entity of book.
 *
 * @author Igor Ivanov
 */
@Entity
@SequenceGenerator(name = "mongodb_sequence")
public class Book
{
    /**
     * This field is id of entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mongodb_sequence")
    @Type(type = "objectid")
    private String id;

    /**
     * This field is title of book.
     */
    private String title;

    /**
     * This field is year of book publishing.
     */
    private String year;

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        Book book = (Book) o;
        return year == book.year &&
                Objects.equals(id, book.id) &&
                Objects.equals(title, book.title);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, title, year);
    }

    @Override
    public String toString()
    {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", year=" + year +
                '}';
    }
}
