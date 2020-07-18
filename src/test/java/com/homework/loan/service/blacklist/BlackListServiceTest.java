package com.homework.loan.service.blacklist;

import com.homework.loan.entity.BlackList;
import com.homework.loan.entity.Person;
import com.homework.loan.repository.BlackListRepository;
import com.homework.loan.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BlackListServiceTest {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private BlackListRepository blackListRepository;

    @Autowired
    private BlackListService blackListService;

    @Test
    public void blackListPersonFound() {
        Person person = personRepository.save(createPerson("111111-11111"));
        createBlackList(person);
        assertTrue(blackListService.isInBlackList(person.getId()));
    }

    @Test
    public void goodPersonNotFoundInBlackList() {
        Person person = createPerson("222222-22222");
        assertFalse(blackListService.isInBlackList(person.getId()));
    }

    private Person createPerson(String personNumber) {
        Person person = new Person();
        person.setName("Janis");
        person.setSurname("Berzins");
        person.setPersonalNumber(personNumber);
        return personRepository.save(person);
    }

    private BlackList createBlackList(Person person) {
        BlackList blackList = new BlackList();
        blackList.setPerson(person);
        return blackListRepository.save(blackList);
    }
}
