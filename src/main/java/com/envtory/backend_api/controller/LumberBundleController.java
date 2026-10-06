package com.envtory.backend_api.controller;

import com.envtory.backend_api.model.LumberBundle;
import com.envtory.backend_api.repository.LumberBundleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.Map;

@RestController
@RequestMapping("/api/bundles")
public class LumberBundleController {

    @Autowired
    private LumberBundleRepository bundleRepository;

    @PostMapping 
    public ResponseEntity<LumberBundle> createBundle(@RequestBody LumberBundle bundle) {
        LumberBundle savedBundle = bundleRepository.save(bundle);
        return ResponseEntity.ok(savedBundle);
    } 

    @GetMapping("/unassigned")
    public ResponseEntity<List<LumberBundle>> getUnassignedBundles() {
        List<LumberBundle> unassigned = bundleRepository.findByLoadIdIsNull();
        return ResponseEntity.ok(unassigned);
    }

    @PutMapping("/{bundleId}/assign")
    public ResponseEntity<LumberBundle> assignToLoad(@PathVariable UUID bundleId, @RequestParam UUID loadId) {
        Optional<LumberBundle> bundleOpt = bundleRepository.findById(bundleId);

        if (bundleOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        LumberBundle bundle = bundleOpt.get();
        bundle.setLoadId(loadId);

        LumberBundle updatedBundle = bundleRepository.save(bundle);
        return ResponseEntity.ok(updatedBundle);
    }

    @PostMapping("/{bundleId}/lengths")
    public ResponseEntity<Void> addLengthToBundle(@PathVariable UUID bundleId, @RequestBody Map<String, Object> payload) {
    bundleRepository.findById(bundleId).ifPresent(bundle -> {
        Number length = (Number) payload.get("lengths_included");
        bundle.getLengthsIncluded().add(length.doubleValue());
        bundleRepository.save(bundle);
    });
    return ResponseEntity.ok().build();
}

}
