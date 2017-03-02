package com.gcit.lms.dao;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;
import com.gcit.lms.entity.Publisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shash on 2/25/2017.
 */
public class BookDAO extends BaseDAO{


    public BookDAO(Connection conn) {
        super(conn);
        // TODO Auto-generated constructor stub
    }



    public void addBook(Book book) throws ClassNotFoundException, SQLException{
        save("insert into tbl_book (title, publisherId) values (?, ?)", new Object[] {book.getTitle(), book.getPublisher().getPublisherId()});
    }


    public void addBookAuthor(Book book,Author author) throws ClassNotFoundException, SQLException {
        save("insert into tbl_book_authors (bookId, authorId) values (?, ?)", new Object[] {book.getBookId(), author.getAuthorId()});

    }

    public void addBookGenre(Book book,Genre genre) throws ClassNotFoundException, SQLException {
        save("insert into tbl_book_genres (genre_id,bookId ) values (?, ?)", new Object[] {genre.getGenreId(), book.getBookId()});

    }

    //GET COUNT OF NUMBER OF AUTHORS
    public Integer getCount() throws ClassNotFoundException, SQLException{
        return getCount("select count(*) from tbl_book");
    }


    public Integer addBookWithID(Book book) throws ClassNotFoundException, SQLException{

        return saveWithID("insert into tbl_book (title, pubId) values (?,?)", new Object[] {book.getTitle(), book.getPublisher().getPublisherId()});
    }

    public void updateBook(Book book) throws ClassNotFoundException, SQLException{
        save("update tbl_book set title = ? where bookId = ?", new Object[] {book.getTitle(), book.getBookId()});
    }

    public void deleteBook(Book book) throws ClassNotFoundException, SQLException{
        save("delete from tbl_book where bookId = ?", new Object[] {book.getBookId()});
    }

    public List<Book> readAllBooks(int pageNo) throws ClassNotFoundException, SQLException{
        setPageNo(pageNo);
        return (List<Book>) readAll("select * from tbl_book", null);
    }

    public Book readBookByID(Integer bookId) throws ClassNotFoundException, SQLException{
        List<Book> books = (List<Book>) readAll("select * from tbl_book where bookId =?", new Object[] {bookId});
        if(books!=null && books.size()>0){
            return books.get(0);
        }
        return null;
    }

    //READ AUTHORS BY NAME
    public List<Book> readBooksByName(String name,int pageNo) throws ClassNotFoundException, SQLException{
        setPageNo(pageNo);
        name="%"+name+"%";
        return (List<Book>) readAll("select * from tbl_book where title like ?", new Object[] {name});
    }

    //READ AUTHORS BY NAME
    public List<Book> readBooksByAuthor(String name,int pageNo) throws ClassNotFoundException, SQLException{
        setPageNo(pageNo);
        name="%"+name+"%";
        return (List<Book>) readAll("select * from tbl_author a inner join tbl_book_authors ba on a.authorId=ba.authorId inner join tbl_book b on ba.bookId = b.bookId where authorName like ?", new Object[] {name});
    }

    //READ AUTHORS BY AUTHOR ID
    public List<Book> readBooksByAuthorId(String authorId,int pageNo) throws ClassNotFoundException, SQLException{
        setPageNo(pageNo);
        return(List<Book>)readAll("select b.title,b.pubId,b.bookId from tbl_author a inner join tbl_book_authors ba on a.authorId=ba.authorId inner join tbl_book b on ba.bookId = b.bookId where a.authorId=?",new Object[] {authorId});
    }


    //READ AUTHORS BY NAME
    public List<Book> readBooksByAuthorOrTitle(String name,int pageNo) throws ClassNotFoundException, SQLException{
        setPageNo(pageNo);
        name="%"+name+"%";
        return (List<Book>) readAll("select * from tbl_author a inner join tbl_book_authors ba on ba.authorId=a.authorId inner join tbl_book b on b.bookId = ba.bookId where a.authorName like ? OR b.title like ?", new Object[] {name,name});
    }

    public List<Book> readBookByBranchID(Integer branchId) throws ClassNotFoundException, SQLException {
        return (List<Book>) readAll("select * from tbl_book where bookId in (select bc.bookId from tbl_book_copies as bc where bc.branchId = ? and noOfCopies>0)", new Object[] {branchId});
    }

    @Override
    public List<?> extractData(ResultSet rs) throws SQLException {
        List<Book> books = new ArrayList<Book>();
        AuthorDAO adao = new AuthorDAO(getConnection());

        while(rs.next()){
            Book b = new Book();
            b.setBookId(rs.getInt("bookId"));
            b.setTitle(rs.getString("title"));

            Publisher p = new Publisher();
            p.setPublisherId(rs.getInt("pubId"));
            b.setPublisher(p);
            try {
                b.setAuthors((List<Author>) adao.readFirstLevel("select * from tbl_author where authorId IN (select authorId from tbl_book_authors where bookId = ?)", new Object[] {b.getBookId()}));

            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            books.add(b);
        }
        return books;
    }



    @Override
    public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
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

}


