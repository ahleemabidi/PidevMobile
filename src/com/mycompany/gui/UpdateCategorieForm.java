/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Categorie;
import com.company.services.ServiceCategorie;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class UpdateCategorieForm extends Form{
       public UpdateCategorieForm(Categorie c, Form previous) {
          setTitle("Update a categorie");
        setLayout(BoxLayout.y());
    ServiceCategorie service = ServiceCategorie.getInstance();
    ArrayList<Categorie> categories = service.getAllCategorie();


    for (Categorie categorie : categories) {
     String labelText = "idCategorie: " + categorie.getId_categorie()+"Type: " + categorie.getType() + ", Matricule: " + categorie.getMatricule() + ", Marque: " + categorie.getMarque();
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
    

    final Categorie currentCategorie = categorie; // Variable locale finale pour stocker la référence à la catégorie

    // Gestionnaire d'événements pour le clic sur le bouton "Update"
    updateButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            // Créer le formulaire de mise à jour de la catégorie
            Form updateForm = new Form("Update Category", BoxLayout.y());

            TextField type = new TextField(currentCategorie.getType(), "Type");
            TextField matricule = new TextField(currentCategorie.getMatricule(), "Matricule");
            TextField marque = new TextField(currentCategorie.getMarque(), "Marque");

            Button btnSave = new Button("Save");
            btnSave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    if (type.getText().isEmpty() || matricule.getText().isEmpty() || marque.getText().isEmpty()) {
                        Dialog.show("Alert", "Please fill all the fields", new Command("OK"));     
                } else if  (!type.getText().equals("moyenne") && !type.getText().equals("haute")) {
                    Dialog.show("Error", "Invalid Type value. Valid values are 'moyenne' or 'haute'", new Command("OK"));
                } else  {
                    String matriculeT = matricule.getText();

                    if (matriculeT.length() != 10) {
                        Dialog.show("Error", "Invalid Matricule value. Valid format is '123Tun4567'", new Command("OK"));
                    } else {
                        String pays = matriculeT.substring(3, 6);
                        if (!pays.equals("Tun")) {
                            Dialog.show("Error", "Invalid Matricule value. Valid format is '123Tun4567'", new Command("OK"));
                        } else {
                      // Afficher une boîte de dialogue de confirmation
            boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to save the changes?", "Yes", "No");

            if (confirmed) {
                        if (service.updateCategorie(type.getText(), matricule.getText(), marque.getText(), currentCategorie.getId_categorie())) {
                            Dialog.show("Success", "Categorie updated", new Command("OK"));
                                            showUpdatedCategoriesList(previous);
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    }
                }
                    }} }});
               updateForm.addAll(type, matricule, marque, btnSave);
            updateForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
            updateForm.show();
        }
    });
}}
 private void showUpdatedCategoriesList(Form previous) {
    Form updatedListForm = new Form("Updated Categories", BoxLayout.y());
        ServiceCategorie service = ServiceCategorie.getInstance();

    // Charger les catégories mises à jour
    ArrayList<Categorie> updatedCategories = service.getAllCategorie();
    
    // Afficher les catégories dans le formulaire
    for (Categorie updatedCategorie : updatedCategories) {
        // Créer le texte de l'étiquette
        String labelText = "idCategorie: " + updatedCategorie.getId_categorie() + "Type: " + updatedCategorie.getType() + ", Matricule: " + updatedCategorie.getMatricule() + ", Marque: " + updatedCategorie.getMarque();
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




