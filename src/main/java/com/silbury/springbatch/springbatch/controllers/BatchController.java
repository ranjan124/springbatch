package com.silbury.springbatch.springbatch.controllers;

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
    @RequestMapping("helloBatch")
    public ResponseEntity<String> helloBatch() {
        return new ResponseEntity<>("Hello Batch", HttpStatus.OK);
    }
}
