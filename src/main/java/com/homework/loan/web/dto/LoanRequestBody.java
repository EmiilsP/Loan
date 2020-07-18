package com.homework.loan.web.dto;

import com.homework.loan.entity.Loan;
import com.homework.loan.entity.User;

public class LoanRequestBody {

    private Loan loan;

    private User user;

    public Loan getLoan() {
        return loan;
    }

    public void setLoan(Loan loan) {
        this.loan = loan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
