package com.homework.loan.repository;

import com.homework.loan.entity.Loan;
import com.homework.loan.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoanRepository extends CrudRepository<Loan, Long> {

    List<Loan> findByPerson(Person person);

}
