package com.silbury.springbatch.springbatch.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Person Model
 * Created by trjena on 02.07.17.
 */
@Entity(name = "PEOPLE")
public class Person implements Serializable{

    private static final long serialVersionUID = 3656944933347366093L;

    @Id
    @Column(name = "PERSON_ID")
    private Long personId;

    @Column(name = "FIRST_NAME")
    private String  firstName;

    @Column(name = "LAST_NAME")
    private String lastName;


    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personId=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
