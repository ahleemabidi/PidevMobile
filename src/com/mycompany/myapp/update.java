/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.entities.user;
import com.company.services.userservice;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class update extends Form{
       public update(user c, Form previous) {
          setTitle("Update a categorie");
        setLayout(BoxLayout.y());
    userservice service = userservice.getInstance();
    ArrayList<user> categories = service.getAllCategorie();


    for (user u : categories) {
     String labelText = "id: " + u.getId()+"email: " + u.getEmail() + ", nom: " + u.getNom() + ", prenom: " + u.getPrenom()+ ", role: " + u.getRole();
        Label label = new Label(labelText);
        // Créer un conteneur avec un BoxLayout horizontal
        Container container = BoxLayout.encloseX(label);

        // Créer un conteneur scrollable avec défilement horizontal
        Container scrollContainer = new Container();
        scrollContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        scrollContainer.setScrollableX(true);
        scrollContainer.addComponent(container);

        // Ajouter le conteneur scrollable dans la Form
        add(scrollContainer);

    // Bouton de mise à jour pour chaque catégorie
    Button updateButton = new Button("Update");
    add(updateButton);
    

    final user currentCategorie = u; // Variable locale finale pour stocker la référence à la catégorie

    // Gestionnaire d'événements pour le clic sur le bouton "Update"
    updateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            // Créer le formulaire de mise à jour de la catégorie
            Form updateForm = new Form("Update user", BoxLayout.y());

            TextField email = new TextField(currentCategorie.getEmail(), "email");
            TextField prenom = new TextField(currentCategorie.getPrenom(), "prenom");
            TextField nom = new TextField(currentCategorie.getNom(), "nom");

            

            Button btnSave = new Button("Save");
            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (email.getText().isEmpty() || prenom.getText().isEmpty() || nom.getText().isEmpty()) {
                        Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                    } else {
                        if (service.updateCategorie(email.getText(), prenom.getText(), nom.getText(), currentCategorie.getId())) {
                            Dialog.show("Success", "Categorie updated", new Command("OK"));
                                            showUpdatedCategoriesList(previous);
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    }
                }
            });
               updateForm.addAll(email, nom, prenom, btnSave);
            updateForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            updateForm.show();
        }
    });
}}
 private void showUpdatedCategoriesList(Form previous) {
    Form updatedListForm = new Form("Updated Categories", BoxLayout.y());
        userservice service = userservice.getInstance();

    // Charger les catégories mises à jour
    ArrayList<user> updatedCategories = service.getAllCategorie();
    
    // Afficher les catégories dans le formulaire
    for (user u : updatedCategories) {
        // Créer le texte de l'étiquette
        String labelText = "id: " + u.getId() + "email: " + u.getEmail() + ", nom: " + u.getNom() + ", prenom: " + u.getPrenom();
        Label label = new Label(labelText);
        // Créer un conteneur avec un BoxLayout horizontal
        Container container = BoxLayout.encloseX(label);

        // Créer un conteneur scrollable avec défilement horizontal
        Container scrollContainer = new Container();
        scrollContainer.setLayout(new BoxLayout(BoxLayout.X_AXIS));
        scrollContainer.setScrollableX(true);
        scrollContainer.addComponent(container);

        // Ajouter le conteneur scrollable dans le formulaire
        updatedListForm.add(scrollContainer);
    }
    
    // Bouton de retour vers le formulaire précédent
    Button backButton = new Button("Back");
    backButton.addActionListener(e -> previous.showBack());
    updatedListForm.add(backButton);
    
    updatedListForm.show();
}}
