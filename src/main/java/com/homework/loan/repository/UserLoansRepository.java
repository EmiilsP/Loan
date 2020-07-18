package com.homework.loan.repository;

import com.homework.loan.entity.User;
import com.homework.loan.entity.UserLoans;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserLoansRepository extends CrudRepository<UserLoans, Long> {

    List<UserLoans> findByUser(User user);

}
