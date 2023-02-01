package com.example.ecoletstn2023.service;


import com.example.ecoletstn2023.dto.EtudiantDto;
import com.example.ecoletstn2023.dto.EtudiantMoyenneDto;
import com.example.ecoletstn2023.dto.EvaluationDto;
import com.example.ecoletstn2023.dto.ImputEtudiantDto;
import com.example.ecoletstn2023.entity.EtudiantEntity;
import com.example.ecoletstn2023.entity.EvaluationEntity;
import com.example.ecoletstn2023.entity.MatiereEntity;
import com.example.ecoletstn2023.repository.EtudiantRepository;
import com.example.ecoletstn2023.repository.EvalutationRepository;
import com.example.ecoletstn2023.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class EtudiantService implements IServiceEtudiant {

    @Autowired
    private EtudiantRepository repository;

    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    private EvalutationRepository evalutationRepository;

    @Override
    public Integer calculerAge(LocalDate date_naisse) {
        return Period.between(date_naisse, LocalDate.now()).getYears();
    }

    @Override
    public EtudiantDto toDto(EtudiantEntity entity) {
        EtudiantDto dto = new EtudiantDto();
        dto.setDisplay_name(entity.getPrenom() + " " + entity.getNom());
        dto.setAge(calculerAge(entity.getDate_naissance()));
        dto.setClasse(entity.getClasse());
        return dto;
    }

    @Override
    public Integer ajouterEtudiant(ImputEtudiantDto dto) {

        EtudiantEntity entity = new EtudiantEntity();
        entity.setNom(dto.getNom());
        entity.setPrenom(dto.getPrenom());
        entity.setClasse(dto.getClasse());
        LocalDate date = LocalDate.parse(dto.getDate());
        entity.setDate_naissance(date);
        try{
            repository.saveAndFlush(entity);
        } catch (Exception e) {
            return null;
        }
        return entity.getID();

    }

    @Override
    public EtudiantDto get(Integer id) {
        return toDto(repository.findById(id).get());
    }

    @Override
    public Boolean exist(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public Boolean delete(Integer id) {
        try {
            repository.deleteById(id);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean update(Integer id, ImputEtudiantDto dto) {
        EtudiantEntity etudiantEntity = repository.findById(id).get();

        etudiantEntity.setPrenom(dto.getPrenom());
        etudiantEntity.setNom(dto.getNom());
        LocalDate date = LocalDate.parse(dto.getDate());
        etudiantEntity.setDate_naissance(date);
        etudiantEntity.setClasse(dto.getClasse());
        try  {
            repository.saveAndFlush(etudiantEntity);
            return  true;
        } catch (Exception e){
            return false;
        }

    }

    @Override
    public Integer addEval(Integer id, String code, EvaluationDto dto) {
        EvaluationEntity evaluationEntity = new EvaluationEntity();
        evaluationEntity.setNote(dto.getNote());
        evaluationEntity.setType(dto.getType());

        LocalDate date = LocalDate.parse(dto.getDate());
        evaluationEntity.setDate(date);

        EtudiantEntity etudiantEntity = repository.findById(id).get();
        MatiereEntity matiereEntity = matiereRepository.findById(code).get();

        evaluationEntity.setEtudiantEntity(etudiantEntity);
        evaluationEntity.setMatiereEntity(matiereEntity);

        evalutationRepository.saveAndFlush(evaluationEntity);

        return  evaluationEntity.getID();



    }

    @Override
    public EtudiantMoyenneDto toDto(Integer ID) {

        EtudiantEntity entity = repository.findById(ID).get();

        EtudiantMoyenneDto dto = new EtudiantMoyenneDto();

        dto.setDisplay_name(entity.getPrenom() + " " + entity.getNom());

        //calcul de la moyenne
        List<EvaluationEntity> list = entity.getEvaluations();
        float somme =0;

        for (int i = 0; i< list.size(); i++){
            somme = somme + (float) list.get(i).getNote();
        }

        dto.setMoyenne(somme/list.size());
        return dto;
    }
}
