package com.silbury.springbatch.springbatch.batch;

import com.silbury.springbatch.springbatch.models.Person;
import com.silbury.springbatch.springbatch.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Item Reader
 * <p>
 * Created by trjena on 02.07.17.
 */
@Component
public class PersonItemReader implements ItemReader<Person> {
    private Logger logger = LoggerFactory.getLogger(PersonItemReader.class);

    private final PersonRepository repository;
    private int count;

    @Autowired
    public PersonItemReader(PersonRepository repository) {
        this.repository = repository;
    }

    @Override
    public Person read() throws Exception {
        List<Person> persons = repository.findAll();
        if (count < persons.size()) {
            Person person = persons.get(count);
            count++;
            return person;
        } else {
            return null;
        }
    }
}
