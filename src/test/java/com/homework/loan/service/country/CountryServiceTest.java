package com.homework.loan.service.country;

import com.homework.loan.entity.Country;
import com.homework.loan.repository.CountryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void countriesRetrieved() {
        Country country = createCountry();
        assertTrue(countryService.getCountries().contains(country));
    }

    private Country createCountry() {
        Country country = new Country();
        country.setIsoCode("PL");
        country.setName("Poland");
        return countryRepository.save(country);
    }
}
