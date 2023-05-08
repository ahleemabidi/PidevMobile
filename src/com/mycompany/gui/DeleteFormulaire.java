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
import com.mycompany.entities.Formulairer;
import com.company.services.ServiceReservation;
import java.util.ArrayList;

/**
 *
 * @author faten
 */
public class DeleteFormulaire extends Form{
        public DeleteFormulaire(Form previous) {
        setTitle("Delete a categorie");
        setLayout(BoxLayout.y());
         ServiceReservation service = ServiceReservation.getInstance();
    ArrayList<Formulairer> formulaires = service.getAllFormulaire();
        
        // Afficher les catégories
        for (Formulairer formulaire : formulaires) {
String labelText = "id: " + formulaire.getId()+ "Num: " + formulaire.getTlp() + ", Mail: " + formulaire.getMail() + ", Nbr: " + formulaire.getNbr()+ ", type: " + formulaire.getType()+ ", Categ: " + formulaire.getCateg()+ ", depart: " + formulaire.getDepart()+ ", destination: " + formulaire.getDestination()+ ", option: " + formulaire.getOption();
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
                boolean deleted = service.deleteFormulaire(formulaire.getId());
                
                if (deleted) {
                    // Suppression réussie
                    Dialog.show("Success", "Categorie deleted successfully", "OK", null);
                    
                    // Mettre à jour l'affichage en supprimant le label et le bouton de suppression
                    removeComponent(label);
                    removeComponent(deleteButton);
                } else {
                    // Échec de la suppression
                    Dialog.show("Error", "Failed to delete categorie", "OK", null);
                }
            });
        }
                getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e-> previous.showBack());

}

    
    
}
