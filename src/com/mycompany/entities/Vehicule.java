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
public class Vehicule {
    private int id;
    private int disponibilite;
    private int numEntretien;
    private Date dateEntretien;
    private String resEntretien;

    public Vehicule() {
    }
    

    public Vehicule(int disponibilite, int numEntretien, Date dateEntretien, String resEntretien) {
        this.disponibilite = disponibilite;
        this.numEntretien = numEntretien;
        this.dateEntretien = dateEntretien;
        this.resEntretien = resEntretien;
    }

    public Vehicule(int disponibilite) {
        this.disponibilite = disponibilite;
    }

    public Vehicule(int disponibilite, int numEntretien) {
        this.disponibilite = disponibilite;
        this.numEntretien = numEntretien;
    }

   

  

    public Vehicule(int id, int disponibilite, int numEntretien, Date dateEntretien, String resEntretien) {
        this.id = id;
        this.disponibilite = disponibilite;
        this.numEntretien = numEntretien;
        this.dateEntretien = dateEntretien;
        this.resEntretien = resEntretien;
    }

    public int getId() {
        return id;
    }

    public int getDisponibilite() {
        return disponibilite;
    }

    public int getNum_entretien() {
        return numEntretien;
    }

    public Date getDate_entretien() {
        return dateEntretien;
    }

    public String getRes_entretien() {
        return resEntretien;
    }

    public void setDisponibilite(int disponibilite) {
            this.disponibilite = disponibilite;
      
       
    }

    public void setNum_entretien(int numEntretien) {
        this.numEntretien = numEntretien;
       
       
    }

    public void setDate_entretien(Date dateEntretien) {
         
              this.dateEntretien = dateEntretien;
        
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRes_entretien(String resEntretien) {
               this.resEntretien = resEntretien;
       
       
     
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehicule other = (Vehicule) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "vehicule{" + "disponibilite=" + disponibilite + ", num_entretien=" + numEntretien + ", date_entretien=" + dateEntretien + ", res_entretien=" + resEntretien + '}';
    }
    
    
}
