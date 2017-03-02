package com.gcit.lm.service;

import com.gcit.lms.dao.*;
import com.gcit.lms.entity.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by shash on 2/25/2017.
 */
public class AdministratorService {

    // ADD AUTHOR
    public void createAuthor(Author author) throws ClassNotFoundException,
            SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            AuthorDAO adao = new AuthorDAO(conn);
            adao.addAuthor(author);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.close();
        }
    }

    // ADD BOOK_AUTHOR
    public void createBookAuthor(Book book) throws ClassNotFoundException,
            SQLException {

        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {

            BookDAO adao = new BookDAO(conn);
            book.setBookId(adao.addBookWithID(book));
            for (Author a : book.getAuthors()) {
                adao.addBookAuthor(book, a);
            }
            for (Genre ge : book.getGenres()) {
                adao.addBookGenre(book, ge);
            }
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.close();
        }
    }

    // ADD BORROWER
    public void createBorrower(Borrower borrower)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BorrowerDAO borrdao = new BorrowerDAO(conn);
            borrdao.addBorrower(borrower);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.close();
        }
    }

    // ADD PUBLISHER
    public void createPublisher(Publisher publisher)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            PublisherDAO pdao = new PublisherDAO(conn);
            pdao.addPublisher(publisher);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.close();
        }
    }

    // ADD BRANCH
    public void createBranch(Branch branch) throws ClassNotFoundException,
            SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            System.out.println("inside crate branch");
            BranchDAO brdao = new BranchDAO(conn);
            brdao.addBranch(branch);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.close();
        }

    }

    // UPDATE PUBLISHER
    public void updatePublisher(Publisher publisher)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            PublisherDAO pdao = new PublisherDAO(conn);
            pdao.updatePublisher(publisher);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.close();
        }
    }

    // EDIT BOOKCOPIES
    public void editBookCopies(BookCopies bc) throws ClassNotFoundException,
            SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BookCopiesDAO adao = new BookCopiesDAO(conn);
            adao.updateBookCopies(bc);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.close();
        }
        return;
    }
    //DISPLAY BOOK AND COPIES
    public List<BookCopies> displayBookCopies(BookCopies bc) {
        ConnectionUtil c = new ConnectionUtil();


        try {
            Connection conn = c.getConnection();
            BookCopiesDAO adao = new BookCopiesDAO(conn);
            return adao.displayCopies(bc);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    // UPDATE BORROWER
    public void updateBorrower(Borrower borrower)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BorrowerDAO borrdao = new BorrowerDAO(conn);
            borrdao.updateBorrower(borrower);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.close();
        }
    }

    // UPDATE BRANCH
    public void updateBranch(Branch branch) throws ClassNotFoundException,
            SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BranchDAO brdao = new BranchDAO(conn);
            brdao.updateBranch(branch);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.close();
        }

    }

    // UPDATE AUTHOR
    public void updateAuthor(Author author) throws ClassNotFoundException,
            SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            AuthorDAO atdao = new AuthorDAO(conn);
            atdao.updateAuthor(author);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.close();
        }

    }

    // UPDATE BOOK
    public void updateBook(Book book) throws ClassNotFoundException,
            SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BookDAO bdao = new BookDAO(conn);
            bdao.updateBook(book);
            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.close();
        }
    }

    // LIST ALL AUTHORS
    public List<Author> getAllAuthors(int pageNo)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            AuthorDAO adao = new AuthorDAO(conn);
            return adao.readAllAuthors(pageNo);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // LIST ALL AUTHORS BY NAME
    public List<Author> getAllAuthorsByName(String searchString)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            AuthorDAO adao = new AuthorDAO(conn);
            return adao.readAuthorsByName(searchString);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // LIST ALL BOOKS BY NAME
    public List<Book> getAllBooksByName(String searchString, Integer pageNo)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BookDAO bdao = new BookDAO(conn);
            return bdao.readBooksByName(searchString, pageNo);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // LIST ALL AUTHORS BY AUTHOR NAME
    public List<Author> getAllAuthorsByAuthorName(String searchString,
                                                  Integer pageNo) throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            AuthorDAO adao = new AuthorDAO(conn);
            return adao.readAuthorsByAuthorName(searchString, pageNo);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // LIST ALL AUTHORS BY AUTHOR NAME
    public List<Author> getAllAuthorsByTitle(String searchString, Integer pageNo)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            AuthorDAO adao = new AuthorDAO(conn);
            return adao.readAuthorsByBookTitle(searchString, pageNo);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // LIST ALL AUTHORS BY AUTHOR NAME
    public List<Author> getAllAuthorsByAuthorOrTitle(String searchString,
                                                     Integer pageNo) throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            AuthorDAO adao = new AuthorDAO(conn);
            return adao.readAuthorsByBookTitleorName(searchString, pageNo);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // LIST ALL BOOKS BY AUTHOR NAME
    public List<Book> getAllBooksByAuthor(String searchString, Integer pageNo)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BookDAO bdao = new BookDAO(conn);
            return bdao.readBooksByAuthor(searchString, pageNo);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // LIST ALL BOOKS BY AUTHOR NAME
    public List<Book> getAllBooksByAuthorOrTitle(String searchString,
                                                 Integer pageNo) throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BookDAO bdao = new BookDAO(conn);
            return bdao.readBooksByAuthorOrTitle(searchString, pageNo);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // LIST ALL BRANCHES BY NAME
    public List<Branch> getAllBranchByName(String searchString)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BranchDAO brdao = new BranchDAO(conn);
            return brdao.readBranchByName(searchString);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    public List<Borrower> getAllBorrowersByName(String searchString,
                                                Integer pageNo) throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BorrowerDAO brdao = new BorrowerDAO(conn);
            return brdao.readBorrowerByName(searchString, pageNo);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    public List<Publisher> getAllPublishersByName(String searchString,
                                                  Integer pageNo) throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            PublisherDAO pdao = new PublisherDAO(conn);
            return pdao.readPublishersByName(searchString, pageNo);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // GET BOOK COPIES NUMBER FROM PARTICULAR BRANCH
    public List<BookCopies> getBookCopiesByBranchID(Integer branchId)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BookCopiesDAO bdao = new BookCopiesDAO(conn);
            return bdao.readBookCopiesByBranchID(branchId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }

    // GET BOOKS FROM PARTICULAR BRANCH
    public List<Book> getBooksWithBranch(Integer branchId)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BookDAO bdao = new BookDAO(conn);
            return bdao.readBookByBranchID(branchId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }

    // READ BORROWER BOOKS
    public List<Book> getAllBorrowersByID(Integer cardNo)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        System.out.println("In the getAllBorrowerByID method");
        try {
            BookLoansDAO bldao = new BookLoansDAO(conn);
            return bldao.readBookBycardNo(cardNo);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
        return null;
    }

    // GET PUBLISHERS
    public List<Publisher> getAllPublishers(int pageNo)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            PublisherDAO pdao = new PublisherDAO(conn);
            return pdao.readAllPublishers(pageNo);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;

    }

    // GET BORROWERS
    public List<Borrower> getAllBorrowers(int pageNo)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BorrowerDAO pdao = new BorrowerDAO(conn);
            return pdao.readAllBorrowers(pageNo);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;

    }

    // GET ALL BRANCHES
    public List<Branch> getAllBranches(int pageNo)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BranchDAO pdao = new BranchDAO(conn);
            return pdao.readAllBranches(pageNo);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // GET ALL BOOKS
    public List<Book> getAllBooks(int pageNo) throws ClassNotFoundException,
            SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BookDAO bdao = new BookDAO(conn);
            return bdao.readAllBooks(pageNo);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // GET AUTHOR COUNT
    public Integer getAuthorCount() throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            AuthorDAO adao = new AuthorDAO(conn);
            return adao.getCount();
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // GET BOOK COUNT
    public Integer getBookCount() throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BookDAO bdao = new BookDAO(conn);
            return bdao.getCount();
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // GET BOOK COUNT
    public Integer getBorrowerCount() throws ClassNotFoundException,
            SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BorrowerDAO borrdao = new BorrowerDAO(conn);
            return borrdao.getCount();
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // GET BRANCH COUNT
    public Integer getBranchCount() throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BranchDAO brdao = new BranchDAO(conn);
            int count = brdao.getCount();
            return brdao.getCount();
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // GET BOOK COUNT
    public Integer getPublisherCount() throws ClassNotFoundException,
            SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            PublisherDAO pdao = new PublisherDAO(conn);
            return pdao.getCount();
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }




    // GET ALL GENRES
    public List<Genre> getAllGenres() throws ClassNotFoundException,
            SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            GenreDAO gdao = new GenreDAO(conn);
            return gdao.readAllGenre();
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // GET AUTHOR BY ID
    public Author getAuthorByID(Integer authorId)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            AuthorDAO adao = new AuthorDAO(conn);
            return adao.readAuthorsByID(authorId);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // GET BOOK BY ID
    public Book getBookByID(Integer bookId) throws ClassNotFoundException,
            SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BookDAO adao = new BookDAO(conn);
            return adao.readBookByID(bookId);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // GET BORROWER BY ID
    public Borrower getBorrowerByID(Integer cardNo)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BorrowerDAO borrdao = new BorrowerDAO(conn);
            return borrdao.readBorrowerByID(cardNo);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // GET PUBLISHER BY ID
    public Publisher getPublisherByID(Integer publisherId)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            PublisherDAO pdao = new PublisherDAO(conn);
            return pdao.readPublisherByID(publisherId);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // GET BRANCH BY ID
    public Branch getBranchByID(Integer branchId)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            BranchDAO pdao = new BranchDAO(conn);
            return pdao.readBranchByID(branchId);
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
        return null;
    }

    // DELETE AUTHOR
    public void deleteAuthor(Integer authorId) throws ClassNotFoundException,
            SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();

        try {
            AuthorDAO adao = new AuthorDAO(conn);
            adao.deleteAuthor(authorId);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }

    }

    // DELETE BORROWER
    public void deleteBorrower(Borrower cardNo) throws ClassNotFoundException,
            SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();

        try {
            BorrowerDAO borrdao = new BorrowerDAO(conn);
            borrdao.deleteBorrower(cardNo);
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }

    }

    // DELETE PUBLISHER
    public void deletePublisher(Publisher publisherId)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();

        try {
            PublisherDAO pdao = new PublisherDAO(conn);
            pdao.deletePublisher(publisherId);

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }

    }

    // DELETE BRANCH
    public void deleteBranch(Branch branchId) throws ClassNotFoundException,
            SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();

        try {
            BranchDAO brdao = new BranchDAO(conn);
            brdao.deleteBranch(branchId);
            ;

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }

    }

    // DELETE BOOK
    public void deleteBook(Book bookId) throws ClassNotFoundException,
            SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();

        try {
            BookDAO brdao = new BookDAO(conn);
            brdao.deleteBook(bookId);
            ;

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            // conn.rollback();
        } finally {
            conn.close();
        }
    }

    // VERIFY CARD DETAILS
    public boolean verifyCard(int cardN) throws ClassNotFoundException,
            SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        boolean present = false;
        try {
            BorrowerDAO bdao = new BorrowerDAO(conn);
            if (bdao.readBorrowerByID(cardN) != null)
                present = true;

        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.close();
        }

        return present;
    }

    // BOOK CHECKIN
    public boolean bookCheckIn(Integer bookId, Integer cardNo)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        try {
            System.out.println("inside service book checkin");
            BookLoansDAO brdao = new BookLoansDAO(conn);
            System.out.println(bookId);
            System.out.println(cardNo);
            brdao.checkIn(bookId, cardNo);

            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.close();
        }
        return false;
    }

    // BOOK CHECK OUT
    public void bookCheckOut(int bookId, int cardNo, int branchId)
            throws ClassNotFoundException, SQLException {
        ConnectionUtil c = new ConnectionUtil();
        Connection conn = c.getConnection();
        System.out.println(bookId + "incheckin");
        System.out.println(cardNo + "incheckin");
        System.out.println(branchId + "incheckin");

        try {
            System.out.println("inside service book checkin");
            BookLoansDAO brdao = new BookLoansDAO(conn);
            System.out.println(bookId);
            System.out.println(cardNo);
            brdao.checkOut(bookId, cardNo, branchId);

            conn.commit();
        } catch (Exception e) {
            conn.rollback();
        } finally {
            conn.close();
        }

    }

}


