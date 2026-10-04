package com.envtory.backend_api.controller;

import com.envtory.backend_api.model.Run;
import com.envtory.backend_api.repository.RunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;


@RestController 
@RequestMapping("/api/runs")
public class RunController {

    @Autowired
    private RunRepository runRepository;

    @PostMapping
    public ResponseEntity<Run> createRun(@RequestBody Run run) {
        run.setStartTime(LocalDateTime.now());
        Run savedRun = runRepository.save(run);
        return new ResponseEntity<>(savedRun, HttpStatus.OK);
    }
    
}
