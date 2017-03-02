package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shash on 2/25/2017.
 */
public class BookLoans implements Serializable{
    private Book book;


    private Branch branch;
    private int cardNo;
    private Date dateOut;
    private Date dueDate;
    private Date dateIn;
    private Borrower borrower;

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

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

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookLoans bookLoans = (BookLoans) o;

        if (cardNo != bookLoans.cardNo) return false;
        if (book != null ? !book.equals(bookLoans.book) : bookLoans.book != null) return false;
        if (dueDate != null ? !dueDate.equals(bookLoans.dueDate) : bookLoans.dueDate != null) return false;
        return dateIn != null ? dateIn.equals(bookLoans.dateIn) : bookLoans.dateIn == null;
    }

    @Override
    public int hashCode() {
        int result = book != null ? book.hashCode() : 0;
        result = 31 * result + cardNo;
        result = 31 * result + (dueDate != null ? dueDate.hashCode() : 0);
        result = 31 * result + (dateIn != null ? dateIn.hashCode() : 0);
        return result;
    }



}


