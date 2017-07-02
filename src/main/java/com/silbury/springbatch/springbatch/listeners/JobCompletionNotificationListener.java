package com.silbury.springbatch.springbatch.listeners;

import com.silbury.springbatch.springbatch.models.Person;
import com.silbury.springbatch.springbatch.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Job completion listeners
 *
 * Created by trjena on 02.07.17.
 */

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

    private PersonRepository personRepository;

    @Autowired
    public JobCompletionNotificationListener(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("!!! JOB FINISHED! Time to verify the results");

            List<Person> results = personRepository.findAll();

            for (Person person : results) {
                log.info("Found <" + person + "> in the database.");
            }

        }
    }
}