package com.example.ecoletstn2023.service;

import com.example.ecoletstn2023.dto.MatiereDto;
import com.example.ecoletstn2023.entity.MatiereEntity;
import com.example.ecoletstn2023.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MatiereService implements IMatiereService {

    @Autowired
    private MatiereRepository matiereRepository;

    @Override
    public MatiereDto toDto(MatiereEntity matiereEntity) {
        MatiereDto dto = new MatiereDto();
        dto.setCode(matiereEntity.getCode());
        dto.setDescription(matiereEntity.getDescription());
        dto.setType(matiereEntity.getType());
        return dto;
    }

    @Override
    public List<MatiereDto> getAll() {

        List<MatiereDto> result = new ArrayList<>();
        List<MatiereEntity> list = matiereRepository.findAll();
        for (int i = 0; i<list.size(); i++){
            MatiereEntity entity = list.get(i);
            result.add(toDto(entity));
        }
        return result;
    }

    @Override
    public Boolean exist(String code) {
        return matiereRepository.existsById(code);

    }

    public List<MatiereDto> getAll2(){
        return matiereRepository.findAll().stream().map(matiereEntity -> toDto(matiereEntity) ).toList();
    }
}
