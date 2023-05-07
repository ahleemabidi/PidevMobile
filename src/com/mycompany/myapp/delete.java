/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
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
public class delete extends Form {

    public delete(Form previous) {
//        super("Delete a user",new BorderLayout());
//        Container list= new Container(BoxLayout.y());
//        list.setScrollableY(true);
        setTitle("Delete a user");
        setLayout(BoxLayout.y());
        userservice service = userservice.getInstance();
        ArrayList<user> users = service.getAllCategorie();

        // Afficher les catégories
        for (user u : users) {
            String labelText = "id: " + u.getId() + "email: " + u.getEmail() + ", nom: " + u.getNom() + ", prenom: " + u.getPrenom() + ",role" + u.getRole();
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
                // Supprimer la catégorie
                boolean deleted = service.deleteCategorie(u.getId());

                if (deleted) {
                    // Suppression réussie
                    Dialog.show("Success", "user deleted successfully", "OK", null);

                    // Mettre à jour l'affichage en supprimant le label et le bouton de suppression
                    removeComponent(label);
                    removeComponent(deleteButton);
                } else {
                    // Échec de la suppression
                    Dialog.show("Error", "Failed to delete user", "OK", null);
                }
            });
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }
}
