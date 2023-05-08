/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author faten
 */
public class Formulairer {
    private int id;
    private String nom;
    private int tlp;
    private String mail;
    private int nbr;
    private String type;
    private String categ;
    private String depart;
    private String destination;
    private String option;
    
    
    public Formulairer(int id, String nom, int tlp, String mail, int nbr, String type, String categ, String depart, String destination, String option ) {
        this.id = id;
        this.nom = nom;
        this.tlp = tlp;
        this.mail = mail;
        this.nbr = nbr;
        this.type = type;
        this.categ = categ;
        this.depart = depart;
        this.destination = destination;
        this.option = option;
    }
    private String image;

    public String getImage() {
        return image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    


    public Formulairer() {
    }
    

    public void setImage(String image) {
        this.image = image;
    }

    public Formulairer (String nom, int tlp, String mail,int nbr, String type, String categ, String depart, String destination, String option ) {
        this.nom = nom;
        this.tlp = tlp;
        this.mail = mail;
        this.nbr = nbr;
        this.type = type;
        this.categ = categ;
        this.depart = depart;
        this.destination = destination;
        this.option = option;
    }
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getTlp() {
        return tlp;
    }

    public void setTlp(int tlp) {
        this.tlp = tlp;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

   

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
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
        final Formulairer other = (Formulairer) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "Formulairer{" + "nom=" + nom + ", tlp=" + tlp + ", mail=" + mail +", nbr=" + nbr +", type=" + type +", categorie=" + categ +", depart=" + depart +", destination=" + destination +", option=" + option +'}';
    }
    
    
}
