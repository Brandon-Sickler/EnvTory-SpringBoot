package com.envtory.backend_api.controller;

import com.envtory.backend_api.model.Lumber;
import com.envtory.backend_api.model.Run;
import com.envtory.backend_api.repository.LumberRepository;
import com.envtory.backend_api.repository.RunRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/runs")
public class LumberController {

    @Autowired 
    private LumberRepository lumberRepository;

    @Autowired 
    private RunRepository runRepository;


    @PostMapping("/{runId}/boards")
    public ResponseEntity<Lumber> addBoardToRun(@PathVariable UUID runId, @RequestBody Lumber board) {

        //First verify the run exists
        Optional<Run> runOptional = runRepository.findById(runId);
        if (runOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        //Anchor the board to the active run
        board.setRun(runOptional.get());

        Lumber savedBoard = lumberRepository.save(board);

        return ResponseEntity.ok(savedBoard);
    } 
    
}
