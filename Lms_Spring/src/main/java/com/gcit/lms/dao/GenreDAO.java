package com.gcit.lms.dao;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Genre;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shash on 2/25/2017.
 */
public class GenreDAO extends BaseDAO{

    public GenreDAO(Connection conn) {
        super(conn);
        // TODO Auto-generated constructor stub
    }

    //ADD GENRE
    public void addGenre(Genre genre) throws ClassNotFoundException, SQLException {
        save("insert into tbl_genre (genre_name) values (?)", new Object[] {genre.getGenreName()});
    }

    //UPDATE GENRE
    public void updateGenre(Genre genre) throws ClassNotFoundException, SQLException{
        save("update tbl_genre set genre_name = ? where genre_id = ?", new Object[] {genre.getGenreName(),genre.getGenreId()});
    }

    //DELETE GENRE
    public void deleteGenre(Genre genre) throws ClassNotFoundException, SQLException{
        save("delete from tbl_genre where genre_id = ?", new Object[] {genre.getGenreId()});
    }


    public List<Genre> readAllGenre() throws ClassNotFoundException, SQLException{
        return (List<Genre>) readAll("select * from tbl_genre", null);
    }



    @Override
    public List<?> extractData(ResultSet rs) throws SQLException {
        List<Genre> genres = new ArrayList<Genre>();
        BookDAO bdao = new BookDAO(getConnection());

        while(rs.next()){
            Genre genr = new Genre();
            genr.setGenreId(rs.getInt("genre_id"));
            genr.setGenreName(rs.getString("genre_name"));
            try{
                genr.setBooks((List<Book>) bdao.readFirstLevel("select * from tbl_book where bookId IN (select bookId from tbl_book_genres where genre_id =?)", new Object[] {genr.getGenreId()}));
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            genres.add(genr);
        }


        return genres;
    }




    @Override
    public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
        List<Genre> genre = new ArrayList<Genre>();
        while(rs.next()){
            Genre gen = new Genre();
            gen.setGenreId(rs.getInt("genre_id"));
            gen.setGenreName(rs.getString("genre_name"));
            genre.add(gen);
        }

        return genre;
    }



}



