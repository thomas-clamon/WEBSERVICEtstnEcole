package com.example.ecoletstn2023.ressource;

import com.example.ecoletstn2023.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Matieres")
public class MatiereRessource {

    @Autowired
    private MatiereService matiereService;

    @GetMapping("all")
    public ResponseEntity getAll(){
        return new ResponseEntity(matiereService.getAll2(), HttpStatus.OK);
    }


}
