package com.gcit.lms.dao;

import com.gcit.lms.entity.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shash on 2/25/2017.
 */
public class BookLoansDAO extends BaseDAO{

    public BookLoansDAO(Connection conn) {
        super(conn);
        // TODO Auto-generated constructor stub
    }


    public List<BookLoans> readAllBookLoans() throws ClassNotFoundException, SQLException{
        return (List<BookLoans>) readAll("select * from tbl_library_loans",null);
    }

    public List<Book> readBookBycardNo(Integer cardNo) throws ClassNotFoundException, SQLException{
        return(List<Book>) readAll("select * from tbl_book as b where b.bookId in (select bookId from tbl_book_loans where cardNo=? and dateIn is NULL)", new Object[] {cardNo});
    }




    public void addAuthor(Author author) throws ClassNotFoundException, SQLException{
        save("insert into tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});
    }

    public void checkIn(Integer bookId, Integer cardNo) throws ClassNotFoundException, SQLException {
        System.out.println(bookId + "incheckin");
        System.out.println(cardNo + "incheckin");
        int branchId = getCount2("select branchId from tbl_book_loans where bookId=? and cardNo = ? and dateIn is NULL", new Object[] {bookId,cardNo});
        System.out.println("branch id: "+branchId);
        save("delete from tbl_book_loans where bookId=? and branchId=? and cardNo = ?",new Object[] {bookId,branchId,cardNo});
        System.out.println("book returned.");
        save("update tbl_book_copies set noOfCopies = noOfCopies+1 where bookId=? and branchId =?",new Object[] {bookId,branchId});
    }

    public void checkOut(int bookId, int cardNo, int branchId) throws ClassNotFoundException, SQLException {
        System.out.println(bookId + "incheckin");
        System.out.println(cardNo + "incheckin");
        System.out.println(branchId + "incheckin");

        save("insert into tbl_book_loans (bookId, branchId, cardNo, dateOut, dueDate ) "
                + "values( ?, ?, ?,CURDATE(),DATE_ADD(CURDATE(),INTERVAL 15 DAY))",new Object[] {bookId,branchId,cardNo});
        System.out.println("entry added to the book loans table");
        save("update tbl_book_copies set noOfCopies = noOfCopies-1 where bookId=? and branchId =?", new Object[] {bookId,branchId});

    }

    @Override
    public List<?> extractData(ResultSet rs) throws SQLException {

        List<Book> books = new ArrayList<Book>();

        while(rs.next()){

            Book b = new Book();

            b.setBookId(rs.getInt("bookId"));
            b.setTitle(rs.getString("title"));

            Publisher p = new Publisher();
            p.setPublisherId(rs.getInt("pubId"));
            b.setPublisher(p);

            books.add(b);

        }

        return books;
    }


    @Override
    public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
        List<BookLoans> bloans = new ArrayList<BookLoans>();
        while(rs.next()){
            BookLoans bl = new BookLoans();
            Book b = new Book();
            Branch br = new Branch();
            Borrower borr = new Borrower();
            b.setBookId(rs.getInt("bookId"));
            bl.setBook(b);
            borr.setCardNo(rs.getInt("cardNo"));
            bl.setBorrower(borr);
            br.setBranchId(rs.getInt("branchId"));
            bl.setBranch(br);
            bl.setDateIn(rs.getDate("dateIn"));
            bl.setDateOut(rs.getDate("dateOut"));
            bl.setDueDate(rs.getDate("dueDate"));
            bloans.add(bl);
        }

        return bloans;
    }

}

