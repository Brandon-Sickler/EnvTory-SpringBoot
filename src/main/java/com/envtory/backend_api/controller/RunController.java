package com.envtory.backend_api.controller;

import com.envtory.backend_api.model.Run;
import com.envtory.backend_api.repository.RunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

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

    @PostMapping("/{runId}/grades")
    public ResponseEntity<Void> addGradeToRun (@PathVariable UUID runId, @RequestBody Map<String, Object> payload) {
        runRepository.findById(runId).ifPresent(run -> {
            run.getGradesAllowed().add((String) payload.get("grades_allowed"));
            runRepository.save(run);
        });
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{runId}/species")
    public ResponseEntity<Void> addSpeciesToRun(@PathVariable UUID runId, @RequestBody Map<String, Object> payload) {
        runRepository.findById(runId).ifPresent(run -> {
            run.getSpeciesAllowed().add((String) payload.get("species_allowed"));
            runRepository.save(run);
        });
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{runId}/thicknesses")
    public ResponseEntity<Void> addThicknessToRun(@PathVariable UUID runId, @RequestBody Map<String, Object> payload) {
        runRepository.findById(runId).ifPresent(run -> {
            Number thickness = (Number) payload.get("thicknesses_allowed");
            run.getThicknessesAllowed().add(thickness.doubleValue());
            runRepository.save(run);
        });
        return ResponseEntity.ok().build();
    }
    
}
