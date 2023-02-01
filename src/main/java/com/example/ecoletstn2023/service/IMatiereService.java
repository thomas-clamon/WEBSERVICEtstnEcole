package com.example.ecoletstn2023.service;

import com.example.ecoletstn2023.dto.EtudiantMoyenneDto;
import com.example.ecoletstn2023.dto.MatiereDto;
import com.example.ecoletstn2023.entity.MatiereEntity;

import java.util.List;

public interface IMatiereService {

     MatiereDto toDto(MatiereEntity matiereEntity);

     List<MatiereDto> getAll();

     Boolean exist(String code);


}
