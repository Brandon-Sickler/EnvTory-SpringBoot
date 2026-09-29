package com.envtory.backend_api.controller;

import com.envtory.backend_api.model.LumberBundle;
import com.envtory.backend_api.repository.LumberBundleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;
import java.util.UUID;
import com.envtory.backend_api.model.Lumber;
import com.envtory.backend_api.repository.LumberRepository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/bundles")
public class LumberBundleController {

    @Autowired
    private LumberBundleRepository repository;

    @Autowired
    private LumberRepository lumberRepository;

    @GetMapping
    public List<LumberBundle> getAllBundles() {
        return repository.findAll();
    }

    @GetMapping("/{bundleId}")
    public LumberBundle getBundle(@PathVariable UUID bundleId) {
        return repository.findById(bundleId)
                .orElseThrow(() -> new RuntimeException("Bundle not found with ID: " + bundleId));
    }

    @PostMapping
    public LumberBundle createBundle(@RequestBody LumberBundle newBundle) {
        return repository.save(newBundle);
    }

    //optimized o(1)
    @PostMapping("/{bundleId}/boards")
    public Lumber addBoardToBundleOptimized(@PathVariable UUID bundleId, @RequestBody Lumber newBoard) {

        //search for the bundle
        Optional<LumberBundle> optionalBundle = repository.findById(bundleId);

        if (optionalBundle.isPresent()) {
            LumberBundle existingBundle = optionalBundle.get();

            //tag the new board with the bundles identity
            newBoard.setBundle(existingBundle);

            //save the board directly to the database
            return lumberRepository.save(newBoard);
        } else {
            throw new RuntimeException("Bundle not found with ID: " + bundleId);
        }
    }

    //The undo endpoint to delete a specific board
    @DeleteMapping("/{bundleId}/boards/{boardId}")
    public String deleteBoard(@PathVariable UUID bundleId, @PathVariable UUID boardId) {

        //check if the board actually exsists before trying to delete it
        if (lumberRepository.existsById(boardId)) {
            lumberRepository.deleteById(boardId);
            return "Board " + boardId + " was successfully deleted from the bundle.";
        } else {
            throw new RuntimeException("Board not found with ID: " + boardId);
        }
    }

    //the update endpoint to update a specific board
    @PutMapping("/{bundleId}/boards/{boardId}")
    public Lumber updateBoard(@PathVariable UUID bundleId, @PathVariable UUID boardId, @RequestBody Lumber updateBoardData) {
        Optional<Lumber> optionalBoard = lumberRepository.findById(boardId);

        if (optionalBoard.isPresent()) {
            Lumber existingBoard = optionalBoard.get();

            //overwrite the old data with the new incoming data
            existingBoard.setSpecies(updateBoardData.getSpecies());
            existingBoard.setLength(updateBoardData.getLength());
            existingBoard.setWidth(updateBoardData.getWidth());
            existingBoard.setThickness(updateBoardData.getThickness());
            existingBoard.setGrade(updateBoardData.getGrade());

            return lumberRepository.save(existingBoard);
        } else {
            throw new RuntimeException("Board not found with ID: " + boardId);
        }
    }
}
