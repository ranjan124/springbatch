package com.silbury.springbatch.springbatch.controllers;

import com.silbury.springbatch.springbatch.configurations.SchedulerConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Batch Controller
 * <p>
 * Created by trjena on 02.07.17.
 */
@RestController
public class BatchController {

    @Autowired
    private SchedulerConfiguration configuration;

    @RequestMapping("helloBatch")
    public ResponseEntity<String> helloBatch() {
        return new ResponseEntity<>("Hello Batch", HttpStatus.OK);
    }

    @RequestMapping("startBatch")
    public ResponseEntity<?> startBatch()
    {
        configuration.startJob();
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping("stopBatch")
    public ResponseEntity<?> stopBatch()
    {
        configuration.stopJob();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
