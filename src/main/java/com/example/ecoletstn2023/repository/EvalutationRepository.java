package com.example.ecoletstn2023.repository;

import com.example.ecoletstn2023.entity.EvaluationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvalutationRepository extends JpaRepository<EvaluationEntity, Integer> {
}
