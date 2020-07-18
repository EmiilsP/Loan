package com.homework.loan.service.loan;

import com.homework.loan.entity.*;
import com.homework.loan.repository.*;
import com.homework.loan.service.Error;
import com.homework.loan.service.Response;
import com.homework.loan.service.Success;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DefaultLoanService implements LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserLoansRepository userLoansRepository;

    @Autowired
    private LoanValidation loanValidation;

    private final List<String> errors = new ArrayList<>();

    @Override
    public Response apply(Loan loan, User user) {
        Response response;
        if (loanValidation.isLoanValid(loan)) {
            response = new Success(loanRepository.save(loan));
            addUserLoans(loan, user);
            addInfoForResponse(loan);
        } else {
            response = new Error(loanValidation.getErrors());
            loanValidation.clearErrors();
        }
        return response;
    }

    @Override
    public List<Loan> getAll() {
        return StreamSupport.stream(loanRepository.findAll().spliterator(), false)
                            .collect(Collectors.toList());
    }

    @Override
    public List<Loan> getByUserId(int id) {
        List<Loan> loans = new ArrayList<>();
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            List<UserLoans> userLoans = userLoansRepository.findByUser(optionalUser.get());
            List<Long> ids = userLoans.stream()
                                      .map(UserLoans::getId)
                                      .collect(Collectors.toList());
            loanRepository.findAllById(ids)
                          .iterator()
                          .forEachRemaining(loans::add);
        }
        return loans;
    }

    private void addUserLoans(Loan loan, User user) {
        UserLoans userLoans = new UserLoans();
        userLoans.setLoan(loan);
        userLoans.setUser(user);
        userLoansRepository.save(userLoans);
    }

    private void addInfoForResponse(Loan loan) {
        addPersonInfoForResponse(loan);
        addCountryInfoForResponse(loan);
    }

    private void addPersonInfoForResponse(Loan loan) {
        Optional<Person> optionalPerson = personRepository.findById(loan.getPerson().getId());
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            Person responsePerson = loan.getPerson();
            responsePerson.setName(person.getName());
            responsePerson.setSurname(person.getSurname());
            responsePerson.setPersonalNumber(person.getPersonalNumber());
        }
    }

    private void addCountryInfoForResponse(Loan loan) {
        Optional<Country> optionalCountry = countryRepository.findById(loan.getCountry().getId());
        if (optionalCountry.isPresent()) {
            Country country = optionalCountry.get();
            Country responseCountry = loan.getCountry();
            responseCountry.setName(country.getName());
            responseCountry.setIsoCode(country.getIsoCode());
        }
    }

}
