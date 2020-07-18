package com.homework.loan.entity;

import javax.persistence.*;
import java.util.Objects;

@Table
@Entity(name = "loan")
public class Loan {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private double amount;

    @Column
    private String term;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return id == loan.id &&
                Double.compare(loan.amount, amount) == 0 &&
                Objects.equals(term, loan.term) &&
                Objects.equals(person, loan.person) &&
                Objects.equals(country, loan.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, term, person, country);
    }

}
