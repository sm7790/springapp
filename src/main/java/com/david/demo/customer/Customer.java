package com.david.demo.customer;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private Long groupId;

    protected Customer() {}

    public Customer(String firstName, String lastName, int age, Long groupId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.groupId = groupId;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s', age='%s']",
                id, firstName, lastName, String.valueOf(age));
    }

}