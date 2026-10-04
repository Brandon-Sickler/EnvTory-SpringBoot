package com.envtory.backend_api.repository;

import com.envtory.backend_api.model.Run;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface RunRepository extends JpaRepository<Run, UUID>{
    
}
