package com.homework.loan.web.controller;

import com.homework.loan.entity.Loan;
import com.homework.loan.service.Response;
import com.homework.loan.service.blacklist.DefaultBlackListService;
import com.homework.loan.service.country.DefaultCountryService;
import com.homework.loan.service.loan.DefaultLoanService;
import com.homework.loan.web.dto.LoanRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoanRestController {

    @Autowired
    private DefaultCountryService defaultCountryService;

    @Autowired
    private DefaultBlackListService defaultBlackListService;

    @Autowired
    private DefaultLoanService defaultLoanService;

    @PostMapping("/apply")
    public Response listCountries(@RequestBody LoanRequestBody loanRequestBody) {
        return defaultLoanService.apply(loanRequestBody.getLoan(), loanRequestBody.getUser());
    }

    @GetMapping("/listAll")
    public List<Loan> listCountries() {
        return defaultLoanService.getAll();
    }

    @GetMapping("/listAll/{userId}")
    public List<Loan> listCountries(@PathVariable int userId) {
        return defaultLoanService.getByUserId(userId);
    }

}
