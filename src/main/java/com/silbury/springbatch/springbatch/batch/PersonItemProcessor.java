package com.silbury.springbatch.springbatch.batch;

import com.silbury.springbatch.springbatch.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/**
 * Item processor
 * <p>
 * Created by trjena on 02.07.17.
 */
@Component
public class PersonItemProcessor implements ItemProcessor<Person, Person> {
    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Person process(Person person) throws Exception {
        person.setFirstName(person.getFirstName().toUpperCase());
        person.setLastName(person.getLastName().toUpperCase());


        log.info("Converting (" + person + ") into (" + person + ")");

        return person;
    }
}
