/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author asus
 */
public class Mission {
     private int idMission;
    private String matricule;
    private String description;
    private Date heureDebut;
    private Date heureFin;

    public Mission(int idMission, String matricule, String description, Date heureDebut, Date heureFin) {
        this.idMission = idMission;
        this.matricule = matricule;
        this.description = description;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public Mission(String matricule, String description, Date heureDebut, Date heureFin) {
        this.matricule = matricule;
        this.description = description;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
    }

    public Mission() {
    }

    

    public int getId_mission() {
        return idMission;
    }


    public String getDescription() {
        return description;
    }

    public Date getHeure_debut() {
        return heureDebut;
    }

    public Date getHeure_fin() {
        return heureFin;
    }

    public void setId_mission(int idMission) {
        this.idMission = idMission;
    }

    

    public void setDescription(String description) {
     
              this.description = description;
      
        
    }

    public void setHeure_debut(Date heureDebut) {
     
            this.heureDebut = heureDebut;

    }

    public void setHeure_fin(Date heureFin) {
         
                  this.heureFin = heureFin;
    }

   
    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    
}
