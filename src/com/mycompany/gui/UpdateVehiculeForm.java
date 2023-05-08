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
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Categorie;
import com.mycompany.entities.Vehicule;
import com.company.services.ServiceCategorie;
import com.company.services.ServiceVehicule;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author asus
 */
public class UpdateVehiculeForm extends Form {

    public UpdateVehiculeForm(Vehicule v, Form previous) {
        setTitle("Update a vehicule");
        setLayout(BoxLayout.y());
        ServiceVehicule service = ServiceVehicule.getInstance();
        ArrayList<Vehicule> vehicules = service.getAllVehicule();

        for (Vehicule vehicule : vehicules) {
            String labelText = "id: " + vehicule.getId() + "Disponibilite: " + vehicule.getDisponibilite() + ", NumEntretien: " + vehicule.getNum_entretien() + ", DateEntretien: " + vehicule.getDate_entretien() + ", ResEntretien: " + vehicule.getRes_entretien();
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

            final Vehicule currentVehicule = vehicule; // Variable locale finale pour stocker la référence à la catégorie

            // Gestionnaire d'événements pour le clic sur le bouton "Update"
            updateButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {

                    // Créer le formulaire de mise à jour de la catégorie
                    Form updateForm = new Form("Update Vehicule", BoxLayout.y());

                    TextField disponibilite = new TextField(String.valueOf(currentVehicule.getDisponibilite()), "Disponibilite");
                    TextField numEntretien = new TextField(String.valueOf(currentVehicule.getNum_entretien()), "numEntretien");
                    TextField resEntretien = new TextField(currentVehicule.getRes_entretien(), "resEntretien");

// Utilisez le Picker pour sélectionner la date d'entretien
                    Picker dateEntretienPicker = new Picker();
                    dateEntretienPicker.setType(Display.PICKER_TYPE_DATE);
                    dateEntretienPicker.setDate(currentVehicule.getDate_entretien());

                    Button btnSave = new Button("Save");
                    btnSave.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            if (disponibilite.getText().isEmpty() || numEntretien.getText().isEmpty() || resEntretien.getText().isEmpty()) {
                                Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                            } else if (!disponibilite.getText().equals("0") && !disponibilite.getText().equals("1")) {
                                Dialog.show("Error", "Invalid Type value. Valid values are 0 or 1", new Command("OK"));
                            } else {
                                boolean confirmed = Dialog.show("Confirmation", "Are you sure you want to save the changes?", "Yes", "No");

                                if (confirmed) {
                                    Date dateEntretien = dateEntretienPicker.getDate();
                                    if (service.updateVehicule(Integer.parseInt(disponibilite.getText()), Integer.parseInt(numEntretien.getText()), dateEntretien, resEntretien.getText(), currentVehicule.getId())) {
                                        Dialog.show("Success", "Vehicule updated", new Command("OK"));
                                        showUpdatedVehiculeList(previous);
                                    } else {
                                        Dialog.show("ERROR", "Server error", new Command("OK"));
                                    }
                                }
                            }
                        }
                    });
                    updateForm.addAll(disponibilite, numEntretien, dateEntretienPicker, resEntretien, btnSave);
                    updateForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
                    updateForm.show();
                }
            });
        }
    }

    private void showUpdatedVehiculeList(Form previous) {
        Form updatedListForm = new Form("Updated Vehicule", BoxLayout.y());
        ServiceVehicule service = ServiceVehicule.getInstance();

        // Charger les catégories mises à jour
        ArrayList<Vehicule> updatedVehicules = service.getAllVehicule();

        // Afficher les catégories dans le formulaire
        for (Vehicule updatedVehicule : updatedVehicules) {
            // Créer le texte de l'étiquette
            String labelText = "id: " + updatedVehicule.getId() + "Disponibilite: " + updatedVehicule.getDisponibilite() + ", NumEntretien: " + updatedVehicule.getNum_entretien() + ", DateEntretien: " + updatedVehicule.getDate_entretien() + ", ResEntretien: " + updatedVehicule.getRes_entretien();
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
    }
}
