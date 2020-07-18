package com.homework.loan.entity;

import javax.persistence.*;
import java.util.Objects;

@Table
@Entity(name = "country")
public class Country {

    @Id
    @Column
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String isoCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return id == country.id &&
                Objects.equals(name, country.name) &&
                Objects.equals(isoCode, country.isoCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isoCode);
    }

}
