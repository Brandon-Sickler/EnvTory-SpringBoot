package com.envtory.backend_api.repository;

import com.envtory.backend_api.model.LumberBundle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.List;

@Repository
public interface LumberBundleRepository extends JpaRepository<LumberBundle, UUID> {
    List<LumberBundle> findByLoadIdIsNull();
}

