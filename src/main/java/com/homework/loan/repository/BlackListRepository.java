package com.homework.loan.repository;

import com.homework.loan.entity.BlackList;
import com.homework.loan.entity.Person;
import org.springframework.data.repository.CrudRepository;

public interface BlackListRepository extends CrudRepository<BlackList, Long> {

    BlackList findByPerson(Person person);

}
