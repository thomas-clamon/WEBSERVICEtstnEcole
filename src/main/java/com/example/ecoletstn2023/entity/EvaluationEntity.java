package com.example.ecoletstn2023.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Evaluations")
public class EvaluationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "note")
    private Integer note;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "type")
    private String type;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_etudiant")
    EtudiantEntity etudiantEntity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_matiere")
    MatiereEntity matiereEntity;

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public EtudiantEntity getEtudiantEntity() {
        return etudiantEntity;
    }

    public void setEtudiantEntity(EtudiantEntity etudiantEntity) {
        this.etudiantEntity = etudiantEntity;
    }

    public MatiereEntity getMatiereEntity() {
        return matiereEntity;
    }

    public void setMatiereEntity(MatiereEntity matiereEntity) {
        this.matiereEntity = matiereEntity;
    }
}
