package com.homework.loan.service.loan;

import com.homework.loan.entity.Loan;

import java.util.List;

public interface LoanValidation {

    boolean isLoanValid(Loan loan);

    List<String> getErrors();

    void clearErrors();
}
