package com.example.ecoletstn2023.ressource;

import com.example.ecoletstn2023.dto.EvaluationDto;
import com.example.ecoletstn2023.dto.ImputEtudiantDto;
import com.example.ecoletstn2023.enumeration.ClasseIPI;
import com.example.ecoletstn2023.enumeration.TypeEval;
import com.example.ecoletstn2023.enumeration.TypeMatiere;
import com.example.ecoletstn2023.service.IMatiereService;
import com.example.ecoletstn2023.service.IServiceEtudiant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("Etudiant")
public class EtudiantRessource {


    @Autowired
    private IServiceEtudiant serviceEtudiant;

    @Autowired
    private IMatiereService matiereService;

    @GetMapping("get/{id}")
    public ResponseEntity get(@PathVariable String id)
    {
        Integer ID;
        try {
            ID = Integer.parseInt(id);
        } catch (NumberFormatException e){
            return new ResponseEntity("l'ID n'a pas le bon format", HttpStatus.BAD_REQUEST);
        }

        if (!serviceEtudiant.exist(ID)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(serviceEtudiant.get(ID), HttpStatus.OK);
    }

    @PostMapping("add")
    public ResponseEntity ajouter(@RequestBody ImputEtudiantDto dto){
        // on verifie la date
        LocalDate date;
        try{
            LocalDate.parse(dto.getDate());
        }catch (DateTimeParseException e){
            return new ResponseEntity("la date est incorrect", HttpStatus.BAD_REQUEST);
        }
        // on verifie la classe
        try {
            ClasseIPI.valueOf(dto.getClasse());
        } catch (IllegalArgumentException e){
            return new ResponseEntity("la classe est incorrect", HttpStatus.BAD_REQUEST);
        }

        Integer ID = serviceEtudiant.ajouterEtudiant(dto);

        if (null == ID){
            return new ResponseEntity("l'enregistrement ne c'est pas fait", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(ID, HttpStatus.OK);

    }

    @GetMapping("delete/{id}")
    public ResponseEntity supprimer (@PathVariable String id){
        Integer ID;
        try {
            ID = Integer.parseInt(id);
        } catch (NumberFormatException e){
            return new ResponseEntity("l'ID n'a pas le bon format", HttpStatus.BAD_REQUEST);
        }

        if (!serviceEtudiant.exist(ID)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        boolean result = serviceEtudiant.delete(ID);
        if (!result){
            return new ResponseEntity("l'etudiant na pas été supprimer", HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity(HttpStatus.OK);

    }

    @PostMapping("update/{id}")
    public ResponseEntity update(@PathVariable String id, @RequestBody ImputEtudiantDto dto){

        Integer ID;
        try {
            ID = Integer.parseInt(id);
        } catch (NumberFormatException e){
            return new ResponseEntity("l'ID n'a pas le bon format", HttpStatus.BAD_REQUEST);
        }
        try{
            LocalDate.parse(dto.getDate());
        }catch (DateTimeParseException e){
            return new ResponseEntity("la date est incorrect", HttpStatus.BAD_REQUEST);
        }

        if (!serviceEtudiant.exist(ID)){
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        boolean result = serviceEtudiant.update(ID, dto);
        if (!result){
            return new ResponseEntity("l'etudiant na pas été modifier", HttpStatus.NOT_MODIFIED);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("addEval")
    public ResponseEntity ajouterEval(@RequestParam String id,
                                      @RequestParam String code,
                                      @RequestBody EvaluationDto dto){

        Integer ID;
        try {
            ID = Integer.parseInt(id);
        } catch (NumberFormatException e){
            return new ResponseEntity("l'ID n'a pas le bon format", HttpStatus.BAD_REQUEST);
        }
        if (!serviceEtudiant.exist(ID)){
            return new ResponseEntity("l'étudiant n'existe pas", HttpStatus.BAD_REQUEST);
        }
        if (!matiereService.exist(code))
            return new ResponseEntity("la matiere n'existe pas", HttpStatus.BAD_REQUEST);
        // on verfie que la note est entre 0 et 20
        if (( 0 >  dto.getNote()) || (dto.getNote() > 20)){
            return new ResponseEntity("la note n'a pas le bon format", HttpStatus.BAD_REQUEST);
        }
        try {
            TypeEval.valueOf(dto.getType());
        } catch (IllegalArgumentException e){
            return new ResponseEntity("le type n'a pas le bon format", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity(serviceEtudiant.addEval(ID, code, dto), HttpStatus.OK);
    }

    @GetMapping("avg/{id}")
    public ResponseEntity moyenne(@PathVariable String id){
        Integer ID;
        try {
            ID = Integer.parseInt(id);
        } catch (NumberFormatException e){
            return new ResponseEntity("l'ID n'a pas le bon format", HttpStatus.BAD_REQUEST);
        }
        if (!serviceEtudiant.exist(ID)){
            return new ResponseEntity("l'étudiant n'existe pas", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(serviceEtudiant.toDto(ID), HttpStatus.OK);
    }

}
