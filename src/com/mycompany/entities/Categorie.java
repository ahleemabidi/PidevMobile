/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author asus
 */
public class Categorie {
       private int idCategorie;
    private String type;
    private String matricule;
    private String marque;
    
    public Categorie(int idCategorie, String type, String matricule, String marque) {
        this.idCategorie = idCategorie;
        this.type = type;
        this.matricule = matricule;
        this.marque = marque;
    }
    private String image;

    public String getImage() {
        return image;
    }

    public int getId_categorie() {
        return idCategorie;
    }


    public Categorie() {
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Categorie(String type, String matricule, String marque) {
        this.type = type;
        this.matricule = matricule;
        this.marque = marque;
    }

    public void setId_categorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }

  
    public int getId() {
        return idCategorie;
    }

    public String getType() {
        return type;
    }

    public String getMatricule() {
        return matricule;
    }

    public String getMarque() {
        return marque;
    }
    public void setType(String type) {
           this.type = type;
       
       
    }

    public void setMatricule(String matricule) {
             this.matricule = matricule;
      
       
      
    }

    public void setMarque(String marque) {
            this.marque = marque;
      
      
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.idCategorie;
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
        final Categorie other = (Categorie) obj;
        if (this.idCategorie != other.idCategorie) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "Categorie{" + "type=" + type + ", matricule=" + matricule + ", marque=" + marque + '}';
    }
    

}
