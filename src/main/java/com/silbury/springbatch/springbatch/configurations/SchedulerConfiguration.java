package com.silbury.springbatch.springbatch.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Scheduler Configuration
 * <p>
 * Created by trjena on 02.07.17.
 */
@Component
@EnableScheduling
public class SchedulerConfiguration {
    private Logger logger = LoggerFactory.getLogger(SchedulerConfiguration.class);

    @Autowired
    private JobLauncher launcher;

    @Autowired
    private Job job;

    @Scheduled(cron = "*/10 * * * * *")
    public void scheduler() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        JobExecution execution;
        try {
            // check if the job is already running
            execution = launcher.run(job, jobParameters);
            // check for the status, can be put in while loop
            // may not be needed there is job finish listener also
            logger.info(" Status ", execution.getStatus());
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }

    }
}
