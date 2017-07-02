package com.silbury.springbatch.springbatch.configurations;

import com.silbury.springbatch.springbatch.batch.PersonItemProcessor;
import com.silbury.springbatch.springbatch.batch.PersonItemReader;
import com.silbury.springbatch.springbatch.batch.PersonItemWriter;
import com.silbury.springbatch.springbatch.listeners.JobCompletionNotificationListener;
import com.silbury.springbatch.springbatch.models.Person;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Batch Configuration
 * Created by trjena on 02.07.17.
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {


    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final PersonItemProcessor itemProcessor;

    private final PersonItemReader itemReader;

    private final PersonItemWriter itemWriter;

    @Autowired
    public BatchConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, PersonItemProcessor itemProcessor, PersonItemReader itemReader, PersonItemWriter itemWriter) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.itemProcessor = itemProcessor;
        this.itemReader = itemReader;
        this.itemWriter = itemWriter;
    }

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Person, Person> chunk(10)
                .reader(itemReader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
    }
}
