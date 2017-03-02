package com.gcit.lms.dao;

import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Borrower;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shash on 2/25/2017.
 */
public class BorrowerDAO extends BaseDAO{
    public BorrowerDAO(Connection conn) {
        super(conn);
        // TODO Auto-generated constructor stub
    }

    //ADD BORROWER
    public void addBorrower(Borrower borr) throws ClassNotFoundException, SQLException{
        save("insert into tbl_borrower (name,address,phone) values (?,?,?)", new Object[] {borr.getName(),borr.getAddress(),borr.getPhone()});
    }

    //UPDATE BORROWER
    public void updateBorrower(Borrower borr) throws ClassNotFoundException, SQLException{
        save("update tbl_borrower set name = ?,address=?,phone=? where cardNo = ?", new Object[] {borr.getName(), borr.getAddress(),borr.getPhone(),borr.getCardNo()});
    }

    //DELETE BORROWER
    public void deleteBorrower(Borrower borr) throws ClassNotFoundException, SQLException{
        save("delete from tbl_borrower where cardNo = ?", new Object[] {borr.getCardNo()});
    }

    public List<Borrower> readAllBorrowers(int pageNo) throws ClassNotFoundException, SQLException{
        setPageNo(pageNo);
        return (List<Borrower>) readAll("select * from tbl_borrower", null);
    }

    //READ BORROWER BY NAME
    public List<Borrower> readBorrowerByName(String name,int pageNo) throws ClassNotFoundException, SQLException{
        setPageNo(pageNo);
        name="%"+name+"%";
        return (List<Borrower>) readAll("select * from tbl_borrower where name like ?", new Object[] {name});
    }


    //GET COUNT OF NUMBER OF AUTHORS
    public Integer getCount() throws ClassNotFoundException, SQLException{
        return getCount("select count(*) from tbl_borrower");
    }

    public Borrower readBorrowerByID(Integer cardNo) throws ClassNotFoundException, SQLException{
        List<Borrower> borrower = (List<Borrower>) readAll("select * from tbl_borrower where cardNo =?", new Object[] {cardNo});
        if(borrower!=null && borrower.size()>0){
            return borrower.get(0);
        }
        return null;
    }


    @Override
    public List<?> extractData(ResultSet rs) throws SQLException {
        List<Borrower> borrower = new ArrayList<Borrower>();
        BookLoansDAO bldao = new BookLoansDAO(getConnection());
        while(rs.next()){
            Borrower borr = new Borrower();
            borr.setCardNo(rs.getInt("cardNo"));
            borr.setName(rs.getString("name"));;
            borr.setAddress(rs.getString("address"));
            borr.setPhone(rs.getString("phone"));
            try {
                borr.setBloans((List<BookLoans>) bldao.readFirstLevel("select * from tbl_book_loans where cardNo=?", new Object[] {borr.getCardNo()}));
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            borrower.add(borr);
        }


        return borrower;
    }



    @Override
    public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
        List<Borrower> borrower = new ArrayList<Borrower>();

        while(rs.next()){
            Borrower borr = new Borrower();
            borr.setCardNo(rs.getInt("cardNo"));
            borr.setName(rs.getString("name"));
            borr.setAddress(rs.getString("address"));
            borr.setPhone(rs.getString("phone"));
            borrower.add(borr);
        }
        return borrower;
    }

    public int CheckCard(Integer cardNo) throws ClassNotFoundException, SQLException {
        System.out.println("In borrower Dao");
        return getCount2("select case when count(br.cardNo)>0 then 1 else 0 end from tbl_borrower as br where br.cardNo=?",new Object[] {cardNo});

    }





}



