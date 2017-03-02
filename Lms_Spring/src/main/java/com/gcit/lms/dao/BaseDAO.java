package com.gcit.lms.dao;

import java.sql.*;
import java.util.List;





public abstract class BaseDAO {

    private Connection conn;

    public BaseDAO(Connection conn) {
        this.conn = conn;
    }

    public Connection getConnection(){
        return conn;
    }

    private Integer pageNo;

    private Integer pageSize = 10;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void save(String query, Object[] vals) throws ClassNotFoundException, SQLException{
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        int count = 1;
        if(vals!=null){
            for(Object o: vals){

                pstmt.setObject(count, o);
                count++;

            }
        }
        pstmt.executeUpdate();
    }


    public Integer saveWithID(String query, Object[] vals) throws ClassNotFoundException, SQLException{
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        int count = 1;

        if(vals!=null){
            for(Object o: vals){
                pstmt.setObject(count, o);
                count++;
            }
        }
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();

        if(rs.next()){
            return rs.getInt(1);
        }else{
            return null;
        }
    }

    public Integer getCount2(String query,Object[] vals) throws ClassNotFoundException, SQLException{
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        int count=1;
        int b_id=0;

        if(vals!=null){
            for(Object o: vals){
                pstmt.setObject(count, o);
                count++;
            }
        }
        ResultSet rs = pstmt.executeQuery();
        while(rs.next()){

            b_id=rs.getInt("branchId");
        }
        return b_id;

    }

    public Integer getCount(String query) throws ClassNotFoundException, SQLException{
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();

        if(rs.next()){
            return rs.getInt(1);
        }else{
            return 0;
        }
    }

    public List<?> readAll(String query, Object[] vals) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        if (pageNo != null && pageNo >0) {
            query+=" LIMIT "+(pageNo - 1)*10+", 10";
        }
        PreparedStatement pstmt = conn.prepareStatement(query);
        int count = 1;

        if (vals != null) {
            for (Object o : vals) {
                pstmt.setObject(count, o);
                count++;
            }
        }

        ResultSet rs = pstmt.executeQuery();

        return extractData(rs);
    }


    public abstract List<?> extractData(ResultSet rs) throws SQLException;

    public List<?> readFirstLevel(String query, Object[] vals) throws SQLException, ClassNotFoundException{
        Connection conn = getConnection();
        PreparedStatement pstmt = conn.prepareStatement(query);
        int count = 1;

        if(vals!=null){
            for(Object o: vals){
                pstmt.setObject(count, o);
                count++;
            }
        }
        ResultSet rs = pstmt.executeQuery();

        return extractDataFirstLevel(rs);
    }




    public abstract List<?> extractDataFirstLevel(ResultSet rs) throws SQLException;
}
