package com.example.ecoletstn2023.service;


import com.example.ecoletstn2023.dto.EtudiantDto;
import com.example.ecoletstn2023.dto.EtudiantMoyenneDto;
import com.example.ecoletstn2023.dto.EvaluationDto;
import com.example.ecoletstn2023.dto.ImputEtudiantDto;
import com.example.ecoletstn2023.entity.EtudiantEntity;

import java.time.LocalDate;

public interface IServiceEtudiant {

    Integer calculerAge(LocalDate date_naisse);
    EtudiantDto toDto(EtudiantEntity entity);

    Integer ajouterEtudiant (ImputEtudiantDto dto);

    EtudiantDto get(Integer id);

    Boolean exist(Integer id);

    Boolean delete(Integer id);

    Boolean update(Integer id, ImputEtudiantDto dto);

    Integer addEval(Integer id, String code, EvaluationDto dto);

    EtudiantMoyenneDto toDto(Integer ID);

}
