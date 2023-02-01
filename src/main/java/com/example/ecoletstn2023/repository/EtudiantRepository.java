package com.example.ecoletstn2023.repository;

import com.example.ecoletstn2023.entity.EtudiantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<EtudiantEntity, Integer>  {
}
