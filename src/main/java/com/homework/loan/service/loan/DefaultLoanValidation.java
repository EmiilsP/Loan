package com.homework.loan.service.loan;

import com.homework.loan.entity.Country;
import com.homework.loan.entity.Loan;
import com.homework.loan.entity.Person;
import com.homework.loan.repository.CountryRepository;
import com.homework.loan.repository.PersonRepository;
import com.homework.loan.service.blacklist.BlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultLoanValidation implements LoanValidation {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private BlackListService blackListService;

    private List<String> errors;

    public DefaultLoanValidation() {
        errors = new ArrayList<>();
    }

    public boolean isLoanValid(Loan loan) {
        validateLoan(loan);
        return errors.isEmpty();
    }

    public List<String> getErrors() {
        return errors;
    }

    public void clearErrors() {
        errors = new ArrayList<>();
    }

    private void validateLoan(Loan loan) {
        validatePerson(loan.getPerson().getId());
        validateCountry(loan.getCountry().getId());
    }

    private void validatePerson(long id) {
        Optional<Person> person = personRepository.findById(id);

        if (person.isPresent()) {
            if (blackListService.isInBlackList(id)) {
                errors.add("Person is in black list!");
            }
        } else {
            errors.add("Person not found!");
        }
    }

    private void validateCountry(int id) {
        Optional<Country> country = countryRepository.findById(id);

        if (country.isEmpty()) {
            errors.add("Country not found!");
        }
    }

}
