package com.envtory.backend_api.repository;

import com.envtory.backend_api.model.Lumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface LumberRepository extends JpaRepository<Lumber, UUID> {
    
}
