package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.List;

public class Borrower implements Serializable{


    private int cardNo;
    private String name;
    private String Address;
    private String phone;
    private List<BookLoans> Bloans;

    public int getCardNo() {
        return cardNo;
    }

    public void setCardNo(int cardNo) {
        this.cardNo = cardNo;
    }

    public List<BookLoans> getBloans() {
        return Bloans;
    }

    public void setBloans(List<BookLoans> bloans) {
        Bloans = bloans;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Borrower borrower = (Borrower) o;

        if (cardNo != borrower.cardNo) return false;
        return name != null ? name.equals(borrower.name) : borrower.name == null;
    }

    @Override
    public int hashCode() {
        int result = cardNo;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }





}
