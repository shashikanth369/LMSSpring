package com.gcit.lms.dao;

import com.gcit.lms.entity.Book;
import com.gcit.lms.entity.Publisher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shash on 2/25/2017.
 */
public class PublisherDAO extends BaseDAO{

    public PublisherDAO(Connection conn) {
        super(conn);
        // TODO Auto-generated constructor stub
    }

    public void addPublisher(Publisher publisher) throws ClassNotFoundException, SQLException {
        save("insert into tbl_publisher (publisherName,publisherAddress,publisherPhone) values (?,?,?)", new Object[] {publisher.getPublisherName(),publisher.getPublisherAddress(),publisher.getPublisherPhone()});
    }



    public void updatePublisher(Publisher publisher) throws ClassNotFoundException, SQLException{

        save("update tbl_publisher set publisherName = ?,publisherAddress=?,publisherPhone=? where publisherId = ?", new Object[] {publisher.getPublisherName(), publisher.getPublisherAddress(),publisher.getPublisherPhone(),publisher.getPublisherId()});

    }

    public void deletePublisher(Publisher publisher) throws ClassNotFoundException, SQLException{
        save("delete from tbl_publisher where publisherId = ?", new Object[] {publisher.getPublisherId()});
    }

    public List<Publisher> readAllPublishers(int pageNo) throws ClassNotFoundException, SQLException{
        setPageNo(pageNo);
        return (List<Publisher>) readAll("select * from tbl_publisher", null);
    }

    public Publisher readPublisherByID(Integer publisherId) throws ClassNotFoundException, SQLException {
        List<Publisher> publishers = (List<Publisher>) readAll("select * from tbl_publisher where publisherId=?",new Object[] {publisherId});
        if(publishers!=null && publishers.size()>0){
            return publishers.get(0);
        }
        return null;
    }

    //READ AUTHORS BY NAME
    public List<Publisher> readPublishersByName(String name,int pageNo) throws ClassNotFoundException, SQLException{
        setPageNo(pageNo);
        name="%"+name+"%";
        return (List<Publisher>) readAll("select * from tbl_publisher where publisherName like ?", new Object[] {name});
    }

    //GET COUNT OF NUMBER OF PUBLISHERS
    public Integer getCount() throws ClassNotFoundException, SQLException{
        return getCount("select count(*) from tbl_publisher");
    }

    List<Publisher> publisher =  new ArrayList<Publisher>();
    @Override
    public List<Publisher> extractData(ResultSet rs) throws SQLException {

        BookDAO bdao = new BookDAO(getConnection());

        while(rs.next()){
            Publisher pub = new Publisher();
            pub.setPublisherId(rs.getInt("publisherId"));
            pub.setPublisherName(rs.getString("publisherName"));
            pub.setPublisherAddress(rs.getString("publisherAddress"));
            pub.setPublisherPhone(rs.getString("publisherPhone"));

            try{

                pub.setBooks((List<Book>) bdao.readFirstLevel("select * from tbl_book where pubId = ?", new Object[] {pub.getPublisherId()}));

            }catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            publisher.add(pub);
        }
        return publisher;
    }



    @Override
    public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
        List<Publisher> publisher = new ArrayList<Publisher>();
        while(rs.next()){
            Publisher pub = new Publisher();
            pub.setPublisherId(rs.getInt("publisherId"));
            pub.setPublisherName(rs.getString("publisherName"));
            pub.setPublisherAddress(rs.getString("publisherAddress"));
            pub.setPublisherPhone(rs.getString("publisherPhone"));
            publisher.add(pub);
        }

        return publisher;
    }



}

