package com.homework.loan.service.loan;

import com.homework.loan.entity.Loan;
import com.homework.loan.entity.User;
import com.homework.loan.service.Response;

import java.util.List;

public interface LoanService {

    Response apply(Loan loan, User user);

    List<Loan> getAll();

    List<Loan> getByUserId(int id);

}
