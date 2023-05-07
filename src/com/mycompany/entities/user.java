/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author mohamed
 */
public class user {
    private int id;
    private String nom;
        private String prenom;
            private String cin;
                        private String role;

            

            private String email;
                private String passowrd;

    public user(String nom, String prenom, String cin, String email, String passowrd,String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.email = email;
        this.passowrd = passowrd;
        this.role=role ; 
    }

    public user(int id, String nom, String prenom, String cin, String email, String passowrd,String role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.email = email;
        this.passowrd = passowrd;
        this.role=role ; 
    }

    public user() {
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getCin() {
        return cin;
    }

    public String getEmail() {
        return email;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "user{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", role=" + role + ", email=" + email + ", passowrd=" + passowrd + '}';
    }
    

    
    
    



   
    
}
