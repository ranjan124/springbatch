package com.silbury.springbatch.springbatch.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.*;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Scheduler Configuration
 * <p>
 * Created by trjena on 02.07.17.
 */
@Component
@EnableScheduling
public class SchedulerConfiguration {
    private Logger logger = LoggerFactory.getLogger(SchedulerConfiguration.class);

    private final JobLauncher launcher;

    private final JobOperator jobOperator;

    private final Job job;

    @Autowired
    public SchedulerConfiguration(JobLauncher launcher, JobOperator jobOperator, Job job) {
        this.launcher = launcher;
        this.jobOperator = jobOperator;
        this.job = job;
    }

    @Scheduled(cron = "0 * * * * *")
    public void scheduler() {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();
        JobExecution execution;
        try {
            // check if the job is already running using job operator
            execution = launcher.run(job, jobParameters);
            // check for the status, can be put in while loop
            // may not be needed there is job completion notification listener also
            logger.info(" Status ", execution.getStatus());
        } catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
            e.printStackTrace();
        }

    }

    public void startJob() {
        scheduler();
    }

    public void stopJob()
    {
        try {
            Set<Long> runningJobs = jobOperator.getRunningExecutions("importUserJob");
            jobOperator.stop(runningJobs.iterator().next());
        } catch (NoSuchJobException e) {
            logger.info("no job found");
        } catch (JobExecutionNotRunningException | NoSuchJobExecutionException e) {
            e.printStackTrace();
        }
    }
}
