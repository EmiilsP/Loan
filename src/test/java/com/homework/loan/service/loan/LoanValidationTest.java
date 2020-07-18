package com.homework.loan.service.loan;

import com.homework.loan.entity.Country;
import com.homework.loan.entity.Loan;
import com.homework.loan.entity.Person;
import com.homework.loan.repository.CountryRepository;
import com.homework.loan.repository.PersonRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LoanValidationTest {

    @Autowired
    private LoanValidation loanValidation;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private PersonRepository personRepository;

    @AfterEach
    public void clearErrors() {
        loanValidation.clearErrors();
    }

    @Test
    public void loanIsValid() {
        assertTrue(loanValidation.isLoanValid(getPreparedLoan()));
        assertTrue(loanValidation.getErrors().isEmpty());
    }

    @Test
    public void loanIsNoTValid() {
        assertFalse(loanValidation.isLoanValid(getCorruptedPreparedLoan()));
        assertFalse(loanValidation.getErrors().isEmpty());
    }

    @Test
    public void errorMessagesAreCleared() {
        loanValidation.isLoanValid(getCorruptedPreparedLoan());
        assertFalse(loanValidation.getErrors().isEmpty());
        loanValidation.clearErrors();
        assertTrue(loanValidation.getErrors().isEmpty());
    }

    private Loan getPreparedLoan() {
        Loan loan = new Loan();
        loan.setAmount(10000);
        loan.setTerm("Car leasing");
        loan.setPerson(createPerson());
        loan.setCountry(createCountry());
        return loan;
    }

    private Loan getCorruptedPreparedLoan() {
        Loan loan = new Loan();
        loan.setAmount(10000);
        loan.setTerm("Car leasing");
        loan.setPerson(new Person());
        loan.setCountry(new Country());
        return loan;
    }

    private Person createPerson() {
        Person person = new Person();
        person.setName("Janis");
        person.setSurname("Berzins");
        person.setPersonalNumber("111111-11111");
        return personRepository.save(person);
    }

    private Country createCountry() {
        Country country = new Country();
        country.setIsoCode("PL");
        country.setName("Poland");
        return countryRepository.save(country);
    }
}
