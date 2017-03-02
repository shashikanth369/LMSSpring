package com.gcit.lms.dao;

import com.gcit.lms.entity.Author;
import com.gcit.lms.entity.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shash on 2/25/2017.
 */
public class AuthorDAO extends BaseDAO {


    public AuthorDAO(Connection conn) {
        super(conn);
        // TODO Auto-generated constructor stub
    }

    //ADD AUTHOR
    public void addAuthor(Author author) throws ClassNotFoundException, SQLException {
        save("insert into tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});
    }

    //ADD AUTHOR BY ID
    public void addAuthorWithID(Author author) throws ClassNotFoundException, SQLException{
        saveWithID("insert into tbl_author (authorName) values (?)", new Object[] {author.getAuthorName()});
    }

    //UPDATE AUTHOR
    public void updateAuthor(Author author) throws ClassNotFoundException, SQLException{
        save("update tbl_author set authorName = ? where authorId = ?", new Object[] {author.getAuthorName(), author.getAuthorId()});
    }

    //DELETE AUTHOR
    public void deleteAuthor(Author author) throws ClassNotFoundException, SQLException{
        save("delete from tbl_author where authorId = ?", new Object[] {author.getAuthorId()});
    }

    //READ ALL AUTHORS
    public List<Author> readAllAuthors(int pageNo) throws ClassNotFoundException, SQLException{
        setPageNo(pageNo);
        return (List<Author>) readAll("select * from tbl_author", null);
    }

    //READ AUTHORS BY NAME
    public List<Author> readAuthorsByName(String name) throws ClassNotFoundException, SQLException{
        //setPageNo(pageNo);
        name="%"+name+"%";
        return (List<Author>) readAll("select * from tbl_author where authorName like ?", new Object[] {name});
    }

    //READ AUTHOR BY ID
    public Author readAuthorsByID(Integer authorId) throws ClassNotFoundException, SQLException{
        List<Author> authors = (List<Author>) readAll("select * from tbl_author where authorId =?", new Object[] {authorId});
        if(authors!=null && authors.size()>0){
            return authors.get(0);
        }
        return null;
    }


    //GET COUNT OF NUMBER OF AUTHORS
    public Integer getCount() throws ClassNotFoundException, SQLException{
        return getCount("select count(*) from tbl_author");
    }

    //DELETE AUTHOR
    public void deleteAuthor(Integer authorId) throws ClassNotFoundException, SQLException {
        save("delete from tbl_author where authorId =?",new Object[]{authorId});
    }


    //READ AUTHORS BY NAME
    public List<Author> readAuthorsByAuthorName(String name,int pageNo) throws ClassNotFoundException, SQLException{
        setPageNo(pageNo);
        name="%"+name+"%";
        return (List<Author>) readAll("select * from tbl_author where authorName like ?", new Object[] {name});
    }

    //READ AUTHORS BY BOOK TITLE
    public List<Author> readAuthorsByBookTitle(String name,int pageNo) throws ClassNotFoundException, SQLException{
        setPageNo(pageNo);
        name="%"+name+"%";
        return (List<Author>) readAll("select * from tbl_author a inner join tbl_book_authors ba on ba.authorId = a.authorId inner join tbl_book b on b.bookId = ba.bookId where b.title like ?", new Object[] {name});
    }

    //READ AUTHORS BY NAME OR TITLE
    public List<Author> readAuthorsByBookTitleorName(String name,int pageNo) throws ClassNotFoundException, SQLException{
        setPageNo(pageNo);
        name="%"+name+"%";
        return (List<Author>) readAll("select * from tbl_author a inner join tbl_book_authors ba on ba.authorId = a.authorId inner join tbl_book b on b.bookId = ba.bookId where b.title like ? or a.authorName like ?", new Object[] {name,name});
    }

    @Override
    public List<Author> extractData(ResultSet rs) throws SQLException {
        List<Author> authors = new ArrayList<Author>();
        BookDAO bdao = new BookDAO(getConnection());
        while(rs.next()){
            Author a = new Author();
            a.setAuthorId(rs.getInt("authorId"));
            a.setAuthorName(rs.getString("authorName"));
            try {
                a.setBooks((List<Book>) bdao.readFirstLevel("select * from tbl_book where bookId IN (select bookId from tbl_book_authors where authorId = ?)", new Object[] {a.getAuthorId()}));

            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            authors.add(a);

        }
        return authors;
    }

    @Override
    public List<Author> extractDataFirstLevel(ResultSet rs) throws SQLException {
        List<Author> authors = new ArrayList<Author>();
        while(rs.next()){
            Author a = new Author();
            a.setAuthorId(rs.getInt("authorId"));
            a.setAuthorName(rs.getString("authorName"));

            authors.add(a);
        }
        return authors;
    }



}


