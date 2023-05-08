/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entities;

import java.util.Date;

/**
 *
 * @author Sondes
 */
public class Colaborationevent {
    private int idevent;
    private String nomevent;
    private Date dateevent;
    private String adresseevent;
    private int nbrplacevehicule;
    private int prixvehiculeevent;
   
    public int getIdevent() {
        return idevent;
    }

    public void setIdevent(int idevent) {
        this.idevent = idevent;
    }

  

    public Colaborationevent() {
    }

    public Colaborationevent(int idevent, String nomevent, Date dateevent, String adresseevent, int nbrplacevehicule, int prixvehiculeevent) {
        this.idevent = idevent;
        this.nomevent = nomevent;
        this.dateevent = dateevent;
        this.adresseevent = adresseevent;
        this.nbrplacevehicule = nbrplacevehicule;
        this.prixvehiculeevent = prixvehiculeevent;
    }

    public Colaborationevent(String nomevent, Date dateevent, String adresseevent, int nbrplacevehicule, int prixvehiculeevent) {
        this.nomevent = nomevent;
        this.dateevent = dateevent;
        this.adresseevent = adresseevent;
        this.nbrplacevehicule = nbrplacevehicule;
        this.prixvehiculeevent = prixvehiculeevent;
    }

    @Override
    public String toString() {
        return "Colaborationevent{" + "idevent=" + idevent + ", nomevent=" + nomevent + ", dateevent=" + dateevent + ", adresseevent=" + adresseevent + ", nbrplacevehicule=" + nbrplacevehicule + ", prixvehiculeevent=" + prixvehiculeevent + '}';
    }
    

    public String getNomevent() {
        return nomevent;
    }

    public void setNomevent(String nomevent) {
        this.nomevent = nomevent;
    }

    public Date getDateevent() {
        return dateevent;
    }

    public void setDateevent(Date dateevent) {
        this.dateevent = dateevent;
    }

    public String getAdresseevent() {
        return adresseevent;
    }

    public void setAdresseevent(String adresseevent) {
        this.adresseevent = adresseevent;
    }

    public int getNbrplacevehicule() {
        return nbrplacevehicule;
    }

    public void setNbrplacevehicule(int nbrplacevehicule) {
        this.nbrplacevehicule = nbrplacevehicule;
    }

    public int getPrixvehiculeevent() {
        return prixvehiculeevent;
    }

    public void setPrixvehiculeevent(int prixvehiculeevent) {
        this.prixvehiculeevent = prixvehiculeevent;
    }

   
}
