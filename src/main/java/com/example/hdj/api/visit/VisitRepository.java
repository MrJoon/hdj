package com.example.hdj.api.visit;

import com.example.hdj.api.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VisitRepository extends JpaRepository<Visit, Long> {
    List<Visit> findByIsUseTrue();

    Optional<Visit> findByVisitIdAndIsUseTrue(long visitId);
}
