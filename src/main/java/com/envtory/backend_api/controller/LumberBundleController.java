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

@RestController
@RequestMapping("/api/bundles")
public class LumberBundleController {

    @Autowired
    private LumberBundleRepository repository;

    @GetMapping
    public List<LumberBundle> getAllBundles() {
        return repository.findAll();
    }

    @PostMapping
    public LumberBundle createBundle(@RequestBody LumberBundle newBundle) {
        return repository.save(newBundle);
    }
}
