/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.entities.Categorie;
import com.company.services.ServiceCategorie;
import java.util.ArrayList;

/**
 *
 * @author asus
 */
public class DeleteCategorieForm extends Form{
        public DeleteCategorieForm(Form previous) {
        setTitle("Delete a categorie");
        setLayout(BoxLayout.y());
     ServiceCategorie service = ServiceCategorie.getInstance();
        ArrayList<Categorie> categories = service.getAllCategorie();
        
        // Afficher les catégories
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
            
            // Bouton de suppression pour chaque catégorie
            Button deleteButton = new Button("Delete");
            add(deleteButton);
            
            // Gestionnaire d'événements pour le clic sur le bouton de suppression
            deleteButton.addActionListener(evt -> {
                 boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to delete this category?", "Yes", "No");
    
    if (confirmed) {
       
        
                // Supprimer la catégorie
                boolean deleted = service.deleteCategorie(categorie.getId());
                
                if (deleted) {
                    // Suppression réussie
                    Dialog.show("Success", "Categorie deleted successfully", "OK", null);
                    
                    // Mettre à jour l'affichage en supprimant le label et le bouton de suppression
                    removeComponent(label);
                    removeComponent(deleteButton);
                } else {
                    // Échec de la suppression
                    Dialog.show("Error", "Failed to delete categorie", "OK", null);
                }}
            });
        }
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

}}
