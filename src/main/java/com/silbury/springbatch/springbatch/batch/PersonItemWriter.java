package com.silbury.springbatch.springbatch.batch;

import com.silbury.springbatch.springbatch.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Person writer
 *
 * Created by trjena on 02.07.17.
 */
@Component
public class PersonItemWriter implements ItemWriter<Person> {

    private Logger logger = LoggerFactory.getLogger(PersonItemWriter.class);
    @Override
    public void write(List items) throws Exception {
        logger.info("Writer job");
    }
}
