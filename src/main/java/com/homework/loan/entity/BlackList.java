package com.homework.loan.entity;

import javax.persistence.*;
import java.util.Objects;

@Table
@Entity(name = "blacklist")
public class BlackList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name="person_id", unique = true)
    private Person person;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlackList blackList = (BlackList) o;
        return id == blackList.id &&
                Objects.equals(person, blackList.person);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, person);
    }

}
