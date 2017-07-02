package com.silbury.springbatch.springbatch.controllers;

import com.silbury.springbatch.springbatch.models.Person;
import com.silbury.springbatch.springbatch.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller to add or delete people
 * <p>
 * Created by trjena on 02.07.17.
 */
@RestController
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @RequestMapping(value = "addPerson", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
    public ResponseEntity<?> addPerson(@RequestBody Person person) {
        personRepository.saveAndFlush(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/deletePerson/{personId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> removePerson(@PathVariable Long personId) {
        Person person = personRepository.findOne(personId);
        personRepository.delete(person);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
