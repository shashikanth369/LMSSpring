package com.gcit.lms.entity;

import java.io.Serializable;

/**
 * Created by shash on 2/25/2017.
 */
public class BookCopies implements Serializable{

    private static final long serialVersionUID = -3256327757486250380L;
    private Book book;
    private Branch branch;
    private int noOfCopies;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public int getNoOfCopies() {
        return noOfCopies;
    }

    public void setNoOfCopies(int noOfCopies) {
        this.noOfCopies = noOfCopies;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookCopies that = (BookCopies) o;

        if (noOfCopies != that.noOfCopies) return false;
        if (book != null ? !book.equals(that.book) : that.book != null) return false;
        return branch != null ? branch.equals(that.branch) : that.branch == null;
    }

    @Override
    public int hashCode() {
        int result = book != null ? book.hashCode() : 0;
        result = 31 * result + (branch != null ? branch.hashCode() : 0);
        result = 31 * result + noOfCopies;
        return result;
    }


}

