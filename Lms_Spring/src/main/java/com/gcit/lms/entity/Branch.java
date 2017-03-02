package com.gcit.lms.entity;

import java.io.Serializable;
import java.util.List;


public class Branch implements Serializable{
    private static final long serialVersionUID = 2669963670055898653L;
    private Integer branchId;

    private String branchName;
    private String branchAddress;
    private List<BookCopies> copies;
    private List<BookLoans> loans;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Branch branch = (Branch) o;

        if (branchId != branch.branchId) return false;
        return branchName != null ? branchName.equals(branch.branchName) : branch.branchName == null;
    }

    @Override
    public int hashCode() {
        int result = branchId;
        result = 31 * result + (branchName != null ? branchName.hashCode() : 0);
        return result;
    }

    public List<BookCopies> getCopies() {
        return copies;
    }

    public void setCopies(List<BookCopies> copies) {
        this.copies = copies;
    }

    public List<BookLoans> getLoans() {
        return loans;
    }

    public void setLoans(List<BookLoans> loans) {
        this.loans = loans;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }
    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchAddress() {
        return branchAddress;
    }

    public void setBranchAddress(String branchAddress) {
        this.branchAddress = branchAddress;
    }




}


