package com.homework.loan.service.loan;

import com.homework.loan.entity.Country;
import com.homework.loan.entity.Loan;
import com.homework.loan.entity.Person;
import com.homework.loan.entity.User;
import com.homework.loan.repository.CountryRepository;
import com.homework.loan.repository.LoanRepository;
import com.homework.loan.repository.PersonRepository;
import com.homework.loan.repository.UserRepository;
import com.homework.loan.service.Error;
import com.homework.loan.service.Response;
import com.homework.loan.service.Success;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LoanServiceTest {

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void loanCreated() {
        Response response = loanService.apply(getPreparedLoan(), createUser());
        boolean loanCreated = true;
        try {
            Success success = (Success) response;
            assertEquals(Loan.class, success.getValue().getClass());
        } catch (ClassCastException exc) {
            loanCreated = false;
        }
        assertTrue(loanCreated);
    }

    @Test
    public void loanCreationFailed() {
        Loan loan = getCorruptedPreparedLoan();
        loan.setPerson(new Person());
        loan.setCountry(new Country());
        Response response = loanService.apply(loan, createUser());
        boolean loanNotCreated = true;
        try {
            Error error = (Error) response;
            assertFalse(error.getErrorList().isEmpty());
        } catch (ClassCastException exc) {
            loanNotCreated = false;
        }
        assertTrue(loanNotCreated);
    }

    @Test
    public void loansRetrieved() {
        Response response = loanService.apply(getPreparedLoan(), createUser());
        List<Loan> allLoans = loanService.getAll();
        containsLoan(response, allLoans);
    }

    @Test
    public void loansRetrievedByUser() {
        User user = createUser();
        Response response = loanService.apply(getPreparedLoan(), user);
        List<Loan> allLoans = loanService.getByUserId(user.getId());
        containsLoan(response, allLoans);
    }

    private void containsLoan(Response response, List<Loan> allLoans) {
        boolean containLoan = true;
        try {
            Success success = (Success) response;
            assertTrue(allLoans.contains(success.getValue()));
        } catch (ClassCastException exc) {
            containLoan = false;
        }
        assertTrue(containLoan);
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

    private User createUser() {
        User user = new User();
        user.setUserName("Test");
        return userRepository.save(user);
    }

}
