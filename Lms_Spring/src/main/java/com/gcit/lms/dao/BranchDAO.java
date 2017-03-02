package com.gcit.lms.dao;
import com.gcit.lms.entity.BookCopies;
import com.gcit.lms.entity.BookLoans;
import com.gcit.lms.entity.Branch;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shash on 2/25/2017.
 */
public class BranchDAO extends BaseDAO{


    public BranchDAO(Connection conn) {
        super(conn);
        // TODO Auto-generated constructor stub
    }

    //INSERT NEW BRANCH
    public void addBranch(Branch branch) throws ClassNotFoundException,SQLException {
        save("insert into tbl_library_branch (branchName,branchAddress) values (?,?)", new Object[] {branch.getBranchName(),branch.getBranchAddress()});
    }

    //UPDATE BRANCH
    public void updateBranch(Branch branch) throws ClassNotFoundException, SQLException{
        save("update tbl_library_branch set branchName =?,branchAddress=? where branchId =? ", new Object[] {branch.getBranchName(),branch.getBranchAddress(),branch.getBranchId()});
    }


    //DELETE BRANCH
    public void deleteBranch(Branch branch) throws ClassNotFoundException, SQLException{
        save("delete from tbl_library_branch where branchId = ?", new Object[] {branch.getBranchId()});
    }

    //READ BRANCH BY NAME
    public List<Branch> readBranchByName(String name) throws ClassNotFoundException, SQLException{
        name="%"+name+"%";
        return (List<Branch>) readAll("select * from tbl_library_branch where branchName like ?", new Object[] {name});
    }


    public List<Branch> readAllBranches(int pageNo) throws ClassNotFoundException, SQLException{
        setPageNo(pageNo);
        return (List<Branch>) readAll("select * from tbl_library_branch",null);
    }

    public Branch readBranchByID(Integer branchId) throws ClassNotFoundException, SQLException{
        List<Branch> branches = (List<Branch>) readAll("select * from tbl_library_branch where branchId =?", new Object[] {branchId});
        if(branches!=null && branches.size()>0){
            return branches.get(0);
        }
        return null;
    }


    public Integer getCount() throws ClassNotFoundException, SQLException{
        return getCount("select count(*) from tbl_library_branch");
    }

    @Override
    public List<Branch> extractData(ResultSet rs) throws SQLException {
        List<Branch> branches = new ArrayList<Branch>();

        while(rs.next()){
            Branch br = new Branch();
            BookCopiesDAO bcdao = new BookCopiesDAO(getConnection());
            BookLoansDAO bldao = new BookLoansDAO(getConnection());
            br.setBranchId(rs.getInt("branchId"));
            br.setBranchName(rs.getString("branchName"));
            br.setBranchAddress(rs.getString("branchAddress"));
            try{
                br.setCopies((List<BookCopies>) bcdao.readFirstLevel("select * from tbl_book_copies where branchId =?",new Object[] {br.getBranchId()}));
                br.setLoans((List<BookLoans>) bldao.readFirstLevel("select * from tbl_book_loans where branchId =?", new Object[] {br.getBranchId()}) );
            }catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            branches.add(br);
        }
        return branches;
    }
    @Override
    public List<?> extractDataFirstLevel(ResultSet rs) throws SQLException {
        List<Branch> branch = new ArrayList<Branch>();

        while(rs.next()){
            Branch br = new Branch();
            br.setBranchId(rs.getInt("branchId"));
            br.setBranchName(rs.getString("branchName"));
            br.setBranchAddress(rs.getString("branchAddress"));
            branch.add(br);
        }
        return branch;
    }







}


