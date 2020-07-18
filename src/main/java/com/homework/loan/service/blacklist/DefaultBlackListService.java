package com.homework.loan.service.blacklist;

import com.homework.loan.entity.Person;
import com.homework.loan.repository.BlackListRepository;
import com.homework.loan.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultBlackListService implements BlackListService {

    @Autowired
    private BlackListRepository blackListRepository;

    @Autowired
    private PersonRepository personRepository;

    @Override
    public boolean isInBlackList(long personId) {
        boolean isPersonInBlackList = false;
        Optional<Person> person = personRepository.findById(personId);
        if (person.isPresent()) {
            isPersonInBlackList = blackListRepository.findByPerson(person.get()) != null;
        }
        return isPersonInBlackList;
    }

}
