package com.prashant.domainjpa.controller;

import com.prashant.domainjpa.data.Patent;
import com.prashant.domainjpa.service.PatentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class PatentController {
    private Logger logger = LoggerFactory.getLogger("PatentController");

    PatentService patentService;

    @Autowired
    public PatentController(PatentService svc) {
        this.patentService = svc;
    }

    // Get all patents
    @GetMapping("/patent")
    public ResponseEntity<List<Patent>> getAllPatents() {
        return new ResponseEntity<>(patentService.getAllPatents(), HttpStatus.OK);
    }

    // Get individual record by id
    @GetMapping(path = "/patent/{recordId}")
    public ResponseEntity<Patent> getRecord(@PathVariable(value = "recordId") String recordId) {

        return new ResponseEntity<>(patentService.getPatent(recordId), HttpStatus.OK);
    }

    // Get individual record by id
    @PatchMapping(path = "/patent/{recordId}")
    public ResponseEntity<Patent> getRecord(@PathVariable(value = "recordId") String recordId,
                                            @RequestParam Map<String,String> requestParams) {
        return new ResponseEntity<>(patentService.patchPatent(recordId, requestParams), HttpStatus.OK);
    }

    // Patch a record fields
    @DeleteMapping(path = "/patent/{recordId}")
    public ResponseEntity<String> deleteRecord(@PathVariable(value = "recordId") String recordId) {

        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/patent")
    public ResponseEntity<String> deleteAllRecords() {

        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    // Get report
    @GetMapping("/deadline-report")
    public ResponseEntity<String> fetchReport() {

        return new ResponseEntity<>("Test", HttpStatus.OK);
    }

    @ExceptionHandler(value = { RuntimeException.class })
    public ResponseEntity<String> runtimeExceptionHandler( RuntimeException rex) {
        return new ResponseEntity<>(rex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
