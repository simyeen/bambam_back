package org.backend.bambam_backend.repository;

import org.backend.bambam_backend.entity.Reptile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReptileRepository extends JpaRepository<Reptile, Long> {

    Optional<Reptile> findByReptileId(Long reptileId);
}

