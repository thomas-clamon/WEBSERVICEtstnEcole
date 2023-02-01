package com.example.ecoletstn2023.repository;


import com.example.ecoletstn2023.entity.MatiereEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatiereRepository extends JpaRepository<MatiereEntity, String>  {
}
