package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by shash on 2/25/2017.
 */
public class Genre implements Serializable{


    private int genreId;
    private String genreName;
    private List<Book> books;

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Genre genre = (Genre) o;

        if (genreId != genre.genreId) return false;
        return genreName != null ? genreName.equals(genre.genreName) : genre.genreName == null;
    }

    @Override
    public int hashCode() {
        int result = genreId;
        result = 31 * result + (genreName != null ? genreName.hashCode() : 0);
        return result;
    }
}
