package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shash on 2/25/2017.
 */
public class Author implements Serializable {

    private static final long serialVersionUID = -5291176225610273089L;
    private int authorId;
    private String authorName;
    private List<Book> books;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (authorId != author.authorId) return false;
        return authorName != null ? authorName.equals(author.authorName) : author.authorName == null;
    }

    @Override
    public int hashCode() {
        int result = authorId;
        result = 31 * result + (authorName != null ? authorName.hashCode() : 0);
        return result;
    }
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }



}
